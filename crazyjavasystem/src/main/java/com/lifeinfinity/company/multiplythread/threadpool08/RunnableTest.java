package com.lifeinfinity.company.multiplythread.threadpool08;

/**
 * @Created By:
 * @Description:
 * @Date: Created on 2018/11/10
 * @Modified By:
 */
public class RunnableTest implements Runnable {

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            try {
                System.out.println(Thread.currentThread().getName()+" "+i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
