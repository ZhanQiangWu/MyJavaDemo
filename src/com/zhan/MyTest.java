package com.zhan;

import java.util.ArrayList;

/**
 *  Created by zhan on 2016/12/27.
 */
public class MyTest {

    static void printArray(Object...args){
        for (Object obj:args){
            System.out.println(obj+" ");
        }
    }

    public static void main(String args[]){
        printArray();

    }
}

