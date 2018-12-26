package com.lifeinfinity.company.multiplythread.threadpool08;

import java.util.concurrent.Executors;

/**
 * @program: crazyjavasystem
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-12-26 20:55
 **/
public class ThreadPool {
    public static void nae(){
        Executors.newWorkStealingPool();
    }


    /*
    参见：
    ThreadPool、ThreadPoolExecutor
    http://www.cnblogs.com/baizhanshi/p/5469948.html
    https://www.jianshu.com/p/d2729853c4da?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
    http://www.cnblogs.com/dolphin0520/p/3932921.html
    https://www.jianshu.com/p/135c89001b61
    http://www.cnblogs.com/zhujiabin/p/5404771.html
    http://www.cnblogs.com/kuoAT/p/6714762.html
    https://blog.csdn.net/M_Jack/article/details/83041729
    https://www.cnblogs.com/kuoAT/p/6714080.html

    ForkJoinPool
    https://blog.csdn.net/qq_25224749/article/details/81146556
    https://www.cnblogs.com/zjfjava/p/8505606.html
    https://www.jianshu.com/p/de025df55363
    http://blog.dyngr.com/blog/2016/09/15/java-forkjoinpool-internals/
    http://gee.cs.oswego.edu/dl/papers/fj.pdf  http://ifeve.com/doug-lea/  http://www.importnew.com/5575.html

    一、线程池
    1、使用new Thread方式的弊端
    执行一个异步任务你还只是如下new Thread吗？
    new Thread(new Runnable() {
    @Override
    public void run() {
    // TODO Auto-generated method stub
    }
    }).start();
    那你就out太多了，new Thread的弊端如下：
    a. 每次new Thread新建对象性能差。
    b. 线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机、jvm崩溃或oom。
    c. 缺乏更多功能，如定时执行、定期执行、线程中断。
    2、使用Java提供的四种线程池的好处在于：
    a. 重用存在的线程，减少对象创建、消亡的开销，性能佳。
    b. 可有效控制最大并发线程数量，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。
    c. 提供定时执行、定期执行、单线程、并发数控制等功能。
    二、Executors静态方法
    Java5通过Executors工厂类的静态方法，来提供线程池的创建
    1)ExecutorService newCachedThreadPool();创建一个可缓存线程池，系统根据需要创建线程，如果线程池长度超过处理需要,可灵活回收空闲线程，若无可回收，则新建线程。
    2)ExecutorService newFixedThreadPool(int nThreads);创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    3)ExecutorService newSingleThreadExecutor();创建一个只有单线程的线程池，相当于调用newFixedThreadPool(1),它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

    4)ScheduledExecutorService ScheduledThreadPool(int corePoolSize);创建一个定长线程池，支持定时及周期性任务执行。
    5)ScheduledExecutorService newSingleThreadScheduledExecutor;创建一个定长线程池，相当于调用newScheduledThreadPool(1),支持定时及周期性任务执行。
    以上静态方法内部，都是使用new ThreadPoolExecutor(int corePoolSize, int maximumPoolSize,long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory,RejectedExecutionHandler handler)
    创建一个ThreadPoolExecutor线程池一般需要以下几个参数：
    corePoolSize（线程池的基本大小）：
　　当提交一个任务到线程池时，线程池会创建一个线程来执行任务，即使其他空闲的基本线程能够执行新任务也会创建线程，等到需要执行的任务数大于线程池基本大小时就不再创建。如果调用了线程池的prestartAllCoreThreads方法，线程池会提前创建并启动所有基本线程。
    maximumPoolSize（线程池最大大小）：
　　线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。值得注意的是如果使用了无界的任务队列这个参数就没什么效果。
    keepAliveTime（线程活动保持时间）：　
　　线程池的工作线程空闲后，保持存活的时间。所以如果任务很多，并且每个任务执行的时间比较短，可以调大这个时间，提高线程的利用率。
    TimeUnit（线程活动保持时间的单位）：
　　可选的单位有天（DAYS），小时（HOURS），分钟（MINUTES），毫秒(MILLISECONDS)等。
    workQueue（任务队列）：　
　　用于保存等待执行的任务的阻塞队列。 可以选择以下几个阻塞队列：ArrayBlockingQueue、LinkedBlockingQueue、SynchronousQueue、PriorityBlockingQueue　
    threadFactory：　
　　用于设置创建线程的工厂，可以通过线程工厂给每个创建出来的线程设置更有意义的名字。　
    handler（饱和策略）：　
　　当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。这个策略默认情况下是AbortPolicy，表示无法处理新任务时抛出异常。
        有以下四种取值：
        ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
        ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
        ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
        ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
　　我们尽量优先使用Executors提供的静态方法来创建线程池，如果Executors提供的方法无法满足要求，再自己通过ThreadPoolExecutor类来创建线程池。

    Java8新增的两个静态方法，内部使用new ForkJoinPool(int parallelism, ForkJoinWorkerThreadFactory factory, UncaughtExceptionHandler handler,boolean asyncMode)
    6)ExecutorService newWorkStealingPool(int parallelism);创建持有足够的线程的线程池来支持给定的并行级别，该方法还有使用多个对垒来减少竞争。
    7)ExecutorService newWorkStealingPool();是上一个方法的简化版本，当前机器有4个CPU，则并行级别设置为4



    */

}
