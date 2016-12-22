package com.zhan.thinking_in_java.second_chapter_practice;

/**
 *  Java 编程思想 第二章课后习题
 *  Created by zhan on 2016/12/22.
 */
public class SecondChapterPractice {

    public static void main(String args[]){
        System.out.println("---------------------------- practice 1 ------------------------------");
        Praceice1 p1 = new Praceice1();
        System.out.println("practice1------p1int----> "+p1.p1int);
        System.out.println("practice1------p1char----> "+p1.p1char);
        System.out.println("int 默认值为"+ p1.p1int);
        if (p1.p1char == '\u0000'){
            System.out.println("char 默认值为"+"\\"+"u0000");
        }

        System.out.println("---------------------------- practice 2 ------------------------------");



    }

    /**
     * 练习1：创建一个类，它包含一个int域和一个char域，它们都没有被初始化，
     * 将它们的值打印出来，已验证Java执行了默认初始化
     */
    static class Praceice1{
        int p1int;
        char p1char;
    }
}
