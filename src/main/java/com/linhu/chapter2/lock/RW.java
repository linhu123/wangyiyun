package com.linhu.chapter2.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class RW {
//   new ReentrantReadWriteLock()
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Lock lock = new ReentrantLock();

    private volatile Integer i = -1;

    public Integer read(){
        lock.lock();
//        readWriteLock.readLock().lock();
        Integer t = i;
        lock.unlock();
//        readWriteLock.readLock().unlock();
        return t;
    }

    public void write(){
        lock.lock();
//        readWriteLock.writeLock().lock();
        i++;
        lock.unlock();
//        readWriteLock.writeLock().unlock();
    }


    public static void main(String[] args) {
        final RW rw = new RW();
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            int n = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(Thread.currentThread().getName()+" run");
                    for (int j = 0; j < 100000; j++) {
//                        System.out.println(Thread.currentThread().getName()+"======"+j);
                        if (n%3==0){
                            rw.write();
                        }else {
                            rw.read();
                        }
                    }
                }
            }).start();
        }
        long l1 = System.currentTimeMillis();
        System.out.println("spent time is "+(l1-l));
    }

}
