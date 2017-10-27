package com.zhan.thinking_in_java.threaddemo;

import java.util.LinkedList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * wait / notify 方法测试
 * 参考资料： http://www.cnblogs.com/0201zcr/p/4758533.html
 * Created by zhan on 2017/10/25.
 */
public class ProducerConsumerTest {

    public static void main(String[] args) {

    }

    void waitNotifyTest(){
        //仓库对象
        Storage storage = new Storage();

        //生产者对象
        Producer p1 = new Producer(storage);
        Producer p2 = new Producer(storage);
        Producer p3 = new Producer(storage);
        Producer p4 = new Producer(storage);
        Producer p5 = new Producer(storage);
        Producer p6 = new Producer(storage);
        Producer p7 = new Producer(storage);

        // 消费者对象
        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);
        Consumer c3 = new Consumer(storage);

        // 设置生产者产品生产数量
        p1.setNum(10);
        p2.setNum(10);
        p3.setNum(10);
        p4.setNum(10);
        p5.setNum(10);
        p6.setNum(10);
        p7.setNum(80);

        // 设置消费者产品消费数量
        c1.setNum(50);
        c2.setNum(20);
        c3.setNum(30);

        // 线程开始执行
        c1.start();
        c2.start();
        c3.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }
}

/**
 * await / signal 测试
 * 仓库类Storage实现缓冲区
 *
 * await()和signal()就是其中用来做同步的两种方法，
 * 它们的功能基本上和wait() / nofity()相同，完全可以取代它们，
 * 但是它们和新引入的锁定机制Lock直接挂钩，具有更大的灵活性。
 * 通过在Lock对象上调用newCondition()方法，
 * 将条件变量和一个锁对象进行绑定，进而控制并发程序访问竞争资源的安全。
 */
class AwaitSignalStorage extends Storage{

    // 仓库最大存储量
    private final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();

    //锁
    private final Lock lock = new ReentrantLock();//重入锁

    //仓库满的条件变量
    private final Condition full = lock.newCondition();

    //仓库空的条件变量
    private final Condition empty = lock.newCondition();

    // 生产num个产品
    public void produce(int num){
        //获得锁
        lock.lock();

        // 如果仓库剩余容量不足
        while (list.size() + num > MAX_SIZE){
            System.out.println("【要生产的产品数量】:" + num + "/t【库存量】:" + list.size()
                    + "/t暂时不能执行生产任务!");
            try {
                // 由于条件不满足，生产阻塞
                full.await();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= num; ++i) {
            list.add(new Object());
        }
        System.out.println("【已经生产产品数】:" + num + "/t【现仓储量为】:" + list.size());
        // 唤醒其他所有线程
        full.signalAll();
        empty.signalAll();

        //释放锁
        lock.unlock();
    }
}


/**
 * 仓库类Storage实现缓冲区
 */
class Storage {

    // 仓库最大存储量
    private final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();

    /**
     * 生产num个产品
     *
     * @param num 生产产品的数量
     */
    public void produce(int num) {
        //同步代码段
        synchronized (list) {
            //如果仓库剩余容量不足
            while (list.size() + num > MAX_SIZE) {
                System.out.println("【要生产的产品数量】:" + num + " \t 【库存量】:"
                        + list.size() + "\t 暂时不能执行生产任务!");

                try {
                    // 由于条件不满足，生产阻塞
                    list.wait();
                    System.out.println(" 生产者 " + num + " 被唤醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 生产条件满足情况下，生产num个产品
            for (int i = 1; i <= num; ++i) {
                list.add(new Object());
            }

            System.out.println("【已经生产产品数】:" + num + "\t 【现仓储量为】:" + list.size());
            list.notifyAll();
        }
    }

    /**
     * 消费num个产品
     *
     * @param num 消费产品数量
     */
    public void consume(int num) {
        synchronized (list) {
            // 如果仓库存储量不足
            while (list.size() < num) {
                System.out.println("【要消费的产品数量】:" + num + " \t【库存量】:"
                        + list.size() + " \t 暂时不能执行消费任务!");
                try {
                    // 由于条件不满足，消费阻塞
                    list.wait();
                    System.out.println(" 消费者 " + num + " 被唤醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 消费条件满足情况下，消费num个产品
            for (int i = 1; i <= num; ++i) {
                list.remove();
            }
            System.out.println("【已经消费产品数】:" + num + " \t 【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }

    // get/set方法
    public LinkedList<Object> getList() {
        return list;
    }

    public void setList(LinkedList<Object> list) {
        this.list = list;
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

}

/**
 * 生产者类Producer继承线程类Thread
 */
class Producer extends Thread {

    // 每次生产的产品数量
    private int num;

    // 所在放置的仓库
    private Storage storage;

    // 构造函数，设置仓库
    Producer(Storage storage) {
        this.storage = storage;
    }

    // 线程run函数
    public void run() {
        produce(num);
    }

    // 调用仓库Storage的生产函数
    void produce(int num) {
        storage.produce(num);
    }

    // get/set方法
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}

/**
 * 消费者类Consumer继承线程类Thread
 */
class Consumer extends Thread {
    // 每次消费的产品数量
    private int num;

    // 所在放置的仓库
    private Storage storage;

    // 构造函数，设置仓库
    public Consumer(Storage storage) {
        this.storage = storage;
    }

    // 线程run函数
    public void run() {
        consume(num);
    }

    // 调用仓库Storage的生产函数
    void consume(int num) {
        storage.consume(num);
    }

    // get/set方法
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}

