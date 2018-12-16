package com.lifeinfinity.company.multiplythread.threadsynchronization04.optimized;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description: 同步方法就是使用synchronized关键字修饰方法，对于实例方法而言(非static方法)锁住的是该方法所在的实例对象，对于静态方法，锁住的是类对象
 * 参见：https://www.jianshu.com/p/d53bf830fa09
 * @Date: Created on 2018/12/11
 * @Modified By:
 */
public class Account {
    private String accountNo;
    private double balance;

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

    public synchronized void draw(double drawMoney) {
        if (balance >= drawMoney) {
            System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票" + drawMoney);
            this.balance = this.getBalance() - drawMoney;
            System.out.println("\t 余额为：" + this.getBalance());
        } else {
            System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足" + drawMoney);
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
