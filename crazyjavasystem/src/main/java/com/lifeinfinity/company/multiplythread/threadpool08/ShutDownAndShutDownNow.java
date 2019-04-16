package com.lifeinfinity.company.multiplythread.threadpool08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author By: zhangyuhang@gridsum.com
 * @Description: https://stackoverflow.com/questions/11520189/difference-between-shutdown-and-shutdownnow-of-executor-service
 *
 * In summary, you can think of it that way:
   shutdown() will just tell the executor service that it can't accept new tasks, but the already submitted tasks continue to run.
   shutdownNow() will do the same AND will try to cancel the already submitted tasks by interrupting the relevant threads. Note that if your tasks ignore the interruption, shutdownNow will behave exactly the same way as shutdown.

   You can try the example below and replace shutdown by shutdownNow to better understand the different paths of execution:
   with shutdown, the output is Still waiting after 100ms: calling System.exit(0)... because the running task is not interrupted and continues to run.
   with shutdownNow, the output is interrupted and Exiting normally... because the running task is interrupted, catches the interruption and then stops what it is doing (breaks the while loop).
   with shutdownNow, if you comment out the lines within the while loop, you will get Still waiting after 100ms: calling System.exit(0)... because the interruption is not handled by the running task any longer.
 * @Date: Created on 2019/4/2
 */
public class ShutDownAndShutDownNow {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("interrupted");
                        break;
                    }
                }
            }
        });

        executor.shutdownNow();//executor.shutdown();
        if (!executor.awaitTermination(100, TimeUnit.MICROSECONDS)) {
            System.out.println("Still waiting after 100ms: calling System.exit(0)...");
            System.exit(0);
        }
        System.out.println("Exiting normally...");
    }
}
