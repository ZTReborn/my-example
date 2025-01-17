package com.honger1234.threadlearning.juc.util;

import java.util.concurrent.CountDownLatch;

/**
 * 工具类CountDownLatch减法计数器
 */
//计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //总数是6,必须要执行任务的时候再使用
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " Go out");
                countDownLatch.countDown();//数量-1
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();//等待计数器归零,然后再往下执行
        System.out.println("Close Door");
    }
}
