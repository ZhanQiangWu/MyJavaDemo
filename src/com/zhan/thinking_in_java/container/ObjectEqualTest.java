package com.zhan.thinking_in_java.container;

import java.util.Collection;
import java.util.HashSet;

/**
 *  容器移除对象测试
 *  自定义对象需要重写 hashCode方法
 *  Created by zhan on 2017/7/17.
 */
public class ObjectEqualTest {
    public static void main(String[] args){
        Collection c = new HashSet<>();
        c.add(new Name("f1","f2"));
        System.out.println(c.remove(new Name("f1","f2")));
    }
}


class Name{
    private String firstName,lastName;
    public Name(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean equals(Object obj){
        if (obj instanceof Name){
            Name name = (Name) obj;
            return (firstName.equals(name.firstName))&&(lastName.equals(name.lastName));
        }
        return super.equals(obj);
    }

    public int hashCode(){
        return firstName.hashCode()+lastName.hashCode();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}