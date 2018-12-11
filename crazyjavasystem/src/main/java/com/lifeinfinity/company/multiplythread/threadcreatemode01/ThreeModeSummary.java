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
    * */

}
