import difflib
import json
import shlex
import sys
from collections import defaultdict
from itertools import chain

from corejava.config import ROOT_DIR


class TestCase:

    def __init__(self, target_manager, target_name, output_file, jvm_options=None, args=None, input_file=None):
        """示例程序的测试用例。

        :param target_manager: TargetManager对象
        :param target_name: str 构建目标名称，格式为chapter/name，例如v1ch02/Foo/com.example.foo.Foo
        :param output_file: str 用于比较标准输出的文件
        :param jvm_options: List[str] JVM选项（可选）
        :param args: List[str] 命令行参数（可选）
        :param input_file: 标准输入文件（可选）
        """
        self.target_manager = target_manager
        self.target_name = target_name
        self.chapter = target_name.split('/')[0]
        self.jvm_options = jvm_options or []
        self.args = args or []
        self.input_file = input_file or None
        self.output_file = output_file

    def __str__(self):
        return f'Test case for {self.target_name}'

    def run(self):
        """运行示例程序，并将标准输出与指定的文件比较，如果一致则返回True。"""
        print(f'Testing {self.target_name}...', end='')
        with open(self.output_file, encoding='utf-8') as f:
            expected_output = f.read()
        result = self.target_manager.test_target(self.target_name, self.args, self.input_file, self.jvm_options)
        actual_output = result.stdout
        if expected_output == actual_output:
            print('OK')
            return True
        else:
            print('failed')
            self._print_diff(expected_output, actual_output)
            return False

    def _print_diff(self, expected, actual):
        expected_lines = expected.splitlines(keepends=True)
        actual_lines = actual.splitlines(keepends=True)
        sys.stdout.writelines(difflib.ndiff(expected_lines, actual_lines))


class TestCaseManager:

    def __init__(self, config_file, target_manager):
        """测试用例管理器。

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
            testdata_dir = ROOT_DIR / chapter / 'testdata'
            for config in configs:
                if not config.get('target') or not config.get('output_file'):
                    raise ValueError(f'Missing "target" or "output_file" in test case: {config}')

                target_name = f"{chapter}/{config['target']}"
                if target_name not in self.target_manager:
                    raise ValueError(f'Target "{target_name}" not found.')

                jvm_options = shlex.split(config.get('jvm_options', ''))
                args = shlex.split(config.get('args', ''))
                input_file = testdata_dir / config['input_file'] if 'input_file' in config else None
                output_file = testdata_dir / config['output_file']
                test_case = TestCase(self.target_manager, target_name, output_file, jvm_options, args, input_file)
                test_cases[chapter].append(test_case)
        return test_cases

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
                    run += 1
                    if not test_case.run():
                        failed += 1
        print(f'Run {run} tests, {failed} failed')
        sys.exit(failed)
