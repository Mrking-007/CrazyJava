package com.lifeinfinity.company.multiplythread.threadconmunication05.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Created By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2018/12/13
 * @Modified By:
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bq =new ArrayBlockingQueue<String>(2);
        bq.put("java");//与bq.add("java"),bq.offer("java")相同
        bq.put("java");//与bq.add("java"),bq.offer("java")相同
        bq.put("java");//阻塞线程、bq.add("java")引发异常、bq.offer("java")返回false，元素不会放到队列中
    }
}
