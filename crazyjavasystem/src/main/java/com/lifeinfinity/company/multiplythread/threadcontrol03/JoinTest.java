package com.lifeinfinity.company.multiplythread.threadcontrol03;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2018/12/9
 * @Modified By:
 */
public class JoinTest extends Thread {
    private int i;

    public JoinTest(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JoinTest joinTest = new JoinTest("新线程");
        joinTest.start();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                JoinTest jt = new JoinTest("join测试");
                jt.start();
                //main线程调用jt线程的join方法，main线程必须等jt线程结束才能会向下执行
                //jt.join();
                //main线程调用jt线程的join方法，main线程必须等jt线程1ms，才能会向下执行，如果jt线程没结束，则不再等待，继续向下执行。
                jt.join(1);
            }
        }
    }
}
