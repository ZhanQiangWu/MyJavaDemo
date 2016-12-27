package com.zhan;

/**
 *  Created by zhan on 2016/12/27.
 */
public class MyTest {

    public static void main(String args[]){
        Dog dog1 = new Dog("haah");
        Dog dog2 = new Dog("haah");
        System.out.println(dog1 == dog2);
        System.out.println(dog1.equals(dog2));
    }

    static class Dog{
        String name;
        Dog(String name){
            this.name = name;
        }
    }
}
