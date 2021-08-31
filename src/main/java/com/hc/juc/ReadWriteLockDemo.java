package com.hc.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ：hc
 * @date ：Created in 2021/8/31 21:22
 * @modified By：ReadWriteLock读写锁的使用
 * 封装一个MyCache类，然后五个线程并发写，五个线程并发读
 * 如果不加读写锁的时候，会发现还没写入完毕呢，就已经读取完毕了，有这种情况发生
 * 加读写锁后，写会依次写入，因为写锁是独占锁，读锁会并发读，因为读锁是共享锁
 */

class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println("线程 " + Thread.currentThread().getName() + "正在写操作，写的key是：" + key);
            // 为了效果明显，可以暂停一下
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key, value);
            System.out.println("线程 " + Thread.currentThread().getName() + "写入完毕，写的key是：" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        Object res = null;
        readWriteLock.readLock().lock();
        try {
            System.out.println("线程 " + Thread.currentThread().getName() + "正在读操作，读的key是：" + key);
            TimeUnit.MICROSECONDS.sleep(300);
            res = map.get(key);
            System.out.println("线程 " + Thread.currentThread().getName() + " 读取完毕，读的key是：" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().lock();
        }
        return res;
    }
}
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            int num = i;
            new Thread( ()-> {
                myCache.put(num + "", num);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            int num = i;
            new Thread( ()-> {
                myCache.get(num + "");
            }, String.valueOf(i)).start();
        }
    }
}
