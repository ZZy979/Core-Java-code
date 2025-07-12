import json
import os
import shutil
import subprocess
from subprocess import DEVNULL, PIPE

from corejava.config import ROOT_DIR, OUT_DIR


class Target:

    def __init__(
            self, chapter, name, module_name=None, srcs=None, deps=None,
            class_path=None, module_path=None, resources=None):
        """书中的示例程序（构建目标）。

        属性：

        - chapter：章节名称，例如v1ch02
        - name：构建目标名称，例如v1ch02/Foo/com.example.foo.Foo
        - module_name：Java模块名
        - is_module：是否是Java模块
        - main_class：主类名，例如com.example.foo.Foo
        - src_dir：源文件目录，例如ROOT_DIR/v1ch02/Foo
        - out_dir：类文件输出目录，例如OUT_DIR/v1ch02/Foo
        - src_file：主类源文件，例如com/example/foo/Foo.java
        - srcs：源文件列表
        - deps：依赖的目标列表，例如v1ch03/Bar/com.example.bar.Bar
        - class_path：额外的类路径列表
        - module_path：额外的模块路径列表
        - resources: 资源文件列表，例如data/foo.txt

        :param chapter: str 章节名称
        :param name: str 构建目标名称，格式为[subdir/][package.]classname
        :param module_name: str Java模块名（可选）
        :param srcs: List[str] 源文件列表（可选），路径相对于源文件路径(src_dir)，如果未指定则使用主类源文件(src_file)
        :param deps: List[str] 依赖的目标列表（可选），格式为chapter/target_name
        :param class_path: List[str] 额外的类路径列表（可选），路径相对于ROOT_DIR
        :param module_path: List[str] 额外的模块路径列表（可选），路径相对于ROOT_DIR
        :param resources: List[str] 资源文件列表（可选），路径相对于源文件路径(src_dir)
        """
        self.chapter = chapter
        self.name = f'{chapter}/{name}'
        self.module_name = module_name
        self.is_module = module_name is not None

        subdir, self.main_class = self.name.rsplit('/', 1)
        self.src_dir = ROOT_DIR / subdir
        self.out_dir = OUT_DIR / subdir
        self.src_file = self.main_class.replace('.', '/') + '.java'
        self.srcs = srcs or [self.src_file]
        self.deps = deps or []
        self.class_path = [ROOT_DIR / p for p in class_path] if class_path else []
        self.module_path = [ROOT_DIR / p for p in module_path] if module_path else []
        self.resources = resources or []

    def __str__(self):
        return self.name

    def _get_out_dir(self, name):
        # 用于获取依赖目标的输出目录，而无需构造Target对象
        return OUT_DIR / name.rsplit('/', 1)[0]

    def _get_dep_dirs(self, include_current_dir=False, include_self=False):
        # 获取所有依赖的输出目录，用作类路径或模块路径
        dirs = set(str(self._get_out_dir(dep)) for dep in self.deps)
        if include_current_dir:
            dirs.add('.')
        if include_self:
            dirs.add(str(self.out_dir))
        return sorted(dirs)

    def _get_extra_class_path(self):
        # 返回额外的类路径列表
        return [str(p) for p in self.class_path]

    def _get_extra_module_path(self):
        # 返回额外的模块路径列表
        return [str(p) for p in self.module_path]

    def get_class_path(self):
        """返回类路径列表：当前目录和所有直接依赖的输出目录。"""
        return self._get_dep_dirs(include_current_dir=True) + self._get_extra_class_path()

    def get_module_path(self, for_run):
        """返回模块路径列表。"""
        return self._get_dep_dirs(include_self=for_run) + self._get_extra_module_path()

    def get_dep_option(self, for_run):
        """返回指定依赖路径的选项，for_run为False表示用于编译命令，否则用于运行命令。"""
        if self.is_module:
            module_path = self.get_module_path(for_run)
            return ['-p', os.pathsep.join(module_path)] if module_path else []
        else:
            class_path = self.get_class_path()
            return ['-cp', os.pathsep.join(class_path)] if class_path else []

    def get_src_files(self):
        """返回要编译的源文件列表。"""
        if self.is_module:
            return ['module-info.java'] + self.srcs
        else:
            return self.srcs

    def get_run_target_option(self):
        """返回指定运行目标的选项。"""
        if self.is_module:
            return ['-m', f'{self.module_name}/{self.main_class}']
        else:
            return [self.main_class]

    def get_compile_command(self):
        """生成编译命令。"""
        return [
            'javac',
            '-d', self.out_dir,
            *self.get_dep_option(False),
            *self.get_src_files()
        ]

    def get_run_command(self, args=None, jvm_options=None):
        """生成运行命令。"""
        return [
            'java',
            *self.get_dep_option(True),
            *(jvm_options or []),
            *self.get_run_target_option(),
            *(args or [])
        ]

    def copy_resources(self, resources=None):
        """将资源文件拷贝到输出目录。"""
        resources = resources if resources is not None else self.resources
        for res in resources:
            src = self.src_dir / res
            dst = self.out_dir / res
            os.makedirs(dst.parent, exist_ok=True)
            shutil.copy(src, dst)

    def compile(self):
        """编译示例程序。"""
        cmd = self.get_compile_command()
        subprocess.run(cmd, cwd=self.src_dir, check=True)

    def build(self):
        self.compile()
        self.copy_resources()

    def run(self, args=None, jvm_options=None):
        """运行示例程序。

        :param args: List[str] 命令行参数
        :param jvm_options: List[str] JVM选项
        """
        cmd = self.get_run_command(args, jvm_options)
        subprocess.run(cmd, cwd=self.out_dir)

    def test(self, args=None, input_file=None, jvm_options=None, testdata=None):
        """测试示例程序。

        :param args: List[str] 命令行参数
        :param input_file: str 输入文件名
        :param jvm_options: List[str] JVM选项
        :param testdata: List[str] 测试数据
        :return: subprocess.CompletedProcess对象
        """
        self.copy_resources(testdata or [])
        cmd = self.get_run_command(args, jvm_options)
        stdin = open(input_file, encoding='utf-8') if input_file else DEVNULL
        result = subprocess.run(cmd, cwd=self.out_dir, stdin=stdin, stdout=PIPE, text=True, encoding='utf-8')
        if input_file:
            stdin.close()
        return result

    def clean(self):
        """清理编译输出。"""
        shutil.rmtree(self.out_dir)


