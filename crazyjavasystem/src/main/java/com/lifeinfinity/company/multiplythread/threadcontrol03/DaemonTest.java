package com.lifeinfinity.company.multiplythread.threadcontrol03;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description: 前台线程创建的子线程，默认是前台线程。后台线程创建的子线程，默认是后台线程。
 * 前台线程死亡后，jvm会通知后台线程死亡，但是接到指令做出响应，需要一定时间。
 * @Date: Created on 2018/12/9
 * @Modified By:
 */
public class DaemonTest extends Thread {
    private int i;

    @Override
    public void run() {
        for (; i < 200; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i + " " + Thread.currentThread().isDaemon());
        }
    }

    public static void main(String[] args) {
        DaemonTest daemonTest = new DaemonTest();
        daemonTest.setDaemon(true);
        daemonTest.start();
        //只能在start前，设置后台线程方法，如果在start之后则抛异常IllegalThreadStateException
        //daemonTest.setDaemon(true);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i + " " + Thread.currentThread().isDaemon());
        }
    }

}
