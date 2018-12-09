package com.lifeinfinity.company.multiplythread.threadcontrol03;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description: 线程执行时都有优先级，优先级高的有较多的执行机会。默认所有线程优先级一样。
 * 每个线程的优先级与创建他的父线程优先级相同。Thread提供了1-10的优先级，有3个静态常量，默认值是5。
 * 可以在start之后设置线程优先级
 * @Date: Created on 2018/12/9
 * @Modified By:
 */
public class PriorityTest extends Thread {
    private int i;

    public PriorityTest(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        for (; i < 300; i++) {
            System.out.println(Thread.currentThread().getName() + " 优先级为：" + Thread.currentThread().getPriority() + " " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setPriority(6);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " 优先级为：" + Thread.currentThread().getPriority() + " " + i);
            if (i == 20) {
                PriorityTest low = new PriorityTest("低级");
                low.start();
                //阻塞下主线程，让子线程输出几个循环变量
                Thread.sleep(1);
                //可以看到子线程有一段输出为6的优先级，之后才变为1（多试几次）
                low.setPriority(Thread.MIN_PRIORITY);
            }
            if (i == 40) {
                PriorityTest high=new PriorityTest("高级");
                high.start();
                //阻塞下主线程，让子线程输出几个循环变量
                Thread.sleep(1);
                //可以看到子线程有一段输出为6的优先级，之后才变为10（多试几次）
                high.setPriority(Thread.MAX_PRIORITY);
            }
        }
    }
}
