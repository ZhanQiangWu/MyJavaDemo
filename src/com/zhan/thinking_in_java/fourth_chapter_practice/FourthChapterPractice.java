package com.zhan.thinking_in_java.fourth_chapter_practice;

/**
 *  Java 编程思想 第四章 标签
 *  Created by zhan on 2016/12/22.
 */
public class FourthChapterPractice {

    public static void main(String args[]){

        int i = 0;
        outer: //Can not have statement here
        for (;true;){
            System.out.println("dfd ------------");
            inner: //Can not have statement here
            for (;i<10;i++){
                System.out.println("i = "+ i);
                if (i == 2){
                    System.out.println("continue");
                    continue ;
                }

                if (i == 3){
                    System.out.println("break");
                    i++;
                    break ;
                }

                if (i == 7){
                    System.out.println("continue outer");
                    i++;
                    continue outer;
                }

                if (i == 8){
                    System.out.println("break outer");
                    i++;
                    break outer;
                }

                for (int k=0; k < 5 ; k++){
                    if (k == 3){
                        System.out.println("continue inner");
                        continue inner;
                    }
                }
            }
        }
    }

}
