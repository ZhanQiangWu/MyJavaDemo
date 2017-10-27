package com.zhan.thinking_in_java.threaddemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * http://www.cnblogs.com/szlbm/p/5588518.html
 * http://blog.csdn.net/column/details/concurrency.html?&page=1
 * Created by zhan on 2017/9/12.
 */
public class BlockingQueueTest {

    public static void main(String[] args){
        BlockingQueue q = new SynchronousQueue<>();
        Pproducer p = new Pproducer(q);
        Cconsumer c1 = new Cconsumer(q);
        Cconsumer c2 = new Cconsumer(q);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }

}




class Pproducer implements Runnable {
    private final BlockingQueue queue;
    Pproducer(BlockingQueue q) { queue = q; }
    public void run() {
        try {
            while (true) { queue.put(produce()); }
        } catch (InterruptedException ex) {
//            ... handle ...
        }
    }

    Object produce() {
//        ...
        return "";
    }
}

class Cconsumer implements Runnable {
    private final BlockingQueue queue;
    Cconsumer(BlockingQueue q) { queue = q; }
    public void run() {
        try {
            while (true) { consume(queue.take()); }
        } catch (InterruptedException ex) {
//            ... handle ...
        }
    }
    void consume(Object x) {
//        ...
    }
}
