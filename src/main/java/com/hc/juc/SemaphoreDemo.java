package com.hc.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author ：hc
 * @date ：Created in 2021/8/31 20:54
 * @modified By：Semaphore使用
 * 三个车位，六辆车，停车
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        // 三个停车位，设置三个许可
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread( () -> {
                try {
                    // 占用车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢占车位");
                    // 随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(6));
                    System.out.println(Thread.currentThread().getName() + " 离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放许可
                    semaphore.release();
                }
            }, String.valueOf(i+1)).start();
        }
    }
}
