package com.lifeinfinity.company.multiplythread.threadconmunication05.waitnotify;

/**
 * @Created By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2018/12/13
 * @Modified By:
 */
public class  TestDrawAndDeposit {
    public static void main(String[] args) {
        Account account = new Account();
        DepositThread depositThread1 = new DepositThread(account, 1000, "存线程1");
        DepositThread depositThread2 = new DepositThread(account, 1000, "存线程2");
        DrawThread drawThread1 = new DrawThread(account, 1000, "取线程1");
        DrawThread drawThread2 = new DrawThread(account, 1000, "取线程2");
        depositThread1.start();
        depositThread2.start();
        drawThread1.start();
        drawThread2.start();
    }
}
