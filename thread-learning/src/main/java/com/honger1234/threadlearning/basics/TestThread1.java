package com.honger1234.threadlearning.basics;

/**
 * 使用继承Thread类创建线程
 */
public class TestThread1 extends Thread{

    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println("我在撸代码"+i);
        }
    }

    public static void main(String[] args) {
        TestThread1 testThread1 = new TestThread1();
        testThread1.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("我在学习"+i);
        }
    }
}
