package com.lifeinfinity.company.multiplythread.threadcreatemode01;

/**
 * @Created By:
 * @Description:
 * @Date: Created on 2018/11/10
 * @Modified By:
 */
public class RunnableTest implements Runnable {
    //一共有5张火车票
    private int ticketsCount = 5;


    @Override
    public void run() {
        while (ticketsCount > 0) {
            try {
                //休眠看效果
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                if (ticketsCount > 0) {
                    //如果还有票，就卖掉一张票
                    ticketsCount--;
                    System.out.println(Thread.currentThread().getName() + "卖掉了1张票，剩余票数为:" + ticketsCount);
                }
            }
        }
    }

    public static void main(String[] args) {

        Runnable runnable = new RunnableTest();
        Thread windowsOne = new Thread(runnable, "窗口1");
        Thread windowsTwo = new Thread(runnable, "窗口2");
        Thread windowsThree = new Thread(runnable, "窗口3");
        windowsOne.start();
        windowsTwo.start();
        windowsThree.start();
    }


}
