package com.hc.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * lock实现
 * @author ：hc
 * @date ：Created in 2021/8/25 21:47
 * @modified By：
 */
class LockTicket {

    private int number = 30;

    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 卖票，调用一次少一张
     */
    public void sale() {
        // 上锁
        lock.lock();
        try {
            if (number > 0) {
                number--;
                System.out.println(Thread.currentThread().getName() + "卖出一张票" + "剩余：" + number);
            }
        } finally {
            lock.unlock();
        }
    }
}
public class LockSaleTicket {

    public static void main(String[] args) {

        LockTicket ticket = new LockTicket();

        // 创建三个线程操作测试
        new Thread( () -> {
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread( () -> {
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread( () -> {
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        }, "C").start();
    }

}
