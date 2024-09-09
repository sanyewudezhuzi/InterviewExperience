package com.zhuzi.jian_zao_zhe;

/**
 * @Description BuilderModel
 * @Author zhuzi
 * @Date 2024/09/09
 */
public class BuilderModel {

    public static void main(String[] args) {
        try {
            test1();
            test2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test1() throws Exception {
        // 使用构造方法创建对象
        Student student1 = new Student("张三", 18);

        // 使用建造者模式创建对象
        Student student2 = new Student.Builder().name("李四").age(20).build();
    }

    public static void test2() throws Exception {
        // 判断年龄与生日年份
        Student s1 = new Student.Builder().age(22).birthdayYear(2002).build();
        Student s2 = new Student.Builder().age(20).birthdayYear(2001).build();
    }

}
