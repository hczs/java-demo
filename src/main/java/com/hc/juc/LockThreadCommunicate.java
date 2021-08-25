package com.hc.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock实现
 * 线程间通信demo，两个线程交替执行加一减一操作
 * @author ：hc
 * @date ：Created in 2021/8/25 22:34
 * @modified By：
 */
class LockShare {
    private int number = 0;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void incr() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number += 1;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            // 通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decr() throws InterruptedException {
        lock.lock();
        try {
            while (number != 1) {
                condition.await();
            }
            number -= 1;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
public class LockThreadCommunicate {

    public static void main(String[] args) {
        LockShare lockShare = new LockShare();

        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lockShare.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lockShare.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lockShare.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lockShare.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
