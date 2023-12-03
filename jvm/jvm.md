# JVM



1.   JVM的好处

>   1.   一次编写，到处运行
>   2.   自动内存管理，垃圾回收功能
>   3.   数组下标越界检查
>   4.   jvm内部使用了虚方法表机制实现多态



2.   JVM、JRE、JDK

>   JVM：Java Virtual Mechinal，Java虚拟机，用于执行编译后的Java文件，也就是class字节码文件，也是Java实现跨平台的核心。JVM是面向系统的，不是面向硬件的。
>
>   JRE：Java Runtime Environment，Java运行环境，用于运行Java程序，由JVM和核心内库组成。
>
>   JDK：Java Development kit，Java开发工具包，用于程序员开发，由JRE和开发工具组成。
>
>   程序运行只需要JRE，程序员开发需要JDK。