import unittest
from io import StringIO
from unittest.mock import Mock, patch, mock_open

from corejava.config import ROOT_DIR
from corejava.target import TargetManager
from corejava.testcase import TestCase as JavaTestCase, TestCaseManager


class TestCaseTest(unittest.TestCase):

    def setUp(self):
        self.testdata_dir = ROOT_DIR / 'corejava/testdata'
        self.target_manager = TargetManager(self.testdata_dir / 'targets.json')
        self.target = self.target_manager['ch01/A']
        self.test_case = JavaTestCase(
            self.target_manager, 'ch01/A', 'A_output.txt',
            ['-Dfile.encoding=UTF-8'], ['arg1', 'arg2'], 'A_input.txt')
        for target in self.target_manager.targets.values():
            target.build = Mock()
            target.test = Mock()

    def test_run_passed(self):
        attrs = {'return_value.stdout': 'Hello, world!'}
        self.target.test.configure_mock(**attrs)
        with patch('sys.stdout', new_callable=StringIO) as stdout, \
                patch('builtins.open', mock_open(read_data='Hello, world!')):
            self.assertTrue(self.test_case.run())
            self.assertEqual('Testing ch01/A...OK\n', stdout.getvalue())
            self.target.build.assert_called_once()
            self.target.test.assert_called_once_with(['arg1', 'arg2'], 'A_input.txt', ['-Dfile.encoding=UTF-8'])

    def test_run_failed(self):
        attrs = {'return_value.stdout': 'Goodbye, world!'}
        self.target.test.configure_mock(**attrs)
        with patch('sys.stdout', new_callable=StringIO) as stdout, \
                patch('builtins.open', mock_open(read_data='Hello, world!')):
            self.assertFalse(self.test_case.run())
            self.assertTrue(stdout.getvalue().startswith('Testing ch01/A...failed'))


class TestCaseManagerTest(unittest.TestCase):

    def setUp(self):
        self.testdata_dir = ROOT_DIR / 'corejava/testdata'
        self.target_manager = TargetManager(self.testdata_dir / 'targets.json')
        self.test_case_manager = TestCaseManager(self.testdata_dir / 'tests.json', self.target_manager)
        for target in self.target_manager.targets.values():
            target.build = Mock()
            target.test = Mock()

    def test_load_tests(self):
        test_cases = {
            'ch01': [
                ('A', [], [], None, ROOT_DIR / 'ch01/testdata/A_output.txt'),
                ('B', [], [], ROOT_DIR / 'ch01/testdata/B_input1.txt', ROOT_DIR / 'ch01/testdata/B_output1.txt'),
                ('B', [], [], ROOT_DIR / 'ch01/testdata/B_input2.txt', ROOT_DIR / 'ch01/testdata/B_output2.txt'),
            ],
            'ch02': [
                ('C', ['-Dfile.encoding=UTF-8'], ['arg1', 'arg2'], None, ROOT_DIR / 'ch02/testdata/C_output.txt'),
                ('D', [], ['hello world'], None, ROOT_DIR / 'ch02/testdata/D_output.txt'),
            ],
            'ch03': [
                ('E', [], [], None, ROOT_DIR / 'ch03/testdata/E_output.txt'),
            ],
        }
        for chapter, expected in test_cases.items():
            actual = self.test_case_manager.test_cases[chapter]
            self.assertEqual(len(expected), len(actual))
            for t, t2 in zip(expected, actual):
                self.assertEqual(chapter, t2.chapter)
                self.assertEqual(f'{chapter}/{t[0]}', t2.target_name)
                self.assertListEqual(t[1], t2.jvm_options)
                self.assertListEqual(t[2], t2.args)
                self.assertEqual(t[3], t2.input_file)
                self.assertEqual(t[4], t2.output_file)

    def test_load_tests_with_missing_config(self):
        self.assertRaisesRegex(
            ValueError, 'Missing "target" or "output_file" in test case:',
            TestCaseManager, self.testdata_dir / 'tests_missing_config.json', self.target_manager)

    def test_load_tests_with_invalid_target(self):
        self.assertRaisesRegex(
            ValueError, 'Target "ch01/F" not found',
            TestCaseManager, self.testdata_dir / 'tests_invalid_target.json', self.target_manager)

    def test_run_tests(self):
        failed_tests = {'ch02/C', 'ch03/E'}
        test_cases = [
            (['ch02'], 2, 1),
            (['ch01', 'ch03'], 4, 1),
            (None, 6, 2)
        ]

        for chapters, run, failed in test_cases:
            for test_case in self.test_case_manager.iter_tests():
                test_case.run = Mock(return_value=test_case.target_name not in failed_tests)
            with patch('sys.stdout', new_callable=StringIO) as stdout, \
                patch('sys.exit') as mock_exit:
                self.test_case_manager.run_tests(chapters)
                self.assertEqual(f'Run {run} tests, {failed} failed\n', stdout.getvalue())
                mock_exit.assert_called_once_with(failed)
