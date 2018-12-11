package com.lifeinfinity.company.multiplythread.threadstate02;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2018/12/9
 * @Modified By:
 */
public class InvokeRun extends Thread {
    private int i;

    @Override
    public void run() {
        for (; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                /*
                * 不能调用run方法，此时不是执行多线程，而是普通对象方法调用。仍然是单线程程序，都处在主线程中。
                * */
                new InvokeRun().run();
                new InvokeRun().run();


                /*连续两次调用start方法，抛异常IllegalThreadStateException，生命周期内只能调用一次start方法。
                * */
                InvokeRun invokeRun = new InvokeRun();
                invokeRun.start();
                invokeRun.start();
            }
        }
    }
}
