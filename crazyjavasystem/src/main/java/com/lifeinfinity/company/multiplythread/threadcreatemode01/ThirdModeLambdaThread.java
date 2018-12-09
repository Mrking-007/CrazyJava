package com.lifeinfinity.company.multiplythread.threadcreatemode01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2018/12/2
 * @Modified By:
 */
public class ThirdModeLambdaThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Callable使用lambda方式实现
        Callable<Integer> callable = new Callable<Integer>() {
            private int i;
            private int sum;

            @Override
            public Integer call() throws Exception {
                for (; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                    sum += i;
                }
                return sum;
            }
        };
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
