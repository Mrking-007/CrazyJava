package com.lifeinfinity.company.multiplythread.threadcreatemode01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 * @author Administrator
 * @date 2018/11/8
 */
public class MyCallableTest implements Callable<Integer> {
    //一共有5张火车票
    private int ticketsCount = 5;

    @Override
    public Integer call() throws InterruptedException {

        while (ticketsCount > 0) {
                          

            
            synchronized (this) {
                if (ticketsCount > 0) {
                    //如果还有票，就卖掉一张票
                    ticketsCount--;
                    System.out.println(Thread.currentThread().getName() + "卖掉了1张票，剩余票数为:" + ticketsCount);
                }
            }
            Thread.sleep(1000);
        }
        return ticketsCount;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建MyCallable对象
        Callable<Integer> myCallable = new MyCallableTest();

        //用FutureTask包装MyCallable对象
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(myCallable);
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(myCallable);
        FutureTask<Integer> futureTask3 = new FutureTask<Integer>(myCallable);

        //这么写，多线程成执行，多线程抢占锁资源，可以共享执行执行call方法
        Thread thread1 = new Thread(futureTask1,"窗口1");
        Thread thread2 = new Thread(futureTask2,"窗口2");
        Thread thread3 = new Thread(futureTask3,"窗口3");
        //这么写共用相同的FutureTask，只能单线程抢占所资源，无法共享执行call方法
        /*Thread thread1 = new Thread(futureTask1,"窗口1");
        Thread thread2 = new Thread(futureTask1,"窗口2");
        Thread thread3 = new Thread(futureTask1,"窗口3");*/

        thread1.start();
        thread2.start();
        thread3.start();
        int leftTickets = futureTask1.get();
        System.out.println("剩余车票数：" + leftTickets);

    }


}
