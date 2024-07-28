import json
import pathlib
import subprocess
import sys
from subprocess import PIPE

root_dir = pathlib.Path(__file__).resolve().parent
out_dir = root_dir / 'out'


def run_test(chapter_dir, test_case):
    chapter = chapter_dir.name
    target = pathlib.Path(test_case['target'])
    src_file = chapter_dir / target.with_suffix('.java')
    class_dir = out_dir / chapter / target.parent
    class_name = target.name
    output_file = chapter_dir / 'testdata' / test_case['output_file']

    subprocess.run(f'javac -d {class_dir} {src_file}'.split())
    result = subprocess.run(
        f'java {class_name}'.split(), stdout=PIPE, stderr=PIPE,
        cwd=class_dir, encoding='utf-8', text=True)
    with open(output_file, encoding='utf-8') as f:
        return f.read() == result.stdout


def main():
    run = failed = 0
    for chapter_dir in root_dir.glob('v*'):
        test_file = chapter_dir / 'testdata/tests.json'
        if test_file.exists():
            with open(test_file, encoding='utf-8') as f:
                test_cases = json.load(f)
            for test_case in test_cases:
                print('Testing {}...'.format(test_case['target']), end='')
                run += 1
                if run_test(chapter_dir, test_case):
                    print('OK')
                else:
                    failed += 1
    print(f'Run {run} tests, {failed} failed')
    sys.exit(failed)


if __name__ == '__main__':
    main()
