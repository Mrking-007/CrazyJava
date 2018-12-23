package com.lifeinfinity.company.multiplythread.threadexception07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @program: crazyjavasystem
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-12-23 21:29
 **/
public class FourExceptionChildThread implements Runnable {
    @Override
    public void run() {
        System.out.println("do something 1");
        exceptionMethod();
        System.out.println("do something 2");
    }

    private void exceptionMethod() {
        throw new RuntimeException("Thread exception");
    }

    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newCachedThreadPool(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setUncaughtExceptionHandler(new MyExceptionHandler());
                    return thread;
                }
            });
            exec.execute(new FourExceptionChildThread());
        } catch (RuntimeException e) {
            //只能捕获当前主线程的异常，无法捕获子线程的异常
            System.out.println("Exception has been handled!");
        }
    }
}
