package com.lifeinfinity.company.multiplythread.threadconmunication05.condition;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2018/12/13
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
        for (int i = 0; i < 100; i++) {
            try {
                account.draw(drawMoney);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
