package com.lifeinfinity.company.multiplythread.threadcreatemode01;

/**
 * @Created By: zhangyuhang@gridsum.com
 * @Description: 使用lambda创建runnable对象，但是这样无法做到多个Thread线程共享一个target对象，这与显式定义实现类和内部匿名类不同。
 * @Date: Created on 2018/12/2
 * @Modified By:
 */
public class SecondModeLambdaThread {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            if (i == 20) {
                new Thread(
                        //使用lambda表达式创建runnable对象
                        () -> {
                            int j=0;
                            for (; j < 100; j++) {
                                System.out.println(Thread.currentThread().getName() + "  " + j);
                            }
                        }, "新线程1").start();
            }
        }
    }
}
