package com.hc.basics.staticproxy;

/**
 * @author ：hc
 * @date ：Created in 2021/10/17 21:35
 * @modified ：
 */
public class HelloServiceImpl implements HelloService{
    @Override
    public void say() {
        System.out.println("hello!");
    }
}
