package com.hc.basics.staticproxy;

/**
 * @author ：hc
 * @date ：Created in 2021/10/17 21:35
 * @modified ：
 */
public class HelloServiceProxy implements HelloService{

    private final HelloService target;

    public HelloServiceProxy(HelloService target) {
        this.target = target;
    }

    @Override
    public void say() {
        System.out.println("记录日志");
        target.say();
        System.out.println("清理数据");
    }
}
