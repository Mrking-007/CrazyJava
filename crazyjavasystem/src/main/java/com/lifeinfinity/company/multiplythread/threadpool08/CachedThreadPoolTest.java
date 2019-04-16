package com.lifeinfinity.company.multiplythread.threadpool08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2019/4/1
 */
public class CachedThreadPoolTest {

    public static void main(String[] args) throws Exception {

        // 创建一个线程池对象，控制要创建几个线程对象。
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 可以执行Runnable对象或者Callable对象代表的线程
        //Future<Integer> result = pool.submit(new MyCallableTest());
        TestService testService=new TestServiceImpl();
        List<Callable<Integer>> callableTasks = new ArrayList<Callable<Integer>>();
        callableTasks.add(new MyCallableTest(testService));
        callableTasks.add(new MyCallableTest(testService));
        try {
            List<Future<Integer>> futureResults = executorService.invokeAll(callableTasks);
            executorService.shutdown();//结束线程池,不再接受新任务，原有任务正常执行

            Integer sumResult = 0;
            for (Future<Integer> future :
                    futureResults) {
                sumResult += future.get();
            }
        } catch (InterruptedException e) {
            System.out.println(String.format("handle exception in child thread. %s", e));
        } catch (ExecutionException e) {
            System.out.println(String.format("handle exception in child thread. %s", e));
        } catch (Exception e) {
            System.out.println(String.format("handle exception in child thread. %s", e));
        } finally {
            if (executorService != null) {
                executorService.shutdownNow();
            }
        }


        //方式一：等待全部任务结束
       /*
        while (true) {
            if (executorService.isTerminated()) {
                System.out.println("结束了！");
                break;
            }
            Thread.sleep(200);
        }
        System.out.println("hello");*/
        //方式二：等待全部任务结束
        /*
        *   if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
            throw new Exception("ss");
        }*/
        //方式三：invokeAll

        //保证线程都执行完
        //异常处理
        //CurrentHashMap
    }
}

