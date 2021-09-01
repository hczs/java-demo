package com.hc.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：hc
 * @date ：Created in 2021/9/1 21:02
 * @modified By：juc自带三种ThreadPool线程池使用
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 创建一个指定大小的线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // 创建一个只有一个线程的线程池
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        // 创建一个可扩容的线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 8; i++) {
                threadPool.execute( () -> {
                    System.out.println(Thread.currentThread().getName() + " 在执行");
                });
            }
        } finally {
            threadPool.shutdown();
        }

    }
}
