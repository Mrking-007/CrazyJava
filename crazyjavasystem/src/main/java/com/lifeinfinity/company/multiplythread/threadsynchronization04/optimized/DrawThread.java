package com.lifeinfinity.company.multiplythread.threadsynchronization04.optimized;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2018/12/11
 * @Modified By:
 */
public class DrawThread extends Thread {
    private Account account;
    private double drawMoney;

    public DrawThread(Account account, double drawMoney, String threadName) {
        super(threadName);
        this.account = account;
        this.drawMoney = drawMoney;
    }

    @Override
    public void run() {
        account.draw(drawMoney);
    }


    public static void main(String[] args) {
        Account account = new Account("编号75727", 1000);
        DrawThread dt1 = new DrawThread(account, 800, "甲");
        DrawThread dt2 = new DrawThread(account, 800, "乙");
        dt1.start();
        dt2.start();
    }
}
