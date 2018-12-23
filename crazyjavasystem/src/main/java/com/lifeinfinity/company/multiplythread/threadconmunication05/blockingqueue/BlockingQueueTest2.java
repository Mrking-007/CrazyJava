package com.lifeinfinity.company.multiplythread.threadconmunication05.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @program: crazyjavasystem
 * @description: ${description}
 * @author: Mr.Zhang
 * @create: 2018-12-16 17:19
 **/
public class BlockingQueueTest2 {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
        new Producer(blockingQueue, "生产者1").start();
        new Producer(blockingQueue, "生产者2").start();
        new Consumer(blockingQueue, "消费者1").start();
    }
}
