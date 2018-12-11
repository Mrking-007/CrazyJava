package com.lifeinfinity.company.multiplythread.threadcontrol03;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description: yield方法会让当前线程暂停，直接从运行状态进入就绪状态。优先级和当前线程相同或者比当前线程高的其他处于就绪状态的线程获得执行机会。
 * 如果没有这样的线程，则很有可能yield的线程直接获得cpu资源，从就绪状态转为运行状态。
 * @Date: Created on 2018/12/9
 * @Modified By:
 */
public class YieldTest extends Thread {
    private int i;

    public YieldTest(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        YieldTest yt1 = new YieldTest("高级");
        yt1.setPriority(Thread.MAX_PRIORITY);
        yt1.start();
        YieldTest yt2 = new YieldTest("低级");
        yt1.setPriority(Thread.MIN_PRIORITY);
        yt2.start();
    }
}
