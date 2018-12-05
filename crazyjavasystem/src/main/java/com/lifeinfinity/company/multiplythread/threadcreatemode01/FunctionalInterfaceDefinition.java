package com.lifeinfinity.company.multiplythread.threadcreatemode01;

/**
 * @author T450
 * @Created By: zhangyuhang@gridsum.com
 * @Description:
 *  什么是函数式接口（Functional Interface）
 其实之前在讲Lambda表达式的时候提到过，所谓的函数式接口，当然首先是一个接口，然后就是在这个接口里面只能有一个抽象方法。
 这种类型的接口也称为SAM接口，即Single Abstract Method interfaces。
 它们主要用在Lambda表达式和方法引用（实际上也可认为是Lambda表达式）上。
 *  函数式接口用途
 它们主要用在Lambda表达式和方法引用（实际上也可认为是Lambda表达式）上。
 *  JDK中的函数式接口举例
 java.lang.Runnable,
 java.awt.event.ActionListener,
 java.util.Comparator,
 java.util.concurrent.Callable
 java.util.function包下的接口，如Consumer、Predicate、Supplier等
参见：https://www.cnblogs.com/runningTurtle/p/7092632.html
      https://blog.csdn.net/followwwind/article/details/78211531
 *
 * @Date: Created on 2018/12/2
 * @Modified By:
 */
//@FunctionalInterface
public interface FunctionalInterfaceDefinition {

    /**
     * 定义抽象方法
     */
    abstract void sayHello();

    /**
     * 默认方法
     */
    default void doSomeMoreWork1() {
        // Method body
    }

    /**
     * 默认方法
     */
    default void doSomeMoreWork2() {
        // Method body
    }

    /**
     * 静态方法
     */
    static void printHello(){
        System.out.println("Hello");
    }

    @Override
    boolean equals(Object obj);
}
