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

方式二：使用工具脚本。用法：

```shell
python -m corejava run {chapter}/{target}
```

其中，chapter为章节名称（例如v1ch02），target为示例程序名称，格式为`[subdir/][package.]classname`（例如Welcome/Welcome）。例如：

```shell
python -m corejava run v1ch02/Welcome/Welcome
python -m corejava run v1ch05/arrayList.ArrayListTest
```

# 单元测试
在项目根目录下执行：

```shell
python -m corejava test [chapter...]
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

### 第5章 继承
* [程序清单5-1 inheritance/ManagerTest.java](v1ch05/inheritance/ManagerTest.java)
* [程序清单5-2 inheritance/Employee.java](v1ch05/inheritance/Employee.java)
* [程序清单5-3 inheritance/Manager.java](v1ch05/inheritance/Manager.java)
* [程序清单5-4 equals/EqualsTest.java](v1ch05/equals/EqualsTest.java)
* [程序清单5-5 equals/Employee.java](v1ch05/equals/Employee.java)
* [程序清单5-6 equals/Manager.java](v1ch05/equals/Manager.java)
* [程序清单5-7 arrayList/ArrayListTest.java](v1ch05/arrayList/ArrayListTest.java)
* [程序清单5-8 abstractClasses/PersonTest.java](v1ch05/abstractClasses/PersonTest.java)
* [程序清单5-9 abstractClasses/Person.java](v1ch05/abstractClasses/Person.java)
* [程序清单5-10 abstractClasses/Employee.java](v1ch05/abstractClasses/Employee.java)
* [程序清单5-11 abstractClasses/Student.java](v1ch05/abstractClasses/Student.java)
* [程序清单5-12 enums/EnumTest.java](v1ch05/enums/EnumTest.java)
* [程序清单5-13 sealed/SealedTest.java](v1ch05/sealed/SealedTest.java)
* [程序清单5-14 resources/ResourceTest.java](v1ch05/resources/ResourceTest.java)
* [程序清单5-15 reflection/ReflectionTest.java](v1ch05/reflection/ReflectionTest.java)
* [程序清单5-16 objectAnalyzer/ObjectAnalyzerTest.java](v1ch05/objectAnalyzer/ObjectAnalyzerTest.java)
* [程序清单5-17 objectAnalyzer/ObjectAnalyzer.java](v1ch05/objectAnalyzer/ObjectAnalyzer.java)
* [程序清单5-18 arrays/CopyOfTest.java](v1ch05/arrays/CopyOfTest.java)
* [程序清单5-19 methods/MethodTableTest.java](v1ch05/methods/MethodTableTest.java)

### 第6章 接口、Lambda表达式和内部类
* [程序清单6-1 interfaces/EmployeeSortTest.java](v1ch06/interfaces/EmployeeSortTest.java)
* [程序清单6-2 interfaces/Employee.java](v1ch06/interfaces/Employee.java)
* [程序清单6-3 timer/TimerTest.java](v1ch06/timer/TimerTest.java)
* [程序清单6-4 clone/CloneTest.java](v1ch06/clone/CloneTest.java)
* [程序清单6-5 clone/Employee.java](v1ch06/clone/Employee.java)
* [程序清单6-6 lambda/LambdaTest.java](v1ch06/lambda/LambdaTest.java)
* [comparator/ComparatorTest.java](v1ch06/comparator/ComparatorTest.java)
* [程序清单6-7 innerClass/InnerClassTest.java](v1ch06/innerClass/InnerClassTest.java)
* [localInnerClass/LocalInnerClassTest.java](v1ch06/localInnerClass/LocalInnerClassTest.java)
* [程序清单6-8 anonymousInnerClass/AnonymousInnerClassTest.java](v1ch06/anonymousInnerClass/AnonymousInnerClassTest.java)
* [程序清单6-9 staticInnerClass/StaticInnerClassTest.java](v1ch06/staticInnerClass/StaticInnerClassTest.java)
* [serviceLoader/ServiceLoaderTest.java](v1ch06/serviceLoader/ServiceLoaderTest.java)
* [程序清单6-10 proxy/ProxyTest.java](v1ch06/proxy/ProxyTest.java)

### 第7章 异常、断言和日志
* [except/ExceptTest.java](v1ch07/except/ExceptTest.java)
* [程序清单7-1 stackTrace/StackTraceTest.java](v1ch07/stackTrace/StackTraceTest.java)
* [exceptional/ExceptionalTest.java](v1ch07/exceptional/ExceptionalTest.java)
* [assertion/AssertionTest.java](v1ch07/assertion/AssertionTest.java)
* [程序清单7-2 logging/LoggingImageViewer.java](v1ch07/logging/LoggingImageViewer.java)

### 第8章 泛型编程
* [pair/Pair.java](v1ch08/pair/Pair.java)
* [程序清单8-1 pair1/PairTest1.java](v1ch08/pair1/PairTest1.java)
* [程序清单8-2 pair2/PairTest2.java](v1ch08/pair2/PairTest2.java)
* [bridgeMethod/BridgeMethodTest.java](v1ch08/bridgeMethod/BridgeMethodTest.java)
* [genericAlgorithms/Pair.java](v1ch08/genericAlgorithms/Pair.java)
* [limitations/NoGenericArray.java](v1ch08/limitations/NoGenericArray.java)
* [genericAlgorithms/GenericAlgorithms.java](v1ch08/genericAlgorithms/GenericAlgorithms.java)
* [limitations/DefeatCheckedExceptionChecking.java](v1ch08/limitations/DefeatCheckedExceptionChecking.java)
* [程序清单8-3 pair3/PairTest3.java](v1ch08/pair3/PairTest3.java)
* [程序清单8-4 genericReflection/GenericReflectionTest.java](v1ch08/genericReflection/GenericReflectionTest.java)
* [程序清单8-5 genericReflection/TypeLiterals.java](v1ch08/genericReflection/TypeLiterals.java)

### 第9章 集合
* [circularArrayQueue/CircularArrayQueue.java](v1ch09/circularArrayQueue/CircularArrayQueue.java)
* [circularArrayQueue/CircularArrayQueueTest.java](v1ch09/circularArrayQueue/CircularArrayQueueTest.java)
* [程序清单9-1 linkedList/LinkedListTest.java](v1ch09/linkedList/LinkedListTest.java)
* [程序清单9-2 set/SetTest.java](v1ch09/set/SetTest.java)
* [程序清单9-3 treeSet/TreeSetTest.java](v1ch09/treeSet/TreeSetTest.java)
* [程序清单9-4 treeSet/Item.java](v1ch09/treeSet/Item.java)
* [程序清单9-5 priorityQueue/PriorityQueueTest.java](v1ch09/priorityQueue/PriorityQueueTest.java)
* [程序清单9-6 map/MapTest.java](v1ch09/map/MapTest.java)
* [程序清单9-7 shuffle/ShuffleTest.java](v1ch09/shuffle/ShuffleTest.java)
* [properties/ImageViewer.java](v1ch09/properties/ImageViewer.java)
* [程序清单9-8 sieve/Sieve.java](v1ch09/sieve/Sieve.java)
* [程序清单9-9 sieve/sieve.cpp](v1ch09/sieve/sieve.cpp)

### 第10章 图形用户界面
* [程序清单10-1 simpleFrame/SimpleFrameTest.java](v1ch10/simpleFrame/SimpleFrameTest.java)
* [sizedFrame/SizedFrame.java](v1ch10/sizedFrame/SizedFrame.java)
* [程序清单10-2 notHelloWorld/NotHelloWorldComponent.java](v1ch10/notHelloWorld/NotHelloWorldComponent.java)
* [程序清单10-3 draw/DrawComponent.java](v1ch10/draw/DrawComponent.java)
* [fill/FillComponent.java](v1ch10/fill/FillComponent.java)
* [listFonts/ListFonts.java](v1ch10/listFonts/ListFonts.java)
* [程序清单10-4 font/FontComponent.java](v1ch10/font/FontComponent.java)
* [image/ImageComponent.java](v1ch10/image/ImageComponent.java)
* [程序清单10-5 button/ButtonFrame.java](v1ch10/button/ButtonFrame.java)
* [plaf/PlafFrame.java](v1ch10/plaf/PlafFrame.java)
* [action/ActionFrame.java](v1ch10/action/ActionFrame.java)
* [程序清单10-6 mouse/MouseComponent.java](v1ch10/mouse/MouseComponent.java)
* [程序清单10-7 preferences/ImageViewerFrame.java](v1ch10/preferences/ImageViewerFrame.java)

#### Swing示例集合
为方便起见，将第10、11章中所有Swing示例集合打包成一个JAR文件，可以直接通过GUI选择要运行的示例。

代码：[MainFrame.java](swing-demos/MainFrame.java)

打包命令：

```shell
cd swing-demos
bash build_swing_demos.sh
```

运行命令：

```shell
java -jar swing-demos.jar
```

### 第11章 Swing用户界面组件
* [layoutManager/FlowLayoutFrame.java](v1ch11/layoutManager/FlowLayoutFrame.java)
* [layoutManager/BorderLayoutFrame.java](v1ch11/layoutManager/BorderLayoutFrame.java)
* [calculator/CalculatorPanel.java](v1ch11/calculator/CalculatorPanel.java)
* [程序清单11-1 text/TextComponentFrame.java](v1ch11/text/TextComponentFrame.java)
* [程序清单11-2 checkBox/CheckBoxFrame.java](v1ch11/checkBox/CheckBoxFrame.java)
* [程序清单11-3 radioButton/RadioButtonFrame.java](v1ch11/radioButton/RadioButtonFrame.java)
* [border/BorderFrame.java](v1ch11/border/BorderFrame.java)
* [程序清单11-4 comboBox/ComboBoxFrame.java](v1ch11/comboBox/ComboBoxFrame.java)
* [程序清单11-5 slider/SliderFrame.java](v1ch11/slider/SliderFrame.java)
* [程序清单11-6 menu/MenuFrame.java](v1ch11/menu/MenuFrame.java)
* [toolBar/ToolBarFrame.java](v1ch11/toolBar/ToolBarFrame.java)
* [程序清单11-7 gridbag/FontFrame.java](v1ch11/gridbag/FontFrame.java)
* [程序清单11-8 gridbag/GBC.java](v1ch11/gridbag/GBC.java)
* [程序清单11-9 circleLayout/CircleLayout.java](v1ch11/circleLayout/CircleLayout.java)
* [程序清单11-10 circleLayout/CircleLayoutFrame.java](v1ch11/circleLayout/CircleLayoutFrame.java)
* [optionDialog/OptionDialogFrame.java](v1ch11/optionDialog/OptionDialogFrame.java)
* [程序清单11-11 dialog/DialogFrame.java](v1ch11/dialog/DialogFrame.java)
* [程序清单11-12 dialog/AboutDialog.java](v1ch11/dialog/AboutDialog.java)
* [程序清单11-13 dataExchange/DataExchangeFrame.java](v1ch11/dataExchange/DataExchangeFrame.java)
* [程序清单11-14 dataExchange/PasswordChooser.java](v1ch11/dataExchange/PasswordChooser.java)
* [fileChooser/ImageViewerFrame.java](v1ch11/fileChooser/ImageViewerFrame.java)
* [fileChooser/ImagePreviewer.java](v1ch11/fileChooser/ImagePreviewer.java)
* [fileChooser/FileIconView.java](v1ch11/fileChooser/FileIconView.java)
* [colorChooser/ColorChooserPanel.java](v1ch11/colorChooser/ColorChooserPanel.java)
* [eventTracer/EventTracer.java](v1ch11/eventTracer/EventTracer.java)
* [robot/RobotTest.java](v1ch11/robot/RobotTest.java)

### 第12章 并发
* [bounce/Bounce.java](v1ch12/bounce/Bounce.java)
* [bounceThread/BounceThread.java](v1ch12/bounceThread/BounceThread.java)
* [程序清单12-1 threads/ThreadTest.java](v1ch12/threads/ThreadTest.java)
* [程序清单12-2 threads/Bank.java](v1ch12/threads/Bank.java)
* [程序清单12-3 unsynch/UnsynchBankTest.java](v1ch12/unsynch/UnsynchBankTest.java)
* [lock/Bank.java](v1ch12/lock/Bank.java)
* [lock/LockBankTest.java](v1ch12/lock/LockBankTest.java)
* [程序清单12-4 synch/Bank.java](v1ch12/synch/Bank.java)
* [synch/SynchBankTest.java](v1ch12/synch/SynchBankTest.java)
* [程序清单12-5 synch2/Bank.java](v1ch12/synch2/Bank.java)
* [synch2/SynchBankTest2.java](v1ch12/synch2/SynchBankTest2.java)
* [程序清单12-6 blockingQueue/BlockingQueueTest.java](v1ch12/blockingQueue/BlockingQueueTest.java)
* [程序清单12-7 concurrentHashMap/ConcurrentHashMapDemo.java](v1ch12/concurrentHashMap/ConcurrentHashMapDemo.java)
* [future/FutureTest.java](v1ch12/future/FutureTest.java)
* [threadPool/ThreadPoolTest.java](v1ch12/threadPool/ThreadPoolTest.java)
* [程序清单12-8 executors/ExecutorDemo.java](v1ch12/executors/ExecutorDemo.java)
* [程序清单12-9 forkJoin/ForkJoinTest.java](v1ch12/forkJoin/ForkJoinTest.java)
* [程序清单12-10 completableFutures/CompletableFutureDemo.java](v1ch12/completableFutures/CompletableFutureDemo.java)
* [程序清单12-11 swingWorker/SwingWorkerTest.java](v1ch12/swingWorker/SwingWorkerTest.java)
* [process/ReadDir.java](v1ch12/process/ReadDir.java)

## 卷II 高级特性
### 第1章 流
* [程序清单1-1 streams/CountLongWords.java](v2ch01/streams/CountLongWords.java)
* [程序清单1-2 streams/CreatingStreams.java](v2ch01/streams/CreatingStreams.java)
* [程序清单1-3 optional/OptionalTest.java](v2ch01/optional/OptionalTest.java)
* [程序清单1-4 collecting/CollectingResults.java](v2ch01/collecting/CollectingResults.java)
* [程序清单1-5 collecting/CollectingIntoMaps.java](v2ch01/collecting/CollectingIntoMaps.java)
* [程序清单1-6 collecting/DownstreamCollectors.java](v2ch01/collecting/DownstreamCollectors.java)
* [程序清单1-7 streams/PrimitiveTypeStreams.java](v2ch01/streams/PrimitiveTypeStreams.java)
* [程序清单1-8 parallel/ParallelStreams.java](v2ch01/parallel/ParallelStreams.java)

### 第2章 输入和输出
* [程序清单2-1 textFile/TextFileTest.java](v2ch02/textFile/TextFileTest.java)
* [程序清单2-2 randomAccess/RandomAccessTest.java](v2ch02/randomAccess/RandomAccessTest.java)
* [程序清单2-3 serial/ObjectStreamTest.java](v2ch02/serial/ObjectStreamTest.java)
* [serializationTweaks/ObjectStreamTest.java](v2ch02/serializationTweaks/ObjectStreamTest.java)
* [程序清单2-4 serialClone/SerialCloneTest.java](v2ch02/serialClone/SerialCloneTest.java)
* [findDirectories/FindDirectories.java](v2ch02/findDirectories/FindDirectories.java)
* [zip/ZipTest.java](v2ch02/zip/ZipTest.java)
* [程序清单2-5 memoryMap/MemoryMapTest.java](v2ch02/memoryMap/MemoryMapTest.java)
* [randomAccess2/RandomAccessTest.java](v2ch02/randomAccess2/RandomAccessTest.java)
* [程序清单2-6 match/HrefMatch.java](v2ch02/match/HrefMatch.java)
* [程序清单2-7 regex/RegexTest.java](v2ch02/regex/RegexTest.java)

### 第6章 日期和时间API
* [程序清单6-1 timeline/TimeLine.java](v2ch06/timeline/TimeLine.java)
* [程序清单6-2 localdates/LocalDates.java](v2ch06/localdates/LocalDates.java)
* [程序清单6-3 zonedtimes/ZonedTimes.java](v2ch06/zonedtimes/ZonedTimes.java)
* [程序清单6-4 formatting/Formatting.java](v2ch06/formatting/Formatting.java)

### 第9章 Java平台模块系统
* [v2ch09.hellomod](v2ch09/v2ch09.hellomod)
* [v2ch09.requiremod](v2ch09/v2ch09.requiremod)
* [com.horstmann.greet](v2ch09/com.horstmann.greet)
* [v2ch09.exportedpkg](v2ch09/v2ch09.exportedpkg)
* [com.horstmann.util](v2ch09/com.horstmann.util)
* [v2ch09.openpkg](v2ch09/v2ch09.openpkg)
* [v2ch09.openpkg2](v2ch09/v2ch09.openpkg2)
* [v2ch09.automod](v2ch09/v2ch09.automod)
* [com.horstmann.greetsvc](v2ch09/com.horstmann.greetsvc)
* [v2ch09.useservice](v2ch09/v2ch09.useservice)
