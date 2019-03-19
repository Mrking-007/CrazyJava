package com.lifeinfinity.company.multiplythread.threadexception07;

import java.util.concurrent.*;

/**
 * @program: crazyjavasystem
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-12-23 21:42
 **/
public class FifthExceptionChildThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("do something 1");
        exceptionMethod();
        System.out.println("do something 2");
        return "";
    }

    private void exceptionMethod() {
        throw new RuntimeException("Thread exception");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        Future future = executorService.submit(new FifthExceptionChildThread());
        try {
            future.get();
        } catch (InterruptedException e) {
            System.out.println(String.format("handle exception in child thread. %s", e));
        } catch (ExecutionException e) {
            System.out.println(String.format("handle exception in child thread. %s", e));
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}
