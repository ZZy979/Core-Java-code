import unittest
from io import StringIO
from unittest.mock import Mock, patch, mock_open

from corejava.config import ROOT_DIR, OUT_DIR
from corejava.target import TargetManager
from corejava.testcase import TestCase as JavaTestCase, TestCaseManager


class TestCaseTest(unittest.TestCase):

    def setUp(self):
        self.testdata_dir = ROOT_DIR / 'corejava/testdata'
        self.target_manager = TargetManager(self.testdata_dir / 'targets.json')
        self.target = self.target_manager['ch01/A']
        self.test_case = JavaTestCase(
            self.target_manager, 'ch01/A', 'A_output.txt', jvm_options=['-Dfile.encoding=UTF-8'],
            args=['arg1', 'arg2'], input_file='A_input.txt', testdata=['data/a.txt'])
        self.test_case2 = JavaTestCase(
            self.target_manager, 'ch01/B', type='file',
            output_file='actual_out.txt', compare_file='expected_out.txt')
        for target in self.target_manager.targets.values():
            target.build = Mock()
            target.test = Mock()

    def test_init(self):
        t = self.test_case
        self.assertEqual('ch01/A', t.target_name)
        self.assertEqual('ch01', t.chapter)
        self.assertListEqual(['-Dfile.encoding=UTF-8'], t.jvm_options)
        self.assertListEqual(['arg1', 'arg2'], t.args)
        self.assertEqual('stdout', t.type)
        self.assertEqual('A_input.txt', t.input_file)
        self.assertEqual('A_output.txt', t.output_file)
        self.assertIsNone(t.compare_file)
        self.assertListEqual(['data/a.txt'], t.testdata)

        t2 = self.test_case2
        self.assertEqual('ch01/B', t2.target_name)
        self.assertEqual('file', t2.type)
        self.assertEqual('actual_out.txt', t2.output_file)
        self.assertEqual('expected_out.txt', t2.compare_file)

    def test_run_passed(self):
        attrs = {'return_value.stdout': 'Hello, world!'}
        self.target.test.configure_mock(**attrs)
        with patch('sys.stdout', new_callable=StringIO), \
                patch('builtins.open', mock_open(read_data='Hello, world!')):
            self.assertTrue(self.test_case.run()[0])
            self.target.build.assert_called_once()
            self.target.test.assert_called_once_with(
                ['arg1', 'arg2'], 'A_input.txt', ['-Dfile.encoding=UTF-8'], ['data/a.txt'])

    def test_run_failed(self):
        attrs = {'return_value.stdout': 'Goodbye, world!'}
        self.target.test.configure_mock(**attrs)
        with patch('sys.stdout', new_callable=StringIO), \
                patch('builtins.open', mock_open(read_data='Hello, world!')):
            self.assertFalse(self.test_case.run()[0])

    def test_run_file_passed(self):
        with patch('filecmp.cmp', return_value=True) as filecmp:
            self.assertTrue(self.test_case2.run()[0])
            filecmp.assert_called_once_with('actual_out.txt', 'expected_out.txt')

    def test_run_file_failed(self):
        with patch('filecmp.cmp', return_value=False):
            self.assertFalse(self.test_case2.run()[0])

    def test_run_unknown_type(self):
        self.test_case.type = 'xxx'
        self.assertRaises(ValueError, self.test_case.run)


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
                ('A', [], [], 'stdout', None, ROOT_DIR / 'ch01/testdata/A_output.txt',
                    None, ['data/a.txt', 'conf/a.conf']),
                ('B', [], [], 'stdout', ROOT_DIR / 'ch01/testdata/B_input1.txt',
                    ROOT_DIR / 'ch01/testdata/B_output1.txt', None, []),
                ('B', [], [], 'stdout', ROOT_DIR / 'ch01/testdata/B_input2.txt',
                    ROOT_DIR / 'ch01/testdata/B_output2.txt', None, []),
            ],
            'ch02': [
                ('C', ['-Dfile.encoding=UTF-8'], ['arg1', 'arg2'], 'stdout', None,
                    ROOT_DIR / 'ch02/testdata/C_output.txt', None, []),
                ('D', [], ['hello world'], 'stdout', None,
                    ROOT_DIR / 'ch02/testdata/D_output.txt', None, []),
            ],
            'ch03': [
                ('E', [], [], 'stdout', None, ROOT_DIR / 'ch03/testdata/E_output.txt', None, []),
            ],
            'ch04': [
                ('mod1/F', [], [], 'stdout', None, ROOT_DIR / 'ch04/testdata/F_output.txt', None, []),
                ('mod2/G', [], ['-o', 'G_actual_out.txt'], 'file', None,
                    OUT_DIR / 'ch04/mod2/G_actual_out.txt', ROOT_DIR / 'ch04/testdata/G_expected.txt', [])
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
                self.assertEqual(t[3], t2.type)
                self.assertEqual(t[4], t2.input_file)
                self.assertEqual(t[5], t2.output_file)
                self.assertEqual(t[6], t2.compare_file)
                self.assertListEqual(t[7], t2.testdata)

    def test_load_tests_with_missing_config(self):
        self.assertRaisesRegex(
            ValueError, "Invalid config: {'target': 'A'}",
            TestCaseManager, self.testdata_dir / 'tests_missing_config.json', self.target_manager)

    def test_load_tests_with_invalid_target(self):
        self.assertRaisesRegex(
            ValueError, 'Target "ch01/F" not found',
            TestCaseManager, self.testdata_dir / 'tests_invalid_target.json', self.target_manager)

    def test_is_config_invalid(self):
        test_cases = [
            ({}, False),
            ({'target': 'ch01/A'}, False),
            ({'target': 'ch01/A', 'output_file': 'A_output.txt'}, True),
            ({'target': 'ch01/B', 'type': 'file', 'output_file': 'B_actual_out.txt'}, False),
            ({'target': 'ch01/B', 'type': 'file', 'output_file': 'B_actual_out.txt',
                'compare_file': 'B_expected_out.txt'}, True),
        ]
        for config, expected in test_cases:
            self.assertEqual(expected, self.test_case_manager.is_config_valid(config))

    def test_build_test_case(self):
        config = {
            'target': 'C', 'jvm_options': '-Dfile.encoding=UTF-8', 'args': 'arg1 arg2',
            'type': 'file', 'input_file': 'C_input.txt', 'output_file': 'C_actual_out.txt',
            'compare_file': 'C_expected_out.txt', 'testdata': ['data.txt']
        }
        t = self.test_case_manager.build_test_case('ch02', config)
        self.assertEqual('ch02/C', t.target_name)
        self.assertListEqual(['-Dfile.encoding=UTF-8'], t.jvm_options)
        self.assertListEqual(['arg1', 'arg2'], t.args)
        self.assertEqual('file', t.type)
        self.assertEqual(ROOT_DIR / 'ch02/testdata/C_input.txt', t.input_file)
        self.assertEqual(OUT_DIR / 'ch02/C_actual_out.txt', t.output_file)
        self.assertEqual(ROOT_DIR / 'ch02/testdata/C_expected_out.txt', t.compare_file)
        self.assertListEqual(['data.txt'], t.testdata)

    def test_run_tests(self):
        failed_tests = {'ch02/C', 'ch03/E'}
        test_cases = [
            (['ch02'], 2, 1),
            (['ch01', 'ch03'], 4, 1),
            (None, 8, 2)
        ]

        for chapters, run, failed in test_cases:
            for test_case in self.test_case_manager.iter_tests():
                test_case.run = Mock(return_value=(test_case.target_name not in failed_tests, ''))
            with patch('sys.stdout', new_callable=StringIO) as stdout, \
                patch('sys.exit') as mock_exit:
                self.test_case_manager.run_tests(chapters)
                self.assertRegex(stdout.getvalue(), f'Run {run} tests, {failed} failed')
                mock_exit.assert_called_once_with(failed)
