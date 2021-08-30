package com.hc.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：hc
 * @date ：Created in 2021/8/31 7:04
 * @modified By：CountDownLatch使用demo
 */
public class CountDownLatchDemo {

    /**
     * 模拟六个同学陆续离开教室之后，班长(main)才可以锁门
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        // 不用countDownLatch时，会出现班长锁门，但是还有线程没执行完的情况
//        noCountDownLatch();

        haveCountDownLatch();
    }

    /**
     * 不用countDownLatch的情况
     */
    public static void noCountDownLatch() {
        for (int i = 0; i < 6; i++) {
            new Thread( () -> {
                System.out.println(Thread.currentThread().getName() + " 号同学离开教室");
            }, String.valueOf(i)).start();
        }

        System.out.println(Thread.currentThread().getName() + " 班长锁门");
    }

    /**
     * 使用countDownLatch
     */
    public static void haveCountDownLatch() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread( () -> {
                System.out.println(Thread.currentThread().getName() + " 号同学离开教室");
                // countDownLatch计数减一，上面输出可以看作是计算任务，线程完成任务之后就减一通知一下
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        // 持续等待
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " 班长锁门");
    }
}
