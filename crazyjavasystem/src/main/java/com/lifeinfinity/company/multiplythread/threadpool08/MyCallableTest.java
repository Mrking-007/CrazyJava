package com.lifeinfinity.company.multiplythread.threadpool08;

import java.util.concurrent.Callable;

/**
 * @author Administrator
 * @date 2018/11/8
 */
public class MyCallableTest implements Callable<Integer> {
    //一共有5张火车票
    private int ticketsCount = 5;
    TestService testService;

    MyCallableTest(TestService testService){
        this.testService=testService;
    }

    @Override
    public Integer call() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            testService.SayHi();
            //System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return ticketsCount;
    }

}
