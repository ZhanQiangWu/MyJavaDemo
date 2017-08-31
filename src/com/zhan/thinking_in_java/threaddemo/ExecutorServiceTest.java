package com.zhan.thinking_in_java.threaddemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 *  参考资料：Java线程池ExecutorService  http://www.cnblogs.com/Steven0805/p/6393443.html
 *  Created by zhan on 2017/8/31.
 */
public class ExecutorServiceTest {

    public static void main(String[] args) {


        newSingleThreadExecutor();


    }

    /**
     * 单线程化的线程池
     * 它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     */
    private static void newSingleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println(index + "  threadname: " + Thread.currentThread().getName()
                                + "  threadid: " + Thread.currentThread().getId());
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * newScheduledThreadPool 定长线程池
     * 支持定时及周期性任务执行
     */
    private static void newScheduledThreadPoolTest() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        //延迟3秒执行
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        },3,TimeUnit.SECONDS);

        //定期执行
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds, and excute every 2 seconds");

            }
        },3,2,TimeUnit.SECONDS); //表示延迟3秒后每2秒执行一次;ScheduledExecutorService比Timer更安全，功能更强大

        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("FixedDelay: init delay 3 seconds, and delay every 2 seconds between every two execution");
            }
        },3,2,TimeUnit.SECONDS);//表示延迟3秒后开始执行，往后每两个前后线程之间的时间间隔为2秒。而每个线程的运行时间不定

        /**  辨析 scheduleAtFixedRate 与 scheduleWithFixedDelay 的区别
         （参考：http://blog.csdn.net/u013819945/article/details/47723091）
         1、scheduleAtFixedRate ，是以上一个任务开始的时间计时，period时间过去后，检测上一个任务是否执行完毕，
            如果上一个任务执行完毕，则当前任务立即执行，如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行。
         2、scheduleWithFixedDelay，是以上一个任务结束时开始计时，period时间过去后，立即执行。
         */
    }

    /**
     * newFixedThreadPool 定长线程池
     * 可控制线程最大并发数，超出的线程会在队列中等
     * 定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()。可参考 PreloadDataCache
     *
     * Runtime.getRuntime().availableProcessors() 可参考 http://blog.csdn.net/jiafu1115/article/details/40430709
     */
    private static void test3() {
        System.out.println("系统数据 ： " + Runtime.getRuntime().availableProcessors());
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index + "  threadname: " + Thread.currentThread().getName()
                                + "  threadid: " + Thread.currentThread().getId());
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * newCachedThreadPool
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
     * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
     */
    private static void test2() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            final int index = i;
//            try {
//                Thread.sleep(900);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            cachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(index + "  threadname: " + Thread.currentThread().getName()
                            + "  threadid: " + Thread.currentThread().getId());
                }
            });
        }
    }

    private static void test1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> resultList = new ArrayList<>();

        // 创建10个任务并执行
        for (int i = 0; i < 10; i++) {
            // 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> future = executorService.submit(new TaskWithResult(i));
            // 将任务执行结果存储到List中
            resultList.add(future);
        }
        executorService.shutdown();

        //遍历任务的结果
        for (Future<String> fs : resultList) {
            try {
                System.out.println(fs.get()); // 打印各个线程（任务）执行的结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                executorService.shutdownNow();
                e.printStackTrace();
                return;
            }
        }
    }
}


class TaskWithResult implements Callable<String>{

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    /**
     * 任务的具体过程，一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        System.out.println("call()方法被自动调用,干活！！！ " + Thread.currentThread().getName());
//        if (new Random().nextBoolean())
//            throw new TaskException("Meet error in task. id = " + id + " " + Thread.currentThread().getName());

        // 一个模拟耗时的操作
        for (int i = 999999999; i > 0; i--)
            ;

        return "call()方法被自动调用，任务的结果是：" + id + "  " + Thread.currentThread().getName();
    }
}

class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
}