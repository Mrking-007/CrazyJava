package com.lifeinfinity.company.multiplythread.threadconmunication05.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @program: crazyjavasystem
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-12-16 17:05
 **/
public class Producer extends  Thread  {
    private BlockingQueue<String> blockingQueue;

    public Producer(BlockingQueue<String> blockingQueue,String threadName){
        super(threadName);
        this.blockingQueue=blockingQueue;
    }

    @Override
    public void  run(){
        String [] strArr=new String[]{
                "java",
                "spring",
                "mybatis"
        };
        for (int i = 0; i < 9999999; i++) {
            System.out.println(Thread.currentThread().getName()+"准备生产集合元素!");
            try{
                Thread.sleep(200);
                blockingQueue.put(strArr[i%3]);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"生产完成"+blockingQueue);
        }
    }
}
