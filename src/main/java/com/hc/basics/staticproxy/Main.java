package com.hc.basics.staticproxy;

/**
 * @author ：hc
 * @date ：Created in 2021/10/17 21:39
 * @modified ：
 */
public class Main {
    public static void main(String[] args) {
        // 目标对象
        HelloService target = new HelloServiceImpl();
        // 代理对象
        HelloServiceProxy helloServiceProxy = new HelloServiceProxy(target);
        helloServiceProxy.say();
    }
}
