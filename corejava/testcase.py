import difflib
import filecmp
import json
import shlex
import sys
from collections import defaultdict
from itertools import chain

from corejava.config import ROOT_DIR


class TestCase:

    def __init__(
            self, target_manager, target_name, output_file, jvm_options=None, args=None,
            type='stdout', input_file=None, compare_file=None, testdata=None):
        """示例程序的测试用例。

        :param target_manager: TargetManager对象
        :param target_name: str 构建目标名称，格式为chapter/target_name，例如v1ch02/Foo/com.example.foo.Foo
        :param output_file: path-like 用于比较标准输出的文件(type=stdout)或程序的输出文件(type=file)
        :param jvm_options: List[str] JVM选项（可选）
        :param args: List[str] 命令行参数（可选）
        :param type: str 测试类型，stdout或file
        :param input_file: path-like 标准输入文件（可选）
        :param compare_file: path-like 用于比较输出文件的文件(type=file)
        :param testdata: List[str] 测试数据（可选）
        """
        self.target_manager = target_manager
        self.target_name = target_name
        self.chapter = target_name.split('/')[0]
        self.jvm_options = jvm_options or []
        self.args = args or []
        self.type = type
        self.input_file = input_file or None
        self.output_file = output_file
        self.compare_file = compare_file
        self.testdata = testdata or []

    def __str__(self):
        return f'Test case for {self.target_name}'

    def run(self):
        """运行示例程序，并将标准输出与指定的文件比较，如果一致则返回True，否则返回False和错误信息。"""
        result = self.target_manager.test_target(
            self.target_name, self.args, self.input_file, self.jvm_options, self.testdata)
        if self.type == 'stdout':
            with open(self.output_file, encoding='utf-8') as f:
                expected_output = f.read()
            actual_output = result.stdout
            if expected_output == actual_output:
                return True, None
            else:
                return False, self.diff_msg(expected_output, actual_output)
        elif self.type == 'file':
            if filecmp.cmp(self.output_file, self.compare_file):
                return True, None
            else:
                return False, f'File {self.output_file} and {self.compare_file} differ'
        else:
            raise ValueError(f'Unknown test type: {self.type}')

    def diff_msg(self, expected, actual):
        expected_lines = expected.splitlines(keepends=True)
        actual_lines = actual.splitlines(keepends=True)
        return ''.join(difflib.ndiff(expected_lines, actual_lines))


class TestCaseManager:

    def __init__(self, config_file, target_manager):
        """测试用例管理器。

        配置文件为JSON格式。顶层是一个JSON对象，key为章节名称，value为测试用例数组。例如：

        {"ch01": [...], "ch02": [...]}

        每个测试用例也是一个JSON对象，支持下列的key：

        - target：构建目标名称，格式同TestCase.target_name
        - jvm_options：JVM选项（可选），空格分隔的字符串
        - args：命令行参数（可选），命令行参数（可选）
        - type：测试类型，stdout - 比较标准输出和output_file（默认），file - 比较output_file和compare_file
        - input_file：标准输入文件（可选），路径相对于章节测试数据目录
        - output_file：type为stdout时表示用于比较标准输出的文件，路径相对于章节测试数据目录；
          type为file时表示程序的输出文件，路径相对于构建目标的输出目录
        - compare_file：用于比较输出文件的文件，type为file时必需，路径相对于章节测试数据目录
        - testdata：测试数据（可选），路径相对于构建目标的源文件目录

        :param config_file: str 测试用例配置文件
        :param target_manager: TargetManager对象
        """
        self.config_file = config_file
        self.target_manager = target_manager
        self.test_cases = self.load_tests()

    def load_tests(self):
        with open(self.config_file, encoding='utf-8') as f:
            test_config = json.load(f)

        test_cases = defaultdict(list)  # chapter -> [TestCase]
        for chapter, configs in test_config.items():
            for config in configs:
                test_cases[chapter].append(self.build_test_case(chapter, config))
        return test_cases

    def is_config_valid(self, config):
        return bool(config.get('target') and config.get('output_file') \
            and (not config.get('type', 'stdout') == 'file' or config.get('compare_file')))

    def build_test_case(self, chapter, config):
        if not self.is_config_valid(config):
            raise ValueError(f'Invalid config: {config}')

        target_name = f"{chapter}/{config['target']}"
        if target_name not in self.target_manager:
            raise ValueError(f'Target "{target_name}" not found.')

        testdata_dir = ROOT_DIR / chapter / 'testdata'
        target = self.target_manager[target_name]
        jvm_options = shlex.split(config.get('jvm_options', ''))
        args = shlex.split(config.get('args', ''))
        type = config.get('type', 'stdout')
        input_file = testdata_dir / config['input_file'] if 'input_file' in config else None
        output_file = target.out_dir / config['output_file'] if type == 'file' else \
            testdata_dir / config['output_file']
        compare_file = testdata_dir / config['compare_file'] if type == 'file' else None
        testdata = config.get('testdata')
        return TestCase(
            self.target_manager, target_name, output_file, jvm_options, args,
            type, input_file, compare_file, testdata)

    def iter_tests(self):
        return chain.from_iterable(self.test_cases.values())

    def run_tests(self, chapters=None):
        """运行指定章节的所有测试用例，如果未指定则运行所有测试用例。

        :param chapters: List[str] 章节列表（可选）
        """
        run = failed = 0
        for chapter, test_cases in self.test_cases.items():
            if not chapters or chapter in chapters:
                for test_case in test_cases:
                    print(f'Testing {test_case.target_name}...', end='')
                    run += 1
                    success, msg = test_case.run()
                    if success:
                        print('OK')
                    else:
                        print('failed')
                        print(msg)
                        failed += 1
        print(f'Run {run} tests, {failed} failed')
        sys.exit(failed)
