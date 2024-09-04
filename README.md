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

# 代码目录
## 卷I 基础知识
### 第2章 Java编程环境
* [程序清单2-1 Welcome/Welcome.java](v1ch02/Welcome/Welcome.java)
* [程序清单2-2 ImageViewer/ImageViewer.java](v1ch02/ImageViewer/ImageViewer.java)

### 第3章 Java的基本编程结构
* [程序清单3-1 FirstSample/FirstSample.java](v1ch03/FirstSample/FirstSample.java)
* [Constants/Constants.java](v1ch03/Constants/Constants.java)
* [Constants/Constants2.java](v1ch03/Constants/Constants2.java)
* [程序清单3-2 InputTest/InputTest.java](v1ch03/InputTest/InputTest.java)
* [程序清单3-3 Retirement/Retirement.java](v1ch03/Retirement/Retirement.java)
* [程序清单3-4 Retirement2/Retirement2.java](v1ch03/Retirement2/Retirement2.java)
* [程序清单3-5 LotteryOdds/LotteryOdds.java](v1ch03/LotteryOdds/LotteryOdds.java)
* [程序清单3-6 BigIntegerTest/BigIntegerTest.java](v1ch03/BigIntegerTest/BigIntegerTest.java)
* [Message/Message.java](v1ch03/Message/Message.java)
* [程序清单3-7 LotteryDrawing/LotteryDrawing.java](v1ch03/LotteryDrawing/LotteryDrawing.java)
* [程序清单3-8 CompoundInterest/CompoundInterest.java](v1ch03/CompoundInterest/CompoundInterest.java)
* [程序清单3-9 LotteryArray/LotteryArray.java](v1ch03/LotteryArray/LotteryArray.java)

### 第4章 对象和类
* [程序清单4-1 CalendarTest/CalendarTest.java](v1ch04/CalendarTest/CalendarTest.java)
* [程序清单4-2 EmployeeTest/EmployeeTest.java](v1ch04/EmployeeTest/EmployeeTest.java)
* [程序清单4-3 StaticTest/StaticTest.java](v1ch04/StaticTest/StaticTest.java)
* [程序清单4-4 ParamTest/ParamTest.java](v1ch04/ParamTest/ParamTest.java)
* [程序清单4-5 ConstructorTest/ConstructorTest.java](v1ch04/ConstructorTest/ConstructorTest.java)
* [程序清单4-6 RecordTest/RecordTest.java](v1ch04/RecordTest/RecordTest.java)
* [程序清单4-7 PackageTest/PackageTest.java](v1ch04/PackageTest/PackageTest.java)
* [程序清单4-8 PackageTest/com/horstmann/corejava/Employee.java](v1ch04/PackageTest/com/horstmann/corejava/Employee.java)
