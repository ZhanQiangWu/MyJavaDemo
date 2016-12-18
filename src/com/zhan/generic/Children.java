package com.zhan.generic;

/**
 * 使用泛型
 * T:表示所有类型
 */
public class Children<T> {
    private String name;
    private T age;

    public Children() {
    }

    public Children(String name, T age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getAge() {
        return age;
    }

    public void setAge(T age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Children{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
