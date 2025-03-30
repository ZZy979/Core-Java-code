import os
import unittest
from unittest.mock import Mock

from corejava.config import ROOT_DIR, OUT_DIR
from corejava.target import Target, TargetManager


class TargetTest(unittest.TestCase):

    def setUp(self):
        self.foo = Target('ch01', 'Foo/com.example.foo.Foo')
        self.bar = Target('ch02', 'com.example.bar.Bar', ['ch01/Foo/com.example.foo.Foo'])

    def test_init(self):
        self.assertEqual('ch01', self.foo.chapter)
        self.assertEqual('ch01/Foo/com.example.foo.Foo', self.foo.name)
        self.assertEqual('com.example.foo.Foo', self.foo.main_class)
        self.assertEqual(ROOT_DIR / 'ch01/Foo', self.foo.src_dir)
        self.assertEqual(ROOT_DIR / 'ch01/Foo/com/example/foo/Foo.java', self.foo.src_file)
        self.assertEqual(OUT_DIR / 'ch01/Foo', self.foo.out_dir)
        self.assertListEqual([], self.foo.deps)

        self.assertEqual(ROOT_DIR / 'ch02', self.bar.src_dir)
        self.assertEqual(OUT_DIR / 'ch02', self.bar.out_dir)
        self.assertListEqual(['ch01/Foo/com.example.foo.Foo'], self.bar.deps)

    def test_get_classpath(self):
        self.assertListEqual(['.'], self.foo.get_classpath())
        self.assertListEqual(['.', str(OUT_DIR / 'ch01/Foo')], self.bar.get_classpath())

    def test_get_module_path(self):
        self.assertListEqual([], self.foo.get_module_path(False))
        self.assertListEqual([str(OUT_DIR / 'ch01/Foo')], self.foo.get_module_path(True))
        self.assertListEqual([str(OUT_DIR / 'ch01/Foo')], self.bar.get_module_path(False))
        self.assertListEqual([str(OUT_DIR / 'ch01/Foo'), str(OUT_DIR / 'ch02')], self.bar.get_module_path(True))

    def test_get_compile_command(self):
        foo_compile_command = [
            'javac', '-d', OUT_DIR / 'ch01/Foo', '-cp', '.',
            ROOT_DIR / 'ch01/Foo/com/example/foo/Foo.java'
        ]
        self.assertListEqual(foo_compile_command, self.foo.get_compile_command())

        bar_compile_command = [
            'javac', '-d', OUT_DIR / 'ch02', '-cp', os.pathsep.join(['.', str(OUT_DIR / 'ch01/Foo')]),
            ROOT_DIR / 'ch02/com/example/bar/Bar.java'
        ]
        self.assertListEqual(bar_compile_command, self.bar.get_compile_command())

    def test_get_compile_command_for_module(self):
        self.foo.is_module = self.bar.is_module = True

        foo_compile_command = [
            'javac', '-d', OUT_DIR / 'ch01/Foo',
            ROOT_DIR / 'ch01/Foo/module-info.java', ROOT_DIR / 'ch01/Foo/com/example/foo/Foo.java'
        ]
        self.assertListEqual(foo_compile_command, self.foo.get_compile_command())

        bar_compile_command = [
            'javac', '-d', OUT_DIR / 'ch02', '-p', str(OUT_DIR / 'ch01/Foo'),
            ROOT_DIR / 'ch02/module-info.java', ROOT_DIR / 'ch02/com/example/bar/Bar.java'
        ]
        self.assertListEqual(bar_compile_command, self.bar.get_compile_command())

    def test_get_run_command(self):
        foo_run_command = ['java', '-cp', '.', 'com.example.foo.Foo']
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
            'java', '-p', os.pathsep.join([str(OUT_DIR / 'ch01/Foo'), str(OUT_DIR / 'ch02')]),
            '-Dfile.encoding=UTF-8', '-m', 'mod2/com.example.bar.Bar', 'arg1', 'arg2'
        ]
        self.assertListEqual(
            bar_run_command, self.bar.get_run_command(['arg1', 'arg2'], ['-Dfile.encoding=UTF-8']))


class TargetManagerTest(unittest.TestCase):

    def setUp(self):
        self.testdata_dir = ROOT_DIR / 'corejava/testdata'
        self.target_manager = TargetManager(self.testdata_dir / 'targets.json')
        for target in self.target_manager.iter_targets():
            target.build = Mock()
            target.run = Mock()

    def test_load_targets(self):
        targets = [
            ("ch01/A", [], None),
            ("ch01/B", [], None),
            ("ch02/C", ["ch01/A", "ch01/B"], None),
            ("ch02/D", ["ch01/B"], None),
            ("ch03/E", ["ch02/C", "ch02/D"], None),
            ("ch04/mod1/F", [], "mod1"),
            ("ch04/mod2/G", ["ch04/mod1/F"], "mod2"),
        ]
        self.assertEqual(len(targets), len(self.target_manager.targets))
        for name, deps, module_name in targets:
            self.assertIn(name, self.target_manager)
            target = self.target_manager[name]
            self.assertEqual(name, target.name)
            self.assertListEqual(deps, target.deps)
            self.assertEqual(module_name, target.module_name)

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
