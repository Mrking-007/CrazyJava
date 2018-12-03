package com.lifeinfinity.company.multiplythread.threadcreatemode01;

import jdk.nashorn.internal.ir.ForNode;

/**
 * @Created By: zhangyuhang@gridsum.com
 * @Description: 使用匿名内部类(Anonymous Inner Class)实现，匿名类参加https://www.cnblogs.com/xianya/p/9098530.html
 * @Date: Created on 2018/12/2
 * @Modified By:
 */

public class SecondModeAnonymousThread {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            if (i == 20) {
                Runnable runnableTarget = new Runnable() {
                    private int anInt;

                    @Override
                    public void run() {
                        for (; anInt < 100; anInt++) {
                            System.out.println(Thread.currentThread().getName() + " " + anInt);
                        }
                    }
                };
                new Thread(runnableTarget, "新线程1").start();
                new Thread(runnableTarget, "新线程2").start();
            }
        }
    }
}
