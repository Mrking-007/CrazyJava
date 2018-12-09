package com.lifeinfinity.company.multiplythread.threadstate02;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2018/12/9
 * @Modified By:
 */
public class StartDead extends Thread {
    private int i;

    @Override
    public void run() {
        for (; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        StartDead startDead = new StartDead();
        for (int i = 0; i < 300; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                startDead.start();
                System.out.println(startDead.isAlive());
            }
            if (i > 20 && !startDead.isAlive()) {
                /*已经dead状态的thread，再次调用start方法，会抛IllegalThreadStateException。（另外也不能调用两次start方法）
                * */
                startDead.start();
            }
        }
    }
}
