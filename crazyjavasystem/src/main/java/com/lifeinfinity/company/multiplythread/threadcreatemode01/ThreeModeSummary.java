package com.lifeinfinity.company.multiplythread.threadcreatemode01;

import java.util.concurrent.FutureTask;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2018/12/9
 * @Modified By:
 */
public class ThreeModeSummary {
    //解密Thread.Start()，如何调用run方法的。https://www.linuxidc.com/Linux/2016-03/128997.htm
    /*
    * 1、继承Thread类，重写run方法。不能再继承其他父类
    *  new FirstModeThread().start()
    * 2、实现Runnable接口，重写run方法。
    *  SecondModeThread runnableTarget = new SecondModeThread();
    *  new Thread(runnableTarget, "新线程1").start();
    * 3、实现Callable接口,重写call方法+FutureTask。FutureTask implements RunnableFuture; RunnableFuture<V> extends Runnable, Future<V>;
    *  public FutureTask(Callable<V> callable) {
    *  }
    *  Callable<Integer> callable = new ThirdModeThread();
       FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
       //到这里本质仍是第二种模式，使用runnable对象作为target创建thread
       new Thread(futureTask, "新线程1").start();
       对于2、3可以共用thread-target对象时，依据实验必须对target对象run、call方法进行线程安全控制，否则无法保证线程安全
    * */

    /* 下方的类，虽然共享target对象，但是做不到线程安全
    * public class HelloThread implements Runnable {

    private int ticket = 5;

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + (ticket--));
            if (ticket < 1) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        HelloThread t = new HelloThread();
        Thread thread1 = new Thread(t, "1号窗口");
        thread1.start();
        Thread thread2 = new Thread(t, "2号窗口");
        thread2.start();
    }
}*/
}
