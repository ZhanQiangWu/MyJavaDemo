package com.zhan.thinking_in_java.threaddemo;

import jdk.nashorn.internal.parser.JSONParser;

import static com.zhan.thinking_in_java.threaddemo.newDemo.ArrayOfGenericReference.gia;

/**
 * http://825635381.iteye.com/blog/2184680
 * Created by zhan on 2017/9/11.
 */
public class newDemo {

    public static void main(String args[]){
        gia = (Generic<Integer>[]) new Generic[2];
    }

    static class Generic<T>{

    }

    public static class ArrayOfGenericReference{
        static Generic<Integer>[] gia;
    }
}
