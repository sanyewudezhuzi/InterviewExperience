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
>       *   `@Transactional`注解，里面线程共享存储了数据库连接connection对象、事务传播行为等
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







# 二、设计模式

## 1. 策略模式

>   策略模式是一种行为设计模式，它定义了一系列算法，并将每一种算法封装起来，使它们可以相互替换。策略模式让算法的变化独立于使用算法的客户。这种模式涉及到三个主要的角色：
>
>   1.   **策略接口**：定义一个公共接口，所有的算法或行为以这个接口为准。环境角色通过这个接口来调用具体的算法实现。
>   2.   **具体策略类**：实现了策略接口，封装了具体的算法或行为
>   3.   **环境类**：接受客户的请求，随后把请求委托给某一个策略类。环境类会维护对策略对象的引用，并可在运行时动态地改变具体的策略。这个类持有对策略对象的引用，并定义一个接口让策略对象可以访问它的数据。
>
>   **优点**：
>
>   *   算法自由转换：可以在运行时切换对象内部的算法，改变对象的行为
>   *   简化单元测试：每个算法都有自己的类，可以通过自己的接口单独测试
>   *   符合开闭原则：对扩展开放，对修改关闭，增加新的算法时不需要修改环境类代码
>
>   **缺点**：
>
>   *   客户端必须知道所有的策略类，并自行决定使用哪一个策略类
>   *   策略模式会造成有很多的策略类，增加了对象的数目

*实际案例*：目前有很多的业务都具有支付功能，在支付功能中可能会有多种支付的方式，遇到这一类情况的业务时我们就可以采取策略模式。[附代码](./code/src/main/java/com/zhuzi/ce_lue)

*   普通写法

```java
@RestController
@RequestMapping("/celue")
public class PaymentController {

    @Autowired
    private PayService payService;

    /**
     * 普通流程支付
     *
     * @param payType 支付方式
     * @return
     */
    @GetMapping("/pay1")
    public Boolean pay1(@RequestParam String payType) {
        return payService.pay(payType);
    }

}
```

```java
// service.impl类
@Service
public class PayServiceImpl implements PayService {

    // 支付方法
    @Override
    public Boolean pay(String payType) {
        if ("1".equals(payType)) {
            // 微信支付
            return weiXinPay();
        } else if ("2".equals(payType)) {
            // 支付宝支付
            return aliPay();
        }
        return false;
    }

    // 微信支付
    private Boolean weiXinPay() {
        return false;
    }

    // 支付宝支付
    private Boolean aliPay() {
        return false;
    }

}
```

如果我们这时需要增加一个新的支付方式（例如“银联支付”），那么只能在service中修改业务代码，增加一层`else if`，并在下方新增一个银联支付的代码。

*   策略模式

```java
// 策略接口
public interface PayServiceStrategy {

    // 支付策略
    Boolean pay();

}
```

```java
// 具体策略类
public class UnionPayServiceImpl implements PayServiceStrategy {
    @Override
    public Boolean pay() {
        System.out.println("银联支付");
        return false;
    }
}
```

```java
// 定义枚举类
@Getter
public enum PayStrategyEnum {
    // 微信支付
    WEI_XIN_PAY("1", SpringUtils.getBeanByClass(WeixinPayServiceImpl.class)),
    // 支付宝支付
    ALI_PAY("2", SpringUtils.getBeanByClass(AliPayServiceImpl.class)),
    // 银联支付
    UNION_PAY("3", SpringUtils.getBeanByClass(UnionPayServiceImpl.class));

    private String payType;
    private PayServiceStrategy payServiceStrategy;

    PayStrategyEnum(String payType, PayServiceStrategy payServiceStrategy) {
        this.payType = payType;
        this.payServiceStrategy = payServiceStrategy;
    }

    public static PayServiceStrategy getByPayType(String payType) {
        if (StringUtils.isBlank(payType)) {
            return null;
        }
        for (PayStrategyEnum payStrategyEnum : PayStrategyEnum.values()) {
            if (payStrategyEnum.payType.equals(payType)) {
                return payStrategyEnum.getPayServiceStrategy();
            }
        }
        return null;
    }
}
```

