package com.hc.design.singleton;

/**
 * @author ：hc
 * @date ：Created in 2021/9/6 21:42
 * @modified By：
 */
public class Main {

    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
    }
}
