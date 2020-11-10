package com.linhu.chapter2.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockQueue {
    private List<Object> array = new ArrayList<Object>();
    private Lock lock = new ReentrantLock();
    private Condition putCondition = lock.newCondition();
    private Condition takeCondition = lock.newCondition();
    private int lenght;

    LockQueue(int lenght) {
        this.lenght = lenght;
    }

    /*
    固定的容器，望里面放东西，满了是push需要阻塞，空的时候take需要被阻塞
     */
    public void put(Object obj) {
        lock.lock();
//        while (true){
        try {
            if (array.size() < lenght) {
                array.add(obj);
                System.out.println("------------------put element " + obj);
                takeCondition.signal();
                return;
            } else {
                putCondition.await();//容器满了 放入的操作需要等待
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
//        }
    }

    public Object take() {
        lock.lock();
        try {
            if (array.size() > 0) {
                Object obj = array.remove(0);
                putCondition.signal();
                System.out.println("take elemenrt " + obj);
                return obj;
            } else {
                takeCondition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            return null;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        LockQueue lockQueue = new LockQueue(5);
        Thread t = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                lockQueue.put(i + "");
            }
        });
        t.start();
        Thread.sleep(2000L);
        for (int i = 0; i < 10; i++) {
            lockQueue.take();
            Thread.sleep(1000L);
        }
    }
}
