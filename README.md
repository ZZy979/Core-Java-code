# Core-Java-code
《Java核心技术》(Core Java)（第12版）书中代码
* 本书网站：<https://horstmann.com/corejava/>
* 源代码：<https://horstmann.com/corejava/corejava.zip>
* 笔记：<https://zzy979.github.io/posts/java-note-index/>

# 依赖
* JDK 17
* Python 3

# 运行方式
方式一：在命令行中编译和运行。例如：

```shell
cd v1ch02/Welcome
javac Welcome.java
java Welcome
```

方式二：使用工具脚本corejava.py。用法：

```shell
python corejava.py run {chapter}/{target}
```

其中，chapter为章节名称（例如v1ch02），target为示例程序名称，格式为`[subdir/][package.]classname`（例如Welcome/Welcome）。例如：

```shell
python corejava.py run v1ch02/Welcome/Welcome
```

# 单元测试
在项目根目录下执行：

```shell
python corejava.py test
```

# 代码目录 卷I
## 第2章
* [程序清单2-1 Welcome/Welcome.java](v1ch02/Welcome/Welcome.java)
* [程序清单2-2 ImageViewer/ImageViewer.java](v1ch02/ImageViewer/ImageViewer.java)
