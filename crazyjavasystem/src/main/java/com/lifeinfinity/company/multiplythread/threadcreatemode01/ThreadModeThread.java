package com.lifeinfinity.company.multiplythread.threadcreatemode01;

import javax.swing.plaf.TreeUI;
import java.util.concurrent.*;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description: 使用Callable和Future创建多线程
 * 参见：http://www.threadworld.cn/archives/39.html https://blog.csdn.net/sunp823/article/details/51569314
 * FutureTask
 * RunnableFuture
 * Runnable
 * Future
 * @Date: Created on 2018/12/2
 * @Modified By:
 */
public class ThreadModeThread implements Callable<Integer> {
    private int i;
    private int sum;

    @Override
    public Integer call() throws Exception {
        Thread.sleep(10000);
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        Callable<Integer> callable = new ThreadModeThread();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        //到这里本质仍是第二种模式，使用runnable对象作为target创建thread
        new Thread(futureTask, "新线程1").start();

        try {
            //试图取消该Thread的target对象执行
            boolean cancel = futureTask.cancel(true);
            System.out.println("cancel:" + cancel);

            //上一个方法如果在正常执行完成前被取消，则为true
            boolean isCancelled = futureTask.isCancelled();
            System.out.println("isCancelled:" + isCancelled);

            //阻塞主线程，直到子线程结束有返回值（此时如果如果FutureTask任务被取消了，则抛出CancellationException）
            Integer get = futureTask.get();
            System.out.println("get:" + get);

            //阻塞主线程指定时间，如果经过指定时间后，子线程怡依然没有结束，则抛出TimeoutException（但不会取消FutureTask的执行，子线程继续自己执行），如果FutureTask任务被取消了，则抛出CancellationException；
            Integer getWaitTime = futureTask.get(5000, TimeUnit.MILLISECONDS);
            System.out.println("getWaitTime:" + getWaitTime);

            //如果FutureTask任务已完成，则为true
            boolean isDone = futureTask.isDone();
            System.out.println("isDone:" + isDone);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
