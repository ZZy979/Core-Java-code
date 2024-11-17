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
python corejava.py run v1ch05/arrayList.ArrayListTest
```

# 单元测试
在项目根目录下执行：

```shell
python corejava.py test [chapter...]
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
