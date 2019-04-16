package com.lifeinfinity.company.multiplythread.threadpool08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2019/1/6
 */
public class FixedThreadPoolTest
{
    public static void main(String[] args) {
        //创建一个具有固定线程数的线程
        ExecutorService executorService=Executors.newFixedThreadPool(6);
        Runnable target=()->{
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
        };
        //向线程池中提交两个线程
        executorService.submit(target);
        executorService.submit(target);
        //关闭线程池
        executorService.shutdown();
    }

}
