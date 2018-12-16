package com.lifeinfinity.company.multiplythread.threadsynchronization04.deadlock;

/**
 * @Created By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2018/12/13
 * @Modified By:
 */
public class DeadLockThread implements Runnable {
    A a = new A();
    B b = new B();

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Thread.currentThread().setName("主线程");
        b.bar(a);
    }

    public void init() {
        Thread.currentThread().setName("子线程");
        a.foo(b);
    }

    public static void main(String[] args) {
        DeadLockThread deadLockThread = new DeadLockThread();
        new Thread(deadLockThread).start();
        deadLockThread.init();
    }
}