```java
// controller层
@RestController
@RequestMapping("/celue")
public class PaymentController {

    @Autowired
    private PayService payService;

    /**
     * 策略模式支付
     *
     * @param payType 支付方式
     * @return
     */
    @GetMapping("/pay2")
    public Boolean pay2(@RequestParam String payType) {
        // 1. 根据payType选择一个Bean对象执行
        PayServiceStrategy payServiceStrategy = PayStrategyEnum.getByPayType(payType);
        if (Objects.isNull(payServiceStrategy)) {
            return false;
        }
        // 2. 调用不同的策略支付
        return payServiceStrategy.pay();
    }

}
```

此时如果需要添加新的支付方式，只需新增新的策略类实现策略接口，并在枚举类中“注册”即可。



## 2. 建造者模式

>   建造者模式是一种创建型设计模式，它的核心思想是将一个复杂对象的构建与表示分离，使得同样的构建过程可以创建不同的表示。
>
>   *   应用场景：
>       1.   当创建复杂对象的算法应该独立于该对象的组成部分以及它们的装配方式时
>       2.   当一个类构造器需要传入很多参数时，使用建造者模式可以避免构造函数的参数过多导致代码可读性差和易出问题

*实际案例：*[附代码](./code/src/main/java/com/zhuzi/jian_zao_zhe)

```java
// Student类
public class Student {
    private String name;
    private Integer age;
    private Integer birthday; // 2002

    public Student() {
    }
    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getBirthday() {
        return birthday;
    }
    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    static class Builder {
        private String name;
        private Integer age;
        private Integer birthdayYear;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(Integer age) throws Exception {
            if (this.birthdayYear != null && !age.equals(LocalDate.now().getYear() - this.birthdayYear)) {
                throw new Exception("年龄与出生年份不匹配");
            }
            this.age = age;
            return this;
        }

        public Builder birthdayYear(Integer birthdayYear) throws Exception {
            if (this.age != null && !birthdayYear.equals(LocalDate.now().getYear() - this.age)) {
                throw new Exception("年龄与出生年份不匹配");
            }
            this.birthdayYear = birthdayYear;
            return this;
        }

        public Student build() {
            return new Student(name, age);
        }
    }

}
```

```java
// 应用场景1
public static void test1() {
    // 使用构造方法创建对象
    Student student1 = new Student("张三", 18);

    // 使用建造者模式创建对象
    Student student2 = new Student.Builder().name("李四").age(20).build();
}
```

```java
// 应用场景2
public static void test2() throws Exception {
    // 判断年龄与生日年份
    Student s1 = new Student.Builder().age(22).birthdayYear(2002).build();
    Student s2 = new Student.Builder().age(20).birthdayYear(2001).build();
}
```



## 3. 观察者模式

>   观察者设计模式是一种行为设计模式，它定义了一种一对多的依赖关系，使得当一个对象（主题或目标）的状态发生改变时，所有依赖于它的对象（观察者）都能够得到通知并自动更新。

*实际案例：*现在有一个需求，在用户注册成功后需要进行一系列的其他操作，例如“赠送100元代金券”、“发送邮箱通知”等，可能过了一段时间，操作又新增了一个“赠送一个月VIP”。[附代码](./code/src/main/java/com/zhuzi/guan_cha_zhe)

*   普通写法

```java
// 注册功能
public Boolean register(String username, String password, String email) {
    // 1. 校验用户名是否重复
    if (checkUserNameExist(username)) {
        throw new RuntimeException("用户名已存在");
    }

    // 2. 新增一条用户数据
    String userId = userDao.addUser(username, password);

    // 3. 注册成功
    if (userId != null && userId.length() > 0) {
        // 3.1 送100元代金券
        voucherDao.addVoucher(userId, "100");

        // 3.2 发送邮件
        String emailMessage = "恭喜你注册成功, 已发放100元代金券, 请前往个人中心领取";
        sendEmailService.sendEmail(email, emailMessage);
        
        // 3.3 其它操作
    }

    return true;
}
```

*   观察者模式

```java
// 观察者接口
public interface UserRegistObserver {

    // 用户注册成功后逻辑
    void handleRegisterSuccess(String userId);

}
```

