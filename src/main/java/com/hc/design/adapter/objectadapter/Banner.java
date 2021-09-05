package com.hc.design.adapter.objectadapter;

/**
 * @author ：hc
 * @date ：Created in 2021/9/5 13:59
 * @modified By：banner类，提供原始的操作，类比为220v电压
 */
public class Banner {

    private final String string;

    public Banner(String string) {
        this.string = string;
    }

    /**
     * 字符串加括号显示
     */
    public void showWithParen() {
        System.out.println("(" + string + ")");
    }

    /**
     * 字符串两端加星号显示
     */
    public void showWithAster() {
        System.out.println("*" + string + "*");
    }
}
