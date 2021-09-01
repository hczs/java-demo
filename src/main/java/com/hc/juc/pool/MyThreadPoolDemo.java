package com.hc.juc.pool;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

/**
 * @author ：hc
 * @date ：Created in 2021/9/1 21:24
 * @modified By：一般工作中推荐自定义线程池，自定义线程池的使用
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        /**
         * 使用之前先介绍自定义线程池时涉及到的七个参数
         * 1. corePoolSize：线程池的核心线程数，也可以成为常驻线程数
         * 2. maximumPoolSize：能容纳的最大线程数
         * 3. keepAliveTime：空闲线程存活时间
         * 4. unit：存活时间的单位
         * 5. workQueue：存放提交但未执行任务的阻塞队列
         * 6. threadFactory：创建线程的工厂类
         * 7. handler：等待队列满之后的拒绝策略
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,
                6,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new AbortPolicy());

        for (int i = 0; i < 8; i++) {
            threadPoolExecutor.execute( () -> {
                System.out.println(Thread.currentThread().getName() + " 在执行");
            });
        }
    }
}
