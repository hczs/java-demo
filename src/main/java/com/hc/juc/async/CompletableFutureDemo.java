package com.hc.juc.async;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

/**
 * CompletableFuture使用，无返回值的异步，有返回值的异步
 * @author ：hc
 * @date ：Created in 2021/9/4 11:36
 * @modified By：
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("主线程做了点任务");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new AbortPolicy());
        // 没有返回值的异步
        CompletableFuture<Void> future = CompletableFuture.runAsync( () -> {
            System.out.println("子线程异步做任务！");
            try {
                Thread.sleep(5000);
                System.out.println("子线程完成任务！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, threadPoolExecutor);
        // 有返回值的异步
        CompletableFuture<String> stringFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回值的子线程开始任务");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "有返回值的子线程做完任务了！";
        }, threadPoolExecutor);
        System.out.println("主线程继续做其他任务");
        System.out.println(stringFuture.get());
    }
}
