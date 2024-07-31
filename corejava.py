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

    - chapter_dir：章节目录，例如ROOT_DIR/v1ch02
    - chapter：章节名称，例如v1ch02
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

    def __init__(self, chapter_dir, target):
        self.chapter_dir = chapter_dir
        self.chapter = chapter_dir.name

        self.target = target
        target_p = pathlib.Path(target)
        self.sub_dir = target_p.parent
        self.full_class_name = target_p.name
        self.package_name, self.class_name = self.parse_full_class_name()

        self.package_dir = pathlib.Path(self.package_name.replace('.', '/'))
        self.src_dir = chapter_dir / self.sub_dir
        self.src_file = self.src_dir / self.package_dir / (self.class_name + '.java')
        self.testdata_dir = chapter_dir / 'testdata'
        self.class_dir = OUT_DIR / self.chapter / self.sub_dir

    def __str__(self):
        return self.target

    def parse_full_class_name(self):
        if '.' in self.full_class_name:
            return self.full_class_name.rsplit('.', 1)
        else:
            return '', self.full_class_name

    def compile(self):
        cmd = ['javac', '-d', self.class_dir, self.src_file]
        subprocess.run(cmd, cwd=self.src_dir, check=True)

    def run(self, compile_first=True, args='', input_file=None):
        """运行示例程序。

        :param compile_first: bool 是否先执行编译命令
        :param args: str 命令行参数，空格分隔
        :param input_file: str 输入文件名
        :return: subprocess.CompletedProcess对象
        """
        if compile_first:
            self.compile()

        if input_file:
            stdin = open(input_file, encoding='utf-8')
        else:
            stdin = DEVNULL

        cmd = ['java', self.full_class_name, *shlex.split(args)]
        result = subprocess.run(
            cmd, stdin=stdin, stdout=PIPE, stderr=PIPE,
            cwd=self.class_dir, encoding='utf-8', text=True)

        if input_file:
            stdin.close()
        return result

    def clean(self):
        shutil.rmtree(self.class_dir)


class TestCase:

    def __init__(self, chapter_dir, config):
        """一个示例程序的测试用例。

        :param chapter_dir: pathlib.Path 章节目录
        :param config: dict 测试用例配置，包含以下键值对：
            "target": 示例程序名称，格式同Example.target
            "args": 命令行参数（可选）
            "input_file": 标准输入文件（可选）
            "output_file": 用于比较标准输出的文件
        """
        self.example = Example(chapter_dir, config['target'])
        self.config = config
        self.args = self.config.get('args', '')
        self.input_file = self.example.testdata_dir / config['input_file'] \
            if 'input_file' in config else None
        self.output_file = self.example.testdata_dir / config['output_file']

    def run(self):
        """运行示例程序，并将标准输出与指定的文件比较。

        :return: bool 输出与预期是否一致
        """
        with open(self.output_file, encoding='utf-8') as f:
            expected_output = f.read()
        result = self.example.run(args=self.args, input_file=self.input_file)
        return expected_output == result.stdout


def main():
    run = failed = 0
    for chapter_dir in ROOT_DIR.glob('v*'):
        test_file = chapter_dir / 'testdata/tests.json'
        if test_file.exists():
            with open(test_file, encoding='utf-8') as f:
                configs = json.load(f)
            for config in configs:
                print('Testing {}...'.format(config['target']), end='')
                run += 1
                test_case = TestCase(chapter_dir, config)
                if test_case.run():
                    print('OK')
                else:
                    print('failed')
                    failed += 1
    print(f'Run {run} tests, {failed} failed')
    sys.exit(failed)


if __name__ == '__main__':
    main()
