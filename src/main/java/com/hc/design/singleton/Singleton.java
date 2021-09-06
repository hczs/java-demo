package com.hc.design.singleton;

/**
 * 单例模式，全局只返回一个实例，暴漏一个给外界获取实例的方法
 * @author ：hc
 * @date ：Created in 2021/9/6 21:39
 * @modified By：
 */
public class Singleton {

    private static final Singleton SINGLETON = new Singleton();

    private Singleton() {
        System.out.println("生成了一个实例！");
    }

    public static Singleton getInstance() {
        return SINGLETON;
    }
}
