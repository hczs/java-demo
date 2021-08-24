package com.hc.juc;

/**
 * @author ：hc
 * @date ：Created in 2021/8/22 15:58
 * @modified By：
 */
class Ticket {

    private int number = 30;

    /**
     * 卖票，调用一次减少一张票
     */
    public synchronized void sale() {
        if (number > 0) {
            number--;
            System.out.println(Thread.currentThread().getName() + "卖出一张票" + "剩余：" + number);
        }
    }
}
public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    ticket.sale();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();
    }
}
