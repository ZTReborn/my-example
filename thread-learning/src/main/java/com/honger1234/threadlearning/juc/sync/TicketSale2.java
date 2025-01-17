package com.honger1234.threadlearning.juc.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 锁
 * 非公平锁:可以插队(无参构造方法默认为非公平锁)
 * 公平锁:先来后到(有参构造方法传值true时为公平锁)
 */
public class TicketSale2 {

    public static void main(String[] args) {
        //并发:多线程操作同一个资源类,把资源丢入线程
        Ticket2 ticket = new Ticket2();
        //@FunctionalInterface 函数式接口,jkd1.8 lambda 表达式(参数)->{代码}
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

/*
 * lock三部曲
 * 1.new ReentrantLock()
 * 2.lock.lock();//加锁
 * 3.finally-> lock.unlock() //解锁
 * */
class Ticket2 {
    //属性  方法
    private int number = 30;
    //卖票的方式
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();//加锁
        try {
            //业务代码
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票,剩余" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//解锁
        }
    }
}
