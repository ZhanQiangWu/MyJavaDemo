package com.zhan.thinking_in_java.threaddemo;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *  java使用LinkedBlockingQueue实现 生产者 消费者模式
 *  参考：http://blog.csdn.net/hpccn/article/details/17580235
 *
 *  java 生产者消费者问题 并发问题的解决
 *  http://www.cnblogs.com/0201zcr/p/4758533.html
 *  Created by zhan on 2017/10/18.
 */
public class LinkedBlockingQueueTest {

    public static void main(String [] args){
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 6; i++) {
            service.submit(new Consumer(queue, "X二代" + i));
            service.submit(new Consumer(queue, "导演" + i));
        }

        for (int i = 0; i < 6; i++) {
            service.submit(new Producer(queue, "黄金酒" + i));
            service.submit(new Producer(queue, "美女演员" + i));
        }

        service.shutdown();
    }

    public static class Producer implements Runnable{

        private BlockingQueue<String> queue;
        private String produce;

        public Producer(BlockingQueue<String> queue, String produce) {
            this.queue = queue;
            if (null != produce)
                this.produce = produce;
            else this.produce = "null ";
        }

        @Override
        public void run() {
            String uuid = UUID.randomUUID().toString();

            try {
                Thread.sleep(200);//生产需要时间
                queue.put(produce + " : " + uuid);
                System.out.println("Produce \"" + produce + "\" : " + uuid + " " + Thread.currentThread());

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static class Consumer implements Runnable {
        private BlockingQueue<String> queue;
        private String consumer;

        public Consumer(BlockingQueue<String> queue, String consumer) {
            this.queue = queue;
            if (null != consumer)
                this.consumer = consumer;
            else
                this.consumer = "null ";
        }

        @Override
        public void run() {
            try {
                String uuid = queue.take();
                System.out.println("消费者 " + consumer + " decayed " + uuid
                        + " " + Thread.currentThread());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