class TargetManager:

    def __init__(self, config_file):
        """构建目标管理器。

        配置文件为JSON格式。顶层是一个JSON对象，key为章节名称，value为构建目标数组。例如：

        {"ch01": [...], "ch02": [...]}

        每个构建目标也是一个JSON对象，支持下列的key：

        - name：构建目标名称，格式同Target.name
        - module_name：Java模块名（可选），如果指定了则编译时自动将module-info.java添加到源文件列表
        - srcs：源文件列表（可选），路径相对于源文件路径(Target.src_dir)，如果未指定则使用主类源文件(Target.src_file)
        - deps：依赖的目标列表（可选），格式同Target.deps
        - class_path：额外的类路径列表（可选），路径相对于ROOT_DIR
        - module_path：额外的模块路径列表（可选），路径相对于ROOT_DIR
        - resources：资源文件列表（可选），路径相对于源文件路径(Target.src_dir)

        :param config_file: str 构建目标配置文件
        """
        self.config_file = config_file
        self.targets = self.load_targets()

    def load_targets(self):
        with open(self.config_file, encoding='utf-8') as f:
            target_config = json.load(f)

        targets = {}  # name -> Target
        for chapter, configs in target_config.items():
            for config in configs:
                if isinstance(config, str):
                    config = {'name': config}
                name = config['name']
                module_name = config.get('module_name')
                srcs = config.get('srcs', [])
                deps = config.get('deps', [])
                class_path = config.get('class_path', [])
                module_path = config.get('module_path', [])
                resources = config.get('resources', [])
                target = Target(chapter, name, module_name, srcs, deps, class_path, module_path, resources)
                targets[target.name] = target

        # 验证依赖目标存在
        for target in targets.values():
            for dep in target.deps:
                if dep not in targets:
                    raise ValueError(f'Dependency "{dep}" of target "{target}" does not exits.')
        return targets

    def __contains__(self, name):
        return name in self.targets

    def __getitem__(self, name):
        return self.targets[name]

    def iter_targets(self):
        return iter(self.targets.values())

    def _build_target_recursive(self, target_name, stack, built):
        if target_name in stack:
            # 循环依赖
            circle = ' -> '.join(stack + [target_name])
            raise RuntimeError(f'Circular dependency detected: {circle}')

        if target_name not in self.targets:
            raise ValueError(f'Target "{target_name}" not found.')
        if target_name in built:
            return

        target = self.targets[target_name]
        stack.append(target_name)

        for dep in target.deps:
            self._build_target_recursive(dep, stack, built)

        target.build()
        built.add(target_name)
        stack.pop()

    def build_target(self, target_name):
        self._build_target_recursive(target_name, [], set())

    def run_target(self, target_name, args=None):
        self.build_target(target_name)
        self.targets[target_name].run(args)

    def test_target(self, target_name, args=None, input_file=None, jvm_options=None, testdata=None):
        self.build_target(target_name)
        return self.targets[target_name].test(args, input_file, jvm_options, testdata)
