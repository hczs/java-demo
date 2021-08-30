package com.hc.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ：hc
 * @date ：Created in 2021/8/30 22:51
 * @modified By： FutureTask使用
 * futureTask特性，只计算一次，计算结果再次get时，无需等待，直接获取
 * 可以理解为异步，在一批计算任务中，可以把一个耗时任务让futureTask计算，然后主线程继续做其他计算
 */

class MyThread1 implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " come in callable");
        return 200;
    }
}

public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());

        new Thread(futureTask, "A").start();
        while (!futureTask.isDone()) {
            System.out.println("waiting...");
        }
        System.out.println(futureTask.get());
        System.out.println(futureTask.get());
    }
}
