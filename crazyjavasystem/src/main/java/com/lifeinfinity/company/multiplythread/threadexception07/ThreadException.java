package com.lifeinfinity.company.multiplythread.threadexception07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: crazyjavasystem
 * @description:
 * 背景：多线程运行不能按照顺序执行过程中捕获异常的方式来处理异常，异常会被直接抛出到控制台（由于线程的本质，使得你不能捕获从线程中逃逸的异常。
 * 一旦异常逃逸出任务的run方法，它就会向外传播到控制台，除非你采用特殊的形式捕获这种异常。），这样会让你很头疼，无法捕捉到异常就无法处理异常而引发的问题。
 * 参见：https://www.cnblogs.com/brolanda/p/4725138.html
 * https://www.cnblogs.com/yangfanexp/p/7594557.html
 * https://blog.csdn.net/qq_30698633/article/details/77046829
 * https://www.cnblogs.com/csniper/p/5891158.html
 * @author: Mr.Zhang
 * @create: 2018-11-29 22:21
 **/
public class ThreadException implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException("这个线程就干了这么一件事，抛出一个运行时异常");
    }
    //现象：控制台打印出异常信息，并运行一段时间后才停止
    public static void main(String[] args){
        //就算把线程的执行语句放到try-catch块中也无济于事
        try{
           new Thread(new ThreadException()).start();
        }catch(RuntimeException e){
            System.out.println("Exception has been handled!");
        }
    }
}
