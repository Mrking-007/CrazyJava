package com.lifeinfinity.company.multiplythread.threadexception07;

/**
 * @program: crazyjavasystem
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-12-23 21:15
 **/
public class SecondExceptionChildThread implements Runnable {
    private static MyExceptionHandler exceptionHandler;

    static {
        exceptionHandler = new MyExceptionHandler();
    }
    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(exceptionHandler);
        System.out.println("do something 1");
        exceptionMethod();
        System.out.println("do something 2");
    }

    private void exceptionMethod() {
        throw new RuntimeException("Thread exception");
    }

    public static void main(String[] args) {
        try {
            new Thread(new SecondExceptionChildThread()).start();
        } catch (RuntimeException e) {
            //只能捕获当前主线程的异常，无法捕获子线程的异常
            System.out.println("Exception has been handled!");
        }
    }
}