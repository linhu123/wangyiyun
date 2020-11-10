package com.linhu.chapter2.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class HashMap_Demo {
    private final Map<String,Object> map = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock r = readWriteLock.readLock();

    private final Lock w = readWriteLock.writeLock();

    public Object get(String key){
        r.lock();
        try{
            return map.get(key);
        }finally {
            r.unlock();
        }
    }

    public void put(String key,Object obj){
        w.lock();
        try{
            map.put(key,obj);
        }finally {
            w.unlock();
        }
    }

    public Object[] allKeys(){
        r.lock();
        try{
            return map.keySet().toArray();
        }finally {
            r.unlock();
        }
    }

    public void clear(){
        w.lock();
        try{
            map.clear();
        }finally {
            w.unlock();
        }
    }


}
