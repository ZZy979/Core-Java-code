import argparse
import difflib
import json
import pathlib
import shlex
import shutil
import subprocess
import sys
from subprocess import PIPE, DEVNULL

ROOT_DIR = pathlib.Path(__file__).resolve().parent
OUT_DIR = ROOT_DIR / 'out'


class Example:
    """书中的一个示例程序。包含以下属性：

    - chapter：章节名称，例如v1ch02
    - chapter_dir：章节目录，例如ROOT_DIR/v1ch02
    - target：示例程序名称，格式为[subdir/][package.]classname，例如Foo/Bar/com.example.foo.Foo
    - sub_dir：子目录（可能为空），例如Foo/Bar
    - full_class_name：完整类名，例如com.example.foo.Foo
    - package_name：包名（可能为空），例如com.example.foo
    - class_name：类名，例如Foo
    - package_dir：包目录（可能为空），例如com/example/foo
    - src_dir：源文件目录，例如ROOT_DIR/v1ch02/Foo/Bar
    - src_file：源文件，例如ROOT_DIR/v1ch02/Foo/Bar/com/example/foo/Foo.java
    - testdata_dir：测试数据目录，例如ROOT_DIR/v1ch02/testdata
    - class_dir：类文件输出目录，例如OUT_DIR/v1ch02/Foo/Bar
    """

    def __init__(self, chapter, target):
        self.chapter = chapter
        self.chapter_dir = ROOT_DIR / chapter

        self.target = target
        target_p = pathlib.Path(target)
        self.sub_dir = target_p.parent
        self.full_class_name = target_p.name
        self.package_name, self.class_name = self.parse_full_class_name()

        self.package_dir = pathlib.Path(self.package_name.replace('.', '/'))
        self.src_dir = self.chapter_dir / self.sub_dir
        self.src_file = self.src_dir / self.package_dir / (self.class_name + '.java')
        self.testdata_dir = self.chapter_dir / 'testdata'
        self.class_dir = OUT_DIR / self.chapter / self.sub_dir

    def __str__(self):
        return f'{self.chapter}/{self.target}'

    def parse_full_class_name(self):
        if '.' in self.full_class_name:
            return self.full_class_name.rsplit('.', 1)
        else:
            return '', self.full_class_name

    def build(self, compile_options=''):
        """编译示例程序。

        :param compile_options: str 编译选项，空格分隔
        """
        cmd = ['javac', '-d', self.class_dir, *shlex.split(compile_options), self.src_file]
        subprocess.run(cmd, cwd=self.src_dir, check=True)

    def run(self, compile_options='', jvm_options='', args=''):
        """运行示例程序。

        :param compile_options: str 编译选项，空格分隔
        :param jvm_options: str JVM选项，空格分隔
        :param args: str 命令行参数，空格分隔
        """
        self.build(compile_options)
        cmd = ['java', *shlex.split(jvm_options), self.full_class_name, *shlex.split(args)]
        subprocess.run(cmd, cwd=self.class_dir)

    def test(self, compile_options='', jvm_options='', args='', input_file=None):
        """测试示例程序。

        :param compile_options: str 编译选项，空格分隔
        :param jvm_options: str JVM选项，空格分隔
        :param args: str 命令行参数，空格分隔
        :param input_file: str 输入文件名
        :return: subprocess.CompletedProcess对象
        """
        self.build(compile_options)

        if input_file:
            stdin = open(input_file, encoding='utf-8')
        else:
            stdin = DEVNULL

        cmd = ['java', *shlex.split(jvm_options), self.full_class_name, *shlex.split(args)]
        result = subprocess.run(cmd, stdin=stdin, stdout=PIPE, cwd=self.class_dir, encoding='utf-8', text=True)

        if input_file:
            stdin.close()
        return result

    def clean(self):
        """清理编译输出。"""
        shutil.rmtree(self.class_dir)


class TestCase:

    def __init__(self, chapter, config):
        """一个示例程序的测试用例。

        :param chapter: str 章节名称
        :param config: dict 测试用例配置，包含以下键值对：
            "target": 示例程序名称，格式同Example.target
            "compile_options"：编译选项（可选）
            "jvm_options"：JVM选项（可选）
            "args": 命令行参数（可选）
            "input_file": 标准输入文件（可选）
            "output_file": 用于比较标准输出的文件
        """
        if not config.get('target') or not config.get('output_file'):
            raise ValueError(f'Missing "target" or "output_file" in config: {config}')

        self.example = Example(chapter, config['target'])
        self.config = config
        self.compile_options = self.config.get('compile_options', '')
        self.jvm_options = self.config.get('jvm_options', '')
        self.args = self.config.get('args', '')
        self.input_file = self.example.testdata_dir / config['input_file'] \
            if 'input_file' in config else None
        self.output_file = self.example.testdata_dir / config['output_file']

    def __str__(self):
        return str(self.example)

    def run(self):
        """运行示例程序，并将标准输出与指定的文件比较。

        :return: bool 输出与预期是否一致
        """
        print(f'Testing {self}...', end='')
        with open(self.output_file, encoding='utf-8') as f:
            expected_output = f.read()
        result = self.example.test(self.compile_options, self.jvm_options, self.args, self.input_file)
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


def load_tests():
    """加载所有的测试用例。"""
    with open(ROOT_DIR / 'tests.json', encoding='utf-8') as f:
        tests = json.load(f)
    for chapter, configs in tests.items():
        for config in configs:
            yield TestCase(chapter, config)


def run(args):
    """运行示例程序。"""
    chapter, target = args.target.split('/', 1)
    Example(chapter, target).run(args.args)


def test(args):
    """运行测试。"""
    run = failed = 0
    for test_case in load_tests():
        if not args.chapter or test_case.example.chapter == args.chapter:
            run += 1
            if not test_case.run():
                failed += 1
    print(f'Run {run} tests, {failed} failed')
    sys.exit(failed)


def create_arg_parser():
    parser = argparse.ArgumentParser(description='《Java核心技术》书中示例程序运行和测试工具')
    parser.set_defaults(func=lambda args: parser.print_help())
    sub_parsers = parser.add_subparsers(help='子命令')

    run_parser = sub_parsers.add_parser('run', help='运行示例程序')
    run_parser.add_argument('target', help='示例程序名称（例如v1ch02/Welcome/Welcome）')
    run_parser.add_argument('-a', '--args', default='', help='命令行参数，用引号括起来')
    run_parser.set_defaults(func=run)

    test_parser = sub_parsers.add_parser('test', help='运行测试')
    test_parser.add_argument('-c', '--chapter', help='章节名称')
    test_parser.set_defaults(func=test)

    return parser


def main():
    parser = create_arg_parser()
    args = parser.parse_args()
    args.func(args)


if __name__ == '__main__':
    main()
