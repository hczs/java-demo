package com.hc.design.adapter.objectadapter;

/**
 * @author ：hc
 * @date ：Created in 2021/9/5 15:44
 * @modified By：
 */
public class Main {

    public static void main(String[] args) {
        // 对于main类而言，
        Print print = new PrintBanner("hello!");
        print.printWeak();
        print.printStrong();
    }
}
