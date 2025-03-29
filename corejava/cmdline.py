import shutil
from argparse import ArgumentParser

from corejava.config import *
from corejava.target import TargetManager
from corejava.testcase import TestCaseManager


def run(args):
    target_manager = TargetManager(TARGETS_CONFIG_FILE)
    target_manager.run_target(args.target, args.args)


def test(args):
    target_manager = TargetManager(TARGETS_CONFIG_FILE)
    test_case_manager = TestCaseManager(TESTS_CONFIG_FILE, target_manager)
    test_case_manager.run_tests(args.chapters)


def clean(args):
    if not args.chapters:
        shutil.rmtree(OUT_DIR)
        print(f'Deleted {OUT_DIR}')
    else:
        for chapter in args.chapters:
            shutil.rmtree(OUT_DIR / chapter)
            print(f'Deleted {OUT_DIR / chapter}')


def create_arg_parser():
    parser = ArgumentParser(prog='python -m corejava', description='《Java核心技术》书中示例程序运行和测试工具脚本')
    parser.set_defaults(func=lambda args: parser.print_help())
    sub_parsers = parser.add_subparsers(help='子命令')

    run_parser = sub_parsers.add_parser('run', help='运行示例程序')
    run_parser.add_argument('target', help='示例程序名称（例如Foo/com.example.foo.Foo）')
    run_parser.add_argument('args', nargs='*', help='命令行参数')
    run_parser.set_defaults(func=run)

    test_parser = sub_parsers.add_parser('test', help='运行测试')
    test_parser.add_argument('chapters', nargs='*', help='章节名称')
    test_parser.set_defaults(func=test)

    clean_parser = sub_parsers.add_parser('clean', help='清理编译输出')
    clean_parser.add_argument('chapters', nargs='*', help='章节名称')
    clean_parser.set_defaults(func=clean)

    return parser


def main():
    parser = create_arg_parser()
    args = parser.parse_args()
    args.func(args)
