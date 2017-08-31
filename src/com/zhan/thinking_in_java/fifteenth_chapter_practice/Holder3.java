package com.zhan.thinking_in_java.fifteenth_chapter_practice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *  泛型测试
 *  Created by zhan on 2017/8/27.
 */
public class Holder3<T> {
    private T a;

    public Holder3(T t){
        this.a = t;
    }

    public void setA(T a) {
        this.a = a;
    }

    public T getA() {
        return a;
    }

    public static void main(String[] args){
        Holder3<String> h3 = new Holder3("hello world");
        System.out.println(h3.getA());
        Map<String,List<String>> map = map();
    }

    public static <K,V> Map<K,V> map(){
        return new HashMap<K,V>();
    }
}
