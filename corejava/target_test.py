import os
import unittest
from unittest.mock import Mock, patch

from corejava.config import ROOT_DIR, OUT_DIR
from corejava.target import Target, TargetManager


class TargetTest(unittest.TestCase):

    def setUp(self):
        self.foo = Target(
            'ch01', 'Foo/com.example.foo.Foo', None, class_path=['ch01/foo_lib.jar'])
        self.bar = Target(
            'ch02', 'com.example.bar.Bar', 'mod2',
            ['com/example/bar/Bar.java', 'com/example/bar/internal/BarImpl.java'],
            ['ch01/Foo/com.example.foo.Foo'],
            module_path=['ch02/bar_lib.jar'],
            resources=['data/bar1.txt', 'data/bar2.txt'])

    def test_init(self):
        self.assertEqual('ch01', self.foo.chapter)
        self.assertEqual('ch01/Foo/com.example.foo.Foo', self.foo.name)
        self.assertIsNone(self.foo.module_name)
        self.assertFalse(self.foo.is_module)
        self.assertEqual('com.example.foo.Foo', self.foo.main_class)
        self.assertEqual(ROOT_DIR / 'ch01/Foo', self.foo.src_dir)
        self.assertEqual(OUT_DIR / 'ch01/Foo', self.foo.out_dir)
        self.assertEqual('com/example/foo/Foo.java', self.foo.src_file)
        self.assertListEqual(['com/example/foo/Foo.java'], self.foo.srcs)
        self.assertListEqual([], self.foo.deps)
        self.assertListEqual([ROOT_DIR / 'ch01/foo_lib.jar'], self.foo.class_path)
        self.assertListEqual([], self.foo.module_path)
        self.assertListEqual([], self.foo.resources)

        self.assertEqual('ch02', self.bar.chapter)
        self.assertEqual('ch02/com.example.bar.Bar', self.bar.name)
        self.assertEqual('mod2', self.bar.module_name)
        self.assertTrue(self.bar.is_module)
        self.assertEqual('com.example.bar.Bar', self.bar.main_class)
        self.assertEqual(ROOT_DIR / 'ch02', self.bar.src_dir)
        self.assertEqual(OUT_DIR / 'ch02', self.bar.out_dir)
        self.assertEqual('com/example/bar/Bar.java', self.bar.src_file)
        self.assertListEqual(['com/example/bar/Bar.java', 'com/example/bar/internal/BarImpl.java'], self.bar.srcs)
        self.assertListEqual(['ch01/Foo/com.example.foo.Foo'], self.bar.deps)
        self.assertListEqual([], self.bar.class_path)
        self.assertListEqual([ROOT_DIR / 'ch02/bar_lib.jar'], self.bar.module_path)
        self.assertListEqual(['data/bar1.txt', 'data/bar2.txt'], self.bar.resources)

    def test_get_class_path(self):
        self.assertListEqual(['.', str(ROOT_DIR / 'ch01/foo_lib.jar')], self.foo.get_class_path())
        self.assertListEqual(['.', str(OUT_DIR / 'ch01/Foo')], self.bar.get_class_path())

    def test_get_module_path(self):
        self.assertListEqual([], self.foo.get_module_path(False))
        self.assertListEqual([str(OUT_DIR / 'ch01/Foo')], self.foo.get_module_path(True))
        self.assertListEqual(
            [str(OUT_DIR / 'ch01/Foo'), str(ROOT_DIR / 'ch02/bar_lib.jar')],
            self.bar.get_module_path(False))
        self.assertListEqual(
            [str(OUT_DIR / 'ch01/Foo'), str(OUT_DIR / 'ch02'), str(ROOT_DIR / 'ch02/bar_lib.jar')],
            self.bar.get_module_path(True))

    def test_get_compile_command(self):
        self.foo.is_module = self.bar.is_module = False

        foo_compile_command = [
            'javac', '-d', OUT_DIR / 'ch01/Foo',
            '-cp', os.pathsep.join(['.', str(ROOT_DIR / 'ch01/foo_lib.jar')]),
            'com/example/foo/Foo.java'
        ]
        self.assertListEqual(foo_compile_command, self.foo.get_compile_command())

        bar_compile_command = [
            'javac', '-d', OUT_DIR / 'ch02',
            '-cp', os.pathsep.join(['.', str(OUT_DIR / 'ch01/Foo')]),
            'com/example/bar/Bar.java', 'com/example/bar/internal/BarImpl.java'
        ]
        self.assertListEqual(bar_compile_command, self.bar.get_compile_command())

    def test_get_compile_command_for_module(self):
        self.foo.is_module = self.bar.is_module = True

        foo_compile_command = [
            'javac', '-d', OUT_DIR / 'ch01/Foo',
            'module-info.java', 'com/example/foo/Foo.java'
        ]
        self.assertListEqual(foo_compile_command, self.foo.get_compile_command())

        bar_compile_command = [
            'javac', '-d', OUT_DIR / 'ch02',
            '-p', os.pathsep.join([str(OUT_DIR / 'ch01/Foo'), str(ROOT_DIR / 'ch02/bar_lib.jar')]),
            'module-info.java', 'com/example/bar/Bar.java', 'com/example/bar/internal/BarImpl.java'
        ]
        self.assertListEqual(bar_compile_command, self.bar.get_compile_command())

    def test_get_run_command(self):
        self.foo.is_module = self.bar.is_module = False

        foo_run_command = [
            'java', '-cp', os.pathsep.join(['.', str(ROOT_DIR / 'ch01/foo_lib.jar')]),
            'com.example.foo.Foo'
        ]
        self.assertListEqual(foo_run_command, self.foo.get_run_command())

        bar_run_command = [
            'java', '-cp', os.pathsep.join(['.', str(OUT_DIR / 'ch01/Foo')]),
            '-Dfile.encoding=UTF-8', 'com.example.bar.Bar', 'arg1', 'arg2'
        ]
        self.assertListEqual(
            bar_run_command, self.bar.get_run_command(['arg1', 'arg2'], ['-Dfile.encoding=UTF-8']))

    def test_get_run_command_for_module(self):
        self.foo.is_module = self.bar.is_module = True
        self.foo.module_name = 'mod1'
        self.bar.module_name = 'mod2'

        foo_run_command = ['java', '-p', str(OUT_DIR / 'ch01/Foo'), '-m', 'mod1/com.example.foo.Foo']
        self.assertListEqual(foo_run_command, self.foo.get_run_command())

        bar_run_command = [
            'java', '-p', os.pathsep.join([str(OUT_DIR / 'ch01/Foo'), str(OUT_DIR / 'ch02'), str(ROOT_DIR / 'ch02/bar_lib.jar')]),
            '-Dfile.encoding=UTF-8', '-m', 'mod2/com.example.bar.Bar', 'arg1', 'arg2'
        ]
        self.assertListEqual(
            bar_run_command, self.bar.get_run_command(['arg1', 'arg2'], ['-Dfile.encoding=UTF-8']))

    @patch('os.makedirs')
    @patch('shutil.copy')
    def test_copy_resources(self, mock_copy, mock_makedirs):
        self.bar.copy_resources()
        self.assertEqual(2, mock_makedirs.call_count)
        mock_makedirs.assert_called_with(OUT_DIR / 'ch02/data', exist_ok=True)
        self.assertEqual(2, mock_copy.call_count)
        mock_copy.assert_any_call(ROOT_DIR / 'ch02/data/bar1.txt', OUT_DIR / 'ch02/data/bar1.txt')
        mock_copy.assert_any_call(ROOT_DIR / 'ch02/data/bar2.txt', OUT_DIR / 'ch02/data/bar2.txt')

        mock_makedirs.reset_mock()
        mock_copy.reset_mock()
        self.bar.copy_resources(['conf/bar.conf'])
        mock_makedirs.assert_called_once_with(OUT_DIR / 'ch02/conf', exist_ok=True)
        mock_copy.assert_called_once_with(ROOT_DIR / 'ch02/conf/bar.conf', OUT_DIR / 'ch02/conf/bar.conf')


