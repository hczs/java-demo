package com.hc.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ：hc
 * @date ：Created in 2021/8/31 7:22
 * @modified By：循环栅栏CyclicBarrier使用 demo
 * 集齐七颗龙珠就可以召唤神龙
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙！");
        });

        for (int i = 0; i < 7; i++) {
            new Thread( () -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 个龙珠被集齐");
                    // 集不齐线程就一直等待
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i+1)).start();
        }
    }
}
