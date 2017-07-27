package com.zhan.thinking_in_java.container;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhan on 2017/7/18.
 */
public class SetTest {

    public static void main(String[] args){
        List list1 = new ArrayList<Test>();
        list1.add(new Test("aa"));
        list1.add(new Test("bb"));
        list1.add(new Test("vv"));

        List list2 = new ArrayList<Test>();
        list2.add(new Test("aa"));
        list2.add(new Test("bb"));
        list2.add(new Test("dd"));

        List<Test> list = new ArrayList<Test>();
        list.addAll(list1);
        list.removeAll(list2);
//        System.out.println("dddd"+list);
        for (Test test :list){
            System.out.println(test.getT());
        }
    }



    static class Test{
        private String t;
        Test(String test){
            t=test;
        }

        public String getT() {
            return t;
        }
    }
}
