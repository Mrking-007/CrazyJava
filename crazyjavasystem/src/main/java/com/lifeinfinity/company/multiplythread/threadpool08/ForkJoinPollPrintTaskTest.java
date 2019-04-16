package com.lifeinfinity.company.multiplythread.threadpool08;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @Author By: zhangyuhang@gridsum.com
 * @Description: https://blog.csdn.net/u012168222/article/details/52790400
 * shutdown() 、 shutdownNow() 、 awaitTermination() 的用法和区别
 * https://blog.csdn.net/wo541075754/article/details/51564359
 * @Date: Created on 2019/1/6
 */
public class ForkJoinPollPrintTaskTest extends RecursiveAction {
    private static final int THREHOLD = 50;
    private int start;
    private int end;

    public ForkJoinPollPrintTaskTest(int start, int end){
        this.start=start;
        this.end=end;
    }

    /**
     * The main computation performed by this task.
     */
    @Override
    protected void compute() {
        if(end-start<THREHOLD){
            for (int i = start; i <end; i++) {
                System.out.println(Thread.currentThread().getName()+"i的值"+i);
            }
        }else{
            //当end与start之间的差大于THRESHOD，即要打印的数超过50个时，将大任务分解成两个小任务
            int middle=(start+end)/2;
            ForkJoinPollPrintTaskTest left=new ForkJoinPollPrintTaskTest(start,middle);
            ForkJoinPollPrintTaskTest right=new ForkJoinPollPrintTaskTest(middle,end);
            //并行执行两个小任务
            left.fork();
            right.fork();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        forkJoinPool.submit(new ForkJoinPollPrintTaskTest(0,300));
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        forkJoinPool.shutdown();
        System.out.println("线程池是否关闭："+forkJoinPool.isShutdown());
        System.out.println("线程池并行值："+forkJoinPool.getParallelism());
    }

}
