package com.hc.basics.dynamicproxy.jdk;

/**
 * @author ：hc
 * @date ：Created in 2021/10/17 22:00
 * @modified ：
 */
public class UserServiceImpl implements UserService{

    @Override
    public void getName() {
        System.out.println("this method is getName!");
    }
}
