package com.zhan.generic;

import java.util.Vector;

/**
 *  泛型
 *  Created by zhan on 2016/12/18.
 */
public class GenericDemo {

    public static void main(String args[]){
        Children<Integer> c1=new Children<Integer>("小小白",3);
        Children<Float> c2 = new Children<Float>("小小黑",1.6f);
        Children<String> c3 = new Children<String>("小小黄","二");

        System.out.println(c1.getClass()==c2.getClass());

        Vector vector=new Vector<String>();
        Vector<Object> dd = vector;

        int age1 = c1.getAge();
        System.out.println(c1.getName()+"的年龄是："+age1);

        Float age2 = (Float) c2.getAge();
        System.out.println(c2.getName()+"的年龄是："+age2);

        String age3 = (String) c3.getAge();
        System.out.println(c3.getName()+"的年龄是："+age3);

        print(c1);
        print(c2);

        //使用泛型上限
        Children2<Integer>  c9 = new Children2();//正确
        //Children2<String> c10 = new Children2<>();//错误

        //使用泛型下限
        fun(c1);
        fun(new Children<Number>());//正确
        fun(new Children<Object>());//正确
        //fun(c2);//错误，c2为float

        //使用泛型方法
        String[] names = {"aa","bb","cc"};
        names=func(names,0,2);
        for (String str : names) {
            System.out.println(str);
        }
    }

    /**
     * 定义泛型下限
     * 表示只能传 Integer 或 Integer 的父类，如Number，Object
     */
    public static void fun(Children<? super Integer> c){
        System.out.println(c.getAge());
    }

    /**
     * 使用通配符？定义参数
     * 使用通配符可以引用其他各种参数化类型，通配符定义的变量主要用作引用，
     * 可以调用与参数无关的方法（如 get），不能调用与参数有关的方法（如set）
     * @param c
     */
    public static void print(Children<?> c){
        //c.setAge(10);//使用通配符接收，只能输出，不能修改
        System.out.println("我的年龄是："+c.getAge());
    }

    /**
     * 定义泛型方法
     * <T> 置于修饰符和返回值之间
     */
    public static<T> T[] func(T[] array,int i,int t){
        T temp = array[i];
        array[i]=array[t];
        array[t]=temp;
        return array;
    }


}