class TargetManagerTest(unittest.TestCase):

    def setUp(self):
        self.testdata_dir = ROOT_DIR / 'corejava/testdata'
        self.target_manager = TargetManager(self.testdata_dir / 'targets.json')
        for target in self.target_manager.iter_targets():
            target.build = Mock()
            target.run = Mock()
            target.test = Mock()

    def test_load_targets(self):
        targets = [
            ('ch01/A', None, ['A.java'], [], [], [], []),
            ('ch01/B', None, ['B.java'], [], [], [], ['data/b.txt']),
            ('ch02/C', None, ['C.java'], ['ch01/A', 'ch01/B'], [], [], []),
            ('ch02/D', None, ['D.java'], ['ch01/B'], [], [], []),
            ('ch03/E', None, ['E.java'], ['ch02/C', 'ch02/D'], [ROOT_DIR / 'ch02/foo_lib.jar'], [], []),
            ('ch04/mod1/F', 'mod1', ['F.java', 'internal/FF.java'], [], [], [], ['conf/foo.conf']),
            ('ch04/mod2/G', 'mod2', ['G.java'], ['ch04/mod1/F'], [], [ROOT_DIR / 'ch04/bar_lib.jar'], []),
        ]
        self.assertEqual(len(targets), len(self.target_manager.targets))
        for t in targets:
            self.assertIn(t[0], self.target_manager)
            target = self.target_manager[t[0]]
            self.assertEqual(t[0], target.name)
            self.assertEqual(t[1], target.module_name)
            self.assertListEqual(t[2], target.srcs)
            self.assertListEqual(t[3], target.deps)
            self.assertListEqual(t[4], target.class_path)
            self.assertListEqual(t[5], target.module_path)
            self.assertListEqual(t[6], target.resources)

    def test_load_targets_with_invalid_dependency(self):
        self.assertRaisesRegex(
            ValueError, 'Dependency "ch02/B" of target "ch01/A" does not exits',
            TargetManager, self.testdata_dir / 'targets_invalid_dependency.json')

    def test_build_target(self):
        test_cases = [
            ('ch01/A', {'ch01/A'}),
            ('ch02/C', {'ch01/A', 'ch01/B', 'ch02/C'}),
            ('ch03/E', {'ch01/A', 'ch01/B', 'ch02/C', 'ch02/D', 'ch03/E'})
        ]
        for target_name, built in test_cases:
            for target in self.target_manager.iter_targets():
                target.build.reset_mock()
            self.target_manager.build_target(target_name)
            for target in self.target_manager.iter_targets():
                if target.name in built:
                    target.build.assert_called_once()
                else:
                    target.build.assert_not_called()

    def test_build_target_not_exist(self):
        self.assertRaisesRegex(ValueError, 'Target "ch03/F" not found', self.target_manager.build_target, 'ch03/F')

    def test_build_target_with_circular_dependency(self):
        target_manager = TargetManager(self.testdata_dir / 'targets_circular_dependency.json')
        self.assertRaisesRegex(
            RuntimeError, 'Circular dependency detected: ch03/C -> ch02/B -> ch01/A -> ch03/C',
            target_manager.build_target, 'ch03/C')

    def test_run_target(self):
        target_a = self.target_manager['ch01/A']
        self.target_manager.run_target('ch01/A', ['arg1', 'arg2'])
        target_a.build.assert_called_once()
        target_a.run.assert_called_once_with(['arg1', 'arg2'])

    def test_test_target(self):
        target_a = self.target_manager['ch01/A']
        self.target_manager.test_target(
            'ch01/A', ['arg1', 'arg2'], 'A_input.txt', ['-Dfile.encoding=UTF-8'],
            ['data/a.txt'])
        target_a.build.assert_called_once()
        target_a.test.assert_called_once_with(
            ['arg1', 'arg2'], 'A_input.txt', ['-Dfile.encoding=UTF-8'], ['data/a.txt'])
