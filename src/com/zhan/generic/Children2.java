package com.zhan.generic;

/**
 * 使用泛型定义：定义泛型上限
 * T:表示所有类型
 */
public class Children2<T extends Number> {
    private String name;
    private T age;

    public Children2() {
    }

    public Children2(String name, T age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public T getAge() { return age; }

    public void setAge(T age) { this.age = age; }

    @Override
    public String toString() { return "Children{" + "name='" + name + '\'' + ", age=" + age + '}';  }
}
