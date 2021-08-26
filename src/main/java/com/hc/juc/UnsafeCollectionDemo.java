package com.hc.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类线程不安全演示
 * @author ：hc
 * @date ：Created in 2021/8/26 22:04
 * @modified By：
 */
public class UnsafeCollectionDemo {

    public static void main(String[] args) {
        // 演示hashSet线程不安全，也会出现java.util.ConcurrentModificationException
//        HashSet<String> hashSet = new HashSet<>();
        // 解决方案，使用CopyOnWriteArraySet
        CopyOnWriteArraySet<String> hashSet = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread( () -> {
                hashSet.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println("线程" + Thread.currentThread().getName() + ":" + hashSet);
            }, String.valueOf(i)).start();
        }
        // 演示HashMap线程不安全
//        HashMap<String, String> hashMap = new HashMap<>();
        // 解决方案：使用ConcurrentHashMap
        ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread( () -> {
                hashMap.put( UUID.randomUUID().toString().substring(0,2), UUID.randomUUID().toString().substring(0, 8));

                System.out.println("线程" + Thread.currentThread().getName() + ":" + hashMap);
            }, String.valueOf(i)).start();
        }

    }

    /**
     * list不安全演示
     */
    private void unsafeListDemo() {
        // ArrayList线程不安全，会发生 java.util.ConcurrentModificationException，并发修改异常
//        ArrayList<String> list = new ArrayList<>();
        // 解决方案1：使用vector，比较老的方案，Vector方法都是加锁的
//        Vector<String> list = new Vector<>();
        // 解决方案2：使用工具类Collections来创建一个synchronizedList
//        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        // 解决方案3：使用cCopyOnWriteArrayList
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread( () -> {
                // 写入
                list.add(UUID.randomUUID().toString().substring(0, 8));
                // 读取
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
