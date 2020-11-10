package com.linhu.chapter2.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Reentrant_Demo {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        lock.lock();
        System.out.println(Thread.currentThread().getName()+"第一次luck");
        Thread.sleep(1000L);

        lock.lock();
        System.out.println(Thread.currentThread().getName()+"第2次luck");
        Thread.sleep(1000L);

        lock.unlock();
        lock.unlock();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"要去获取锁");
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"已经获取锁");
        }).start();
    }
}
