package com.zhan.thinking_in_java.eleven_chapter_practice;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 *  ListIterator 使用测试
 *  Created by zhan on 2017/8/10.
 */
public class ListIteration {

    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("a1");
        list.add("a2");
        list.add("a3");

        ListIterator iterator = list.listIterator(1);
        while (iterator.hasNext()){
            String item = (String) iterator.next();
            System.out.println(item);
        }
        Stack stack = new Stack();
        stack.push("h1");
        stack.push("h2");
        System.out.println(stack.toString());
    }
}
