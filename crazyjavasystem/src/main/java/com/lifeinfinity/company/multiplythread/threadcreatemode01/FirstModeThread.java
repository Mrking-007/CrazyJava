package com.lifeinfinity.company.multiplythread.threadcreatemode01;

/**
 * @program: crazyjavasystem
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-11-29 21:50
 **/
public class FirstModeThread extends Thread {
    private int i;

    @Override
    public void run() {
        for (i = 0; i < 100; i++) {
           /*
            当线程继承自Thread类时，直接使用this即可获取当前线程
            Thread对象的getName()返回当前线程的名字
            因此可以直接使用getName()方法返回当前线程的名字
            等同于Thread.currentThread()
            */
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //调用Thread的currentThread方法获取当前线程
            System.out.println(Thread.currentThread().getName()+" "+i);
            if (i == 20) {
                //创建并启动第一个线程
                new FirstModeThread().start();
                //创建并启动第二个线程
                new FirstModeThread().start();
            }
        }
    }
}
