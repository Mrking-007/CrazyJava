package com.lifeinfinity.company.multiplythread.threadexception07;

/**
 * @program: crazyjavasystem
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-12-16 21:39
 **/
public class MyThread extends Thread {

    public MyThread(String threadName) {
        super(threadName);
    }

    //提供执行线程名、线程组的构造器
    public MyThread(ThreadGroup threadGroup, String threadName) {
        super(threadGroup, threadName);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "线程的i变量" + i);
        }
    }

    public static void main(String[] args) {
        ThreadGroup threadGroup=Thread.currentThread().getThreadGroup();
        System.out.println("主线程所在的线程组为名字："+threadGroup.getName());
        System.out.println("主线程所在线程组是否是后台程组："+threadGroup.isDaemon());
        System.out.println("主线程所在线程组活动线程的数目："+threadGroup.activeCount());
        new MyThread("主线程组线程启动").start();

        ThreadGroup threadGroup1=new ThreadGroup("新线程组");
        threadGroup1.setDaemon(true);
        threadGroup1.setMaxPriority(Thread.MAX_PRIORITY);
        System.out.println("threadGroup1线程组是否是后台线程组"+threadGroup1.isDaemon());
        new MyThread(threadGroup1,"threadGoup1组的线程甲").start();
        new MyThread(threadGroup1,"threadGoup1组的线程乙").start();
        //这个关键字后续需要了解下
        //threadGroup1.interrupt();
    }
}
