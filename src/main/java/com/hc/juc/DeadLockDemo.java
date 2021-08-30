package com.hc.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author ：hc
 * @date ：Created in 2021/8/30 22:18
 * @modified By：演示死锁的情况
 * 两个线程，一个线程拿上锁a，另一个线程拿上锁b，然后互相等待，导致死锁
 * 如何验证死锁？
 * 命令行下：jps -l 列出当前所有java进程，找到进程号
 * 然后用jstack 进程号，可以查看详情，可以看到最后有输出found 1 deadlock
 */
public class DeadLockDemo {

    static Object a = new Object();
    static Object b = new Object();

    public static void main(String[] args) {
        new Thread( () -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "有锁a，尝试获取锁b");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + "获取锁b");
                }
            }
        }, "线程A").start();

        new Thread( () -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "有锁b，尝试获取锁a");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + "获取锁a");
                }
            }
        }, "线程B").start();
    }
}
