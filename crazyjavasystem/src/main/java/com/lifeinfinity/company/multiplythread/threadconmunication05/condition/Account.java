package com.lifeinfinity.company.multiplythread.threadconmunication05.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Created By: zhangyuhang@gridsum.com
 * @Description:
 * @Date: Created on 2018/12/13
 * @Modified By:
 */
public class Account {

    private String accountNo;
    private double balance;
    //false可以存钱 true可以取钱
    private boolean flag = false;
    //显式定义lock对象
    private final ReentrantLock lock = new ReentrantLock();
    //获取lock对象的Condition对象
    private final Condition condition = lock.newCondition();

    /**
     * 账户编号
     *
     * @return
     */
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * 账户余额
     *
     * @return
     */
    public double getBalance() {
        return balance;
    }

    public Account() {
    }

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public void draw(double drawMoney) throws InterruptedException {
        lock.lock();
        try {
            // 如果flag为假，表明账户中还没有人存钱进去，调用await方法则当前线程阻塞，并释放lock锁对象，让其他线程可以继续访问该对象draw方法
            if (!flag) {
                condition.await();
            } else {
                // 执行取钱
                if (balance >= drawMoney) {
                    System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票" + drawMoney);
                    this.balance = this.getBalance() - drawMoney;
                    System.out.println("\t 余额为：" + this.getBalance());
                } else {
                    System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足" + drawMoney);
                }
                // 将标识账户是否已有存款的旗标设为false。
                flag = false;
                //唤醒其他线程，进入就绪状态
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void deposit(double depositMoney) throws InterruptedException {
        lock.lock();
        try {
            // 如果flag为真，表明账户中已有人存钱进去，调用await方法则当前线程阻塞，并释放lock锁对象，让其他线程可以继续访问该对象draw方法
            if (flag) {
                condition.await();
            } else {
                // 执行存款
                System.out.println(Thread.currentThread().getName() + "存钱成功！存入钞票" + depositMoney);
                balance = balance + depositMoney;
                System.out.println("\t 余额为：" + this.getBalance());
                // 将表示账户是否已有存款的旗标设为true
                flag = true;
                // 唤醒其他线程
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int hashCode() {
        return accountNo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == Account.class) {
            Account target = (Account) obj;
            return this.accountNo.equals(target.getAccountNo());
        }
        return false;
    }
}
