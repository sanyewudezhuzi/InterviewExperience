package com.zhuzi.jian_zao_zhe;

import java.time.LocalDate;

/**
 * @Description Student
 * @Author zhuzi
 * @Date 2024/09/09
 */
public class Student {
    private String name;

    private Integer age;

    private Integer birthdayYear; // 2002

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

    public Integer getbirthdayYear() {
        return birthdayYear;
    }

    public void setbirthdayYear(Integer birthdayYear) {
        this.birthdayYear = birthdayYear;
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
