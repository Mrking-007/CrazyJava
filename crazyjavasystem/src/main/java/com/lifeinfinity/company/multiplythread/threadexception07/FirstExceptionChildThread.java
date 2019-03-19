package com.lifeinfinity.company.multiplythread.threadexception07;

/**
 * @program: crazyjavasystem
 * @description:
 * 背景：多线程运行不能按照顺序执行过程中捕获异常的方式来处理异常，异常会被直接抛出到控制台（由于线程的本质，使得你不能捕获从线程中逃逸的异常。
 * 一旦异常逃逸出任务的run方法，它就   会向外传播到控制台，除非你采用特殊的形式捕获这种异常。），这样会让你很头疼，无法捕捉到异常就无法处理异常而引发的问题。
 *
 * 设计：在java多线程程序中，所有线程都不允许抛出未捕获的checked exception（比如sleep时的InterruptedException），也就是说各个线程需要自己把自己的checked exception处理掉。
 * 这一点是通过java.lang.Runnable.run()方法声明(因为此方法声明上没有throw exception部分)进行了约束。
 * 但是线程依然有可能抛出unchecked exception（如运行时异常），当此类异常跑抛出时，线程就会立即终结，而对于主线程和其他线程完全不受影响，且完全感知不到某个线程抛出的异常(也是说完全无法catch到这个异常)。
 * JVM的这种设计源自于这样一种理念：“线程是独立执行的代码片断，线程的问题应该由线程自己来解决，而不要委托到外部。”
 * 基于这样的设计理念，在Java中，线程方法的异常（无论是checked还是unchecked exception），都应该在线程代码边界之内（run方法内）进行try catch并处理掉。
 * 换句话说，我们不能捕获从线程中逃逸的异常。
 *
 * 方式：
 * 方法1、子线程run方法中或者call方法中使用try... catch...
 * 方法2、线程设置“未捕获异常处理器”UncaughtExceptionHandler或者DefaultUncaughtExceptionHandler
 *         1)Thread.setUncaughtExceptionHandler 为指定的线程实例设置异常处理器。
 *         2)Thread.setDefaultUncaughtExceptionHandler 为该线程类的所有线程实例设置默认的异常处理器。
 *         当一个线程抛出未处理的异常时，jvm会查找
 *         当前线程实例是否有异常处理器（默认没有），则优先使用该UncaughtExceptionHandler类；
 *         否则，如果当前线程所属的线程组有异常处理器，则使用线程组的UncaughtExceptionHandler(默认没有)；
 *         否则，如果当前线程所属的线程组的父线程组(注意是父线程组，而不是父线程)是否有异常处理器，则使用线程组的UncaughtExceptionHandler(默认没有)；
 *         否则，如果当前线程所属的线程类有全局默认的DefaultUncaughtExceptionHandler，则使用DefaultUncaughtExceptionHandler;
 *         如果都没有的话，子线程就会退出(子线程任务既没执行成功，也没有任何日志提示的“诡异”现象的)。
 *        设置方式：
 *        1)在创建线程的时候进行设置
 *        Thread t = new Thread(new ExceptionThread());
 *        t.setUncaughtExceptionHandler(new MyExceptionHandler());
 *        t.start();
 *        2）在线程run方法中进行设置
 *        Thread.currentThread().setUncaughtExceptionHandler(exceptionHandler);
 *        3）使用Executors创建线程时，还可以在TreadFactory中设置
 *          ExecutorService exec = Executors.newCachedThreadPool(new ThreadFactory(){
 *                     @Override
 *             public Thread newThread(Runnable r) {
 *                 Thread thread = newThread(r);
 *                 thread.setUncaughtExceptionHandler(new MyExceptionHandler());
 *                 return thread;
 *             }
 *                  });
 *          exec.execute(new ExceptionThread());
 *          4）设置全局默认异常捕获
 *          Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());
 * 方法3、通过Future的get方法捕获异常（推荐）
 *  ExecutorService executorService = Executors.newFixedThreadPool(8);
 *         Future future = executorService.submit(new ExceptionThread());
 *         try {
 *             future.get();
 *         } catch (InterruptedException e) {
 *             System.out.println(String.format("handle exception in child thread. %s", e));
 *         } catch (ExecutionException e) {
 *             System.out.println(String.format("handle exception in child thread. %s", e));
 *         } finally {
 *             if (executorService != null) {
 *                 executorService.shutdown();
 *             }
 *         }
 *
 * 参见：
 * https://www.cnblogs.com/brolanda/p/4725138.html
 * https://www.cnblogs.com/yangfanexp/p/7594557.html
 * https://blog.csdn.net/qq_30698633/article/details/77046829
 * https://www.cnblogs.com/csniper/p/5891158.html
 * @author: Mr.Zhang
 * @create: 2018-11-29 22:21
 **/
public class FirstExceptionChildThread implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("do something 1");
            exceptionMethod();
            System.out.println("do something 2");
        } catch (Exception e) {
            System.out.println("use try catch in child thread");
        }
    }

    private void exceptionMethod() {
        throw new RuntimeException("Thread exception");
    }

    public static void main(String[] args) {
        try {
            new Thread(new FirstExceptionChildThread()).start();
        } catch (RuntimeException e) {
            //只能捕获当前主线程的异常，无法捕获子线程的异常
            System.out.println("Exception has been handled!");
        }
    }
}
