package com.lifeinfinity.company.multiplythread.threadcreatemode01;

/**
 * @program: crazyjavasystem
 * @description: 自定义类显式实现Runnable接口，来实现线程的
 * @author: Mr.Zhang
 * @create: 2018-11-29 22:19
 **/
public class SecondModeThread implements Runnable {
    private int i;

    @Override
    public void run() {
        for (; i < 100; i++) {
            //实现Runnable只能使用Thread.current获取线程名称，不能使用this关键字
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                SecondModeThread runnableTarget = new SecondModeThread();
                new Thread(runnableTarget, "新线程1").start();
                new Thread(runnableTarget, "新线程2").start();
            }
        }
    }
}
