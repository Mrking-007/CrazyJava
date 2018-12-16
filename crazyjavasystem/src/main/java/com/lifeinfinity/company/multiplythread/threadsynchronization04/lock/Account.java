package com.lifeinfinity.company.multiplythread.threadsynchronization04.lock;

import java.util.concurrent.locks.*;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description: java5开始，通过显示定义同步锁对象实现同步，功能更为强大。锁提供了对共享资源的独占访问。每次只能有一个线程对Lock对象加锁，线程开始访问共享资源之前应先获得Lock对象。
 * 1、ReentrantLock implements Lock；（比较常用的是可重入锁）
 * 2、ReentrantReadWriteLock implements ReadWriteLock，ReentrantReadWriteLock 为读写提供了三种锁模式：Writing，ReadingOptimistic(乐观)、Reading
 * 3、java8 新型StampedLock 大多数场景中可以替代传统的ReentrantReadWriteLock
 * 4、使用ReentrantLock的代码格式如下：
 *   class x{
 *     //定义锁对象
 *     private final ReentrantLock lock=new ReentrantLock();
 *     //定义需要保证线程安全的方法
 *     public void m(){
 *         //加锁
 *         lock.lock();
 *         try{
 *             //需要保证线程安全的代码
 *         }
 *         finally{
 *             lock.unlock();
 *         }
 *     }
 * }
 * @Date: Created on 2018/12/13
 * @Modified By:
 */
public class Account {

    private final ReentrantLock lock=new ReentrantLock();
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

    public  void draw(double drawMoney) {
        //加锁
        lock.lock();
        try {
            if (balance >= drawMoney) {
                System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票" + drawMoney);
                this.balance = this.getBalance() - drawMoney;
                System.out.println("\t 余额为：" + this.getBalance());
            } else {
                System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足" + drawMoney);
            }
        }
        finally {
            //修改完成，释放锁
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
