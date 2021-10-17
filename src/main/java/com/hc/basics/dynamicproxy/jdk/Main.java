package com.hc.basics.dynamicproxy.jdk;

/**
 * @author ：hc
 * @date ：Created in 2021/10/17 21:39
 * @modified ：
 */
public class Main {
    public static void main(String[] args) {
        // 目标对象
        HelloService target = new HelloServiceImpl();
        MyInvocationHandler invocationHandler = new MyInvocationHandler(target);
        HelloService proxy = (HelloService) invocationHandler.getProxy();
        proxy.say();
        // 动态代理类的好处，不用为某个类单独建代理对象，换个接口类依然可以用这个handler，只需要创建对象即可
        UserService userService = new UserServiceImpl();
        MyInvocationHandler userServiceHandler = new MyInvocationHandler(userService);
        UserService userServiceProxy = (UserService) userServiceHandler.getProxy();
        userServiceProxy.getName();
    }
}
