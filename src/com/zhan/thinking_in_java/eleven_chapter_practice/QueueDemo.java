package com.zhan.thinking_in_java.eleven_chapter_practice;

import java.util.*;

/**
 *  Queue 队列 测试
 *  Created by zhan on 2017/8/19.
 */
public class QueueDemo {

    public static  void main(String[] args){
        Queue queue = new LinkedList<Integer>();
        Random random = new Random(47);
        for (int i = 0;i<10;i++){
            queue.offer(random.nextInt(i + 10));
        }
        printQ(queue);

    }

    public static  void printQ(Queue queue){
        while (queue.peek() != null){
            System.out.print(queue.remove() + "");
        }
        System.out.println();
    }

}
