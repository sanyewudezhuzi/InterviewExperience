# Java



# 一、Java基础

## 1. Java基本数据类型

|        | 数据类型    | 字节数 | 位数 |
| ------ | ----------- | ------ | ---- |
| 整形   | byte        | 1      | 8    |
|        | short       | 2      | 16   |
|        | **int**     | 4      | 32   |
|        | long        | 8      | 64   |
| 浮点型 | float       | 4      | 32   |
|        | **double**  | 8      | 64   |
| 布尔型 | **boolean** | 1      | 8    |
| 字符型 | **char**    | 2      | 16   |



## 2. 方法重载与方法重写

>   **重载**：发生在同一个类中，方法名必须相同，参数列表不同，与返回值无关
>
>   **重写**：发生在父子类中，方法名、参数列表必须相同，返回值范围小于等于父类，抛出的异常范围小于等于父类，访问修饰符大于等于父类



## 3. ThreadLocal

>   *   ThreadLocal介绍
>
>       *   ThreadLocal是Thread的局部变量
>       *   ThreadLocal为每个线程提供单独一份存储空间
>       *   ThreadLocal存储的数据可以在一个线程内传递，不同线程是相互隔离的
>       *   底层是一个Map存储，每个ThreadLocal使用Entry存储数据，ThreadLocal对象本身作为key，数据作为value，值得注意的是**key是弱引用**
>
>   *   ThreadLocal应用场景
>
>       *   在一个线程内传递数据，例如传递登录数据
>
>       *   `@Transactional`注解，里面线程共享存储了数据库连接connection对线、事务传播行为等
>
>           *   回顾JDBC操作数据库的过程：
>
>               ```java
>               // 获取数据库连接对线：connection
>               connection.setAutoCommit(false); // 关闭自动提交事务
>               PrepareStatement pstmt = connection.preparedStatement(sql); // 获取数据库执行命令对象
>               pstmt.execute(); // 执行sql语句
>               // 释放资源：ResultSet、pstmt、connection
>               connection.commit(); // 没有异常则提交
>               connection.rollback(); // 有异常则回滚
>               ```
>
>           *   ![image-20240828144155305](img/image-20240828144155305.png)
>
>       *   注入HttpServletRequest对线和HttpServletResponse都使用的是ThreadLocal存取
>
>       *   使用mybatis框架时，pageHelper分页插件里面Page数据使用ThreadLocal存储
>
>   *   ThreadLocal的问题
>
>       *   当前线程使用完成后ThreadLocal存储的数据没有清理就放回线程池，后面再次取出继续使用的时候存储的数据就暴露了，这就是内存泄漏
>       *   解决方案：当前线程使用完成后要清理内存数据



## 4. 权限修饰符

>   1.   `public`：公开的，权限最大的修饰符，可以在任何一个类中任意使用
>   2.   `protected`：受保护的，不能修饰外部类，只能被同包下的其他类或子类访问
>   3.   `default`：默认的，即不写任何关键字，只能被同包下的其他类访问
>   4.   `private`：私有的，不能修饰外部类，不能被其他包访问
>
>   <img src="img/image-20240828145855995.png" alt="image-20240828145855995" style="zoom:50%;" />



## 5. 泛型的作用\<?\>和\<T\>的使用场景和区别

>   *   \<T\>：声明一个泛型类或者泛型方法，代表的是某一种具体的数据类型
>   *   \<?\>：使用泛型类或者泛型方法，代表的是泛指所有的对象类型

