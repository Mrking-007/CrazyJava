package com.lifeinfinity.company.multiplythread.threadsynchronization04.notoptimized;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description: 可变类总是线程安全的（对象状态不可改变），不可变类需要同步代码块、同步方法、lock作为线程安全保障
 * 该类的对象可以被多个线程安全地访问
 * 每个线程调用该对象的任意方法之后都将得到正确的结果
 * 每个线程调用该对象的任意方法之后，该对象状态怡然保持合理状态
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

    /**
     * run方法体不具有线程同步安全性-程序中有两个并发线程在修改Account对象。
     */
   /* @Override
    public void run() {
        if (account.getBalance() > drawMoney) {
            System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票" + drawMoney);
            //线程暂停，增加线程并发取钱操作，线程不安全的执行可能
            try {
                Thread.sleep(1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            account.setBalance(account.getBalance() - drawMoney);
            System.out.println("\t 余额为：" + account.getBalance());
        } else {
            System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足" + drawMoney);
        }
    }*/

    /**
     * 使用同步代码块，利用account对象作为同步监视器，任何线程进入下面同步代码块之前
     * 必须先获得对account账户的锁定-其他线程无法获得锁，也就无法修改它
     * 这种做法符合：“加锁->修改->释放锁”的逻辑
     * 虽然java程序允许使用任何对象作为同步监视器，同步监视器的目的：阻止两个线程对同一个共享资源进行并发访问，通常使用可能被并发访问的共享资源充当同步监视器
     */
   /* @Override
    public void run() {
        synchronized (account) {
            if (account.getBalance() > drawMoney) {
                System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票" + drawMoney);
                try {
                    Thread.sleep(1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                account.setBalance(account.getBalance() - drawMoney);
                System.out.println("\t 余额为：" + account.getBalance());
            } else {
                System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足" + drawMoney);
            }
        }
    }*/

    public static void main(String[] args) {
        Account account = new Account("编号75727", 1000);
        DrawThread dt1 = new DrawThread(account, 800, "甲");
        DrawThread dt2 = new DrawThread(account, 800, "乙");
        dt1.start();
        dt2.start();
    }
}
