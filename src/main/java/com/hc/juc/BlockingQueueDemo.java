package com.hc.juc;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author ：hc
 * @date ：Created in 2021/9/1 7:13
 * @modified By：阻塞队列，核心方法演示
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        addAndRemove();
        offerAndPoll();
        putAndTake();
    }

    /**
     * add，如果队满会抛异常，放成功会返回true
     * remove，如果队空会抛异常，取成功会返回值
     * element，返回队头元素，空的话会抛异常
     */
    public static void addAndRemove() {
        System.out.println("==========================add and remove============================");
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // 再放会抛异常
//        System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // 再取也会抛异常
//        System.out.println(blockingQueue.remove());
        // 没有元素会抛异常
//        System.out.println(blockingQueue.element());
    }

    /**
     * offer：往队里放元素，成功返回true，失败返回false，可以设置超时时间，超过多长时间还放不进去就不放了
     * poll：取元素，成功返回值，失败返回fnull，可以设置超时时间，超过多长时间还取不出来就不取了
     * peek：取队头元素，空的话会返回空
     */
    public static void offerAndPoll() throws InterruptedException {
        System.out.println("==========================offer and poll============================");
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // 返回false
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        // 返回null
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // 超过三秒放不进去就不放了
//        System.out.println(blockingQueue.offer("d", 3, TimeUnit.SECONDS));
    }

    /**
     * put：放元素，如果队满了就一直处于阻塞状态，直到有地方了，然后放进去，结束
     * take：取元素，如果队空也会一直处于阻塞状态，直到有元素了，然后取出，结束
     */
    public static void putAndTake() throws InterruptedException {
        System.out.println("==========================put and take============================");
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        // 将会一直处于阻塞状态
//        blockingQueue.put("d");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        // 同理，将会处于阻塞状态
        System.out.println(blockingQueue.take());
    }



}
