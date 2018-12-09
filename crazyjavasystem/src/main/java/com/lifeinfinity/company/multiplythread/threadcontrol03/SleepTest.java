package com.lifeinfinity.company.multiplythread.threadcontrol03;

import java.util.Date;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description: 通常用于当前线程的暂停执行，使当前线程从运行状态转入阻塞状态，阻塞时间完成后立刻计入就绪状态，等待cpu分配资源。
 * @Date: Created on 2018/12/9
 * @Modified By:
 */
public class SleepTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            Thread.sleep(1000);
        }
    }
}
