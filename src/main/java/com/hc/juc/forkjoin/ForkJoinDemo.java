package com.hc.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 累加任务
 */
class MyTask extends RecursiveTask<Integer> {
    private final int start;
    private final int end;
    private int sum;

    /**
     * 构造函数
     * @param start start
     * @param end end
     */
    public MyTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 计算任务
     * @return result
     */
    @Override
    protected Integer compute() {
        System.out.println("任务" + start + "============================" + end + "累加开始");
        // 相差不超过十，直接加即可
        if (end - start <= 10) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 其他情况得拆分再加
            int middle = (end + start) / 2;
            // 拆分左边任务
            MyTask myTask01 = new MyTask(start, middle);
            // 拆分右边任务
            MyTask myTask02 = new MyTask(middle + 1, end);
            myTask01.fork();
            myTask02.fork();
            // 合并
            sum = myTask01.join() + myTask02.join();
        }
        return sum;
    }
}
/**
 * @author ：hc
 * @date ：Created in 2021/9/4 10:03
 * @modified By：ForkJoin使用 Fork/Join是一种基于“分治”的算法：通过分解任务，并行执行，最后合并结果得到最终结果。
 */
public class ForkJoinDemo {

    public static void main(String[] args) {
        // 定义任务
        MyTask myTask = new MyTask(0, 100);
        // 定义执行对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 加入任务执行
        ForkJoinTask<Integer> res = forkJoinPool.submit(myTask);
        try {
            System.out.println(res.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            forkJoinPool.shutdown();
        }
    }
}
