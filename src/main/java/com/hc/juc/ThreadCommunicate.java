package com.hc.juc;

/**
 * synchronized实现
 * 线程间通信demo，两个线程交替执行加一减一操作
 * @author ：hc
 * @date ：Created in 2021/8/25 22:19
 * @modified By：
 */
class Share {
    private int number = 0;

    public synchronized void incr() throws InterruptedException {
        // 注意wait方法应该始终在循环体中使用，防止虚假唤醒
        while (number != 0) {
            // 在哪里睡，就在哪里醒来，如果用if的话，可能醒来之后条件还是满足if的，应该继续wait，但是不wait了，所以就会出现虚假唤醒
            // 如何防止虚假唤醒？就是用循环来判断条件，醒来之后继续判断，符合条件继续wait
            this.wait();
        }
        number += 1;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        // 通知其他线程，可以操作了
        this.notifyAll();
    }

    public synchronized void decr() throws InterruptedException {
        while (number != 1) {
            this.wait();
        }
        number -= 1;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        // 通知
        this.notifyAll();
    }
}
public class ThreadCommunicate {

    public static void main(String[] args) {
        Share share = new Share();

        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
