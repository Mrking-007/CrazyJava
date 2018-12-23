package com.lifeinfinity.company.multiplythread.threadconmunication05.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @program: crazyjavasystem
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-12-16 17:12
 **/
public class Consumer extends  Thread {
    private BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue,String threadName){
        super(threadName);
        this.blockingQueue=blockingQueue;
    }

    @Override
    public void  run(){
         while (true){
             System.out.println(Thread.currentThread().getName()+"准备消费集合元素！");
             try {
                 Thread.sleep(200);
                 blockingQueue.take();
             }
             catch (Exception e){
                 e.printStackTrace();
             }
             System.out.println(Thread.currentThread().getName()+"消费完成"+blockingQueue);
         }
    }
}
