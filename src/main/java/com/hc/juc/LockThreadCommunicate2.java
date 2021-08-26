package com.hc.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock实现进程定制化通信
 * A线程打印2次
 * 随后紧跟B线程打印3次
 * 随后紧跟C线程打印5次
 * 然后A再打印两次...循环2轮
 * 利用flag标志位来控制该谁操作了
 * @author ：hc
 * @date ：Created in 2021/8/26 21:19
 * @modified By：
 */
class LockShare2 {

    private int flag = 1;
    private final ReentrantLock lock = new ReentrantLock();

    private final Condition c1 = lock.newCondition();
    private final Condition c2 = lock.newCondition();
    private final Condition c3 = lock.newCondition();


    /**
     * 打印两次线程名
     * @throws InterruptedException
     */
    public void print2() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1) {
                c1.await();
            }
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + "printOK!");
            }
            // 通知B线程
            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * B线程打印三次
     * @throws InterruptedException
     */
    public void print3() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2) {
                c2.await();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + "printOK!");
            }
            // 通知C线程
            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * C线程打印五次
     * @throws InterruptedException
     */
    public void print5() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3) {
                c3.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + "printOK!");
            }
            // 通知A线程
            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }

}
public class LockThreadCommunicate2 {

    public static void main(String[] args) {
        LockShare2 lockShare2 = new LockShare2();

        new Thread( ()-> {
            for (int i = 0; i < 2; i++) {
                try {
                    lockShare2.print2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread( ()-> {
            for (int i = 0; i < 2; i++) {
                try {
                    lockShare2.print3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread( ()-> {
            for (int i = 0; i < 2; i++) {
                try {
                    lockShare2.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }
}
