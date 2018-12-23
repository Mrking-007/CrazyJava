package com.lifeinfinity.company.multiplythread.threadexception07;

/**
 * @program: crazyjavasystem
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-12-23 19:58
 **/
public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(String.format("%s线程出现了异常：%s",t,e));
    }
}