```java
// 实现接口
public class RegisterEmailObserver implements UserRegistObserver {
    @Override
    public void handleRegisterSuccess(String userId) {
        String emailMessage = "恭喜你注册成功, 已发放100元代金券, 请前往个人中心领取";
        return;
    }
}
```

```java
// 注册接口
private static final List<UserRegistObserver> USER_REGIST_OBSERVERS = new ArrayList<>();

static {
    // 注册观察者
    USER_REGIST_OBSERVERS.add(new RegisterVoucherObserver());
    USER_REGIST_OBSERVERS.add(new RegisterEmailObserver());
}

// 注册功能
public Boolean register(String username, String password, String email) {
    // 1. 校验用户名是否重复
    if (checkUserNameExist(username)) {
        throw new RuntimeException("用户名已存在");
    }

    // 2. 新增一条用户数据
    String userId = userDao.addUser(username, password);

    // 3. 注册成功
    for (UserRegistObserver observer : USER_REGIST_OBSERVERS) {
        observer.handleRegisterSuccess(userId);
    }

    return true;
}
```



## 4. 责任链模式

>   责任链模式是一种行为设计模式，用于处理请求的发送者和接收者之间的解耦问题。在这种模式中，一系列的处理对象（或称为处理器）被连接成一条链，请求沿着这条链传递，直到链中的某个对象能够处理该请求为止。
>
>   责任链模式主要包含以下几种角色：
>
>   *   抽象处理者：定义一个处理请求的接口，并包含一个指向下一个处理者的引用（即链中的下一个节点）
>   *   具体处理者：实现抽象处理者的接口，具体处理请求，并可能将请求传递给链中的下一个处理者
>   *   客户端：创建处理链，并向链的第一个处理者发送请求

*实际案例：*文章发帖或评论发布的时候通常会对文本进行一系列的审核操作，这时我们可以采用责任链模式来优化代码。[附代码](./code/src/main/java/com/zhuzi/ze_ren_lian)

*   普通写法

```java
// 新增文章
public Boolean addArticle(String articleContent) {
    // 检查文章是否包含黄色词语
    if (!filterYellowKeyword(articleContent)) {
        return false;
    }

    // 检查文章是否包含政治敏感词
    if (!filterPoliticsKeyword(articleContent)) {
        return false;
    }

    // 检查文章是否包含宗教敏感词
    if (!filterReligionKeyword(articleContent)) {
        return false;
    }

    // 其它操作...
    return true;
}
```

*   责任链模式

```java
// 定义过滤接口
public interface SensitiveWordFilter {

    Boolean wordFilter(String content);

}
```

```java
// 定义过滤接口实现类
public class YellowSensitiveWordFilter implements SensitiveWordFilter {
    @Override
    public Boolean wordFilter(String content) {
        return true;
    }
}
```

```java
// 定义执行链
@Component
public class SensitiveWordFilterChain {
    private static final List<SensitiveWordFilter> SENSITIVE_WORD_FILTERS = new ArrayList<>();

    @PostConstruct
    public void init() {
        // 检查黄色词语
        SENSITIVE_WORD_FILTERS.add(SpringUtils.getBeanByClass(YellowSensitiveWordFilter.class));
        // 检查政治敏感词
        SENSITIVE_WORD_FILTERS.add(SpringUtils.getBeanByClass(PoliticsSensitiveWordFilter.class));
        // 检查宗教敏感词
        SENSITIVE_WORD_FILTERS.add(SpringUtils.getBeanByClass(ReligionSensitiveWordFilter.class));
    }

    // 过滤敏感词
    public static boolean filterSensitiveWord(String content) {
        for (SensitiveWordFilter wordFilter : SENSITIVE_WORD_FILTERS) {
            if (!wordFilter.wordFilter(content)) {
                return false;
            }
        }
        return true;
    }
}
```

```java
// 新增文章
public Boolean addArticle(String articleContent) {
    boolean sensitiveWordResult = SensitiveWordFilterChain.filterSensitiveWord(articleContent);
    if (!sensitiveWordResult) {
        return false;
    }

    // 其它操作...
    return true;
}
```



