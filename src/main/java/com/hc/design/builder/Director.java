package com.hc.design.builder;


/**
 * 使用Builder类中声明的方法来编写文档
 * @author ：hc
 * @date ：Created in 2021/9/8 20:46
 * @modified By：
 */
public class Director {

    private final Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.makeTitle("Greeting");
        builder.makeString("从早上到下午");
        builder.makeItems(new String[]{"早上好！", "下午好！"});
        builder.makeString("晚上");
        builder.makeItems(new String[]{"晚上好！", "晚安！", "再见！"});
        builder.close();
    }
}
