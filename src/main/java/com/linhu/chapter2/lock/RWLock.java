package com.linhu.chapter2.lock;

import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RWLock {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();

        new Thread(()->{
            lock.lock();
            while (true){
                System.out.println(Thread.currentThread().getName());
                try {
                    lock.wait(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            lock.lock();
            while (true){
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }).start();


    }
}
