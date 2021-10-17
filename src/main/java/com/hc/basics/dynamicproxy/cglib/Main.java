package com.hc.basics.dynamicproxy.cglib;

import com.hc.basics.dynamicproxy.jdk.HelloService;
import com.hc.basics.dynamicproxy.jdk.HelloServiceImpl;

/**
 * @author ：hc
 * @date ：Created in 2021/10/17 22:25
 * @modified ：
 */
public class Main {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        CglibProxyFactory helloServiceProxyFactory = new CglibProxyFactory(helloService);
        HelloService helloServiceProxy = (HelloService) helloServiceProxyFactory.getProxy();
        helloServiceProxy.say();
        // 找一个没有实现接口的类进行代理，上面的helloService实现了HelloService接口
        Student student = new Student();
        CglibProxyFactory studentProxyFactory = new CglibProxyFactory(student);
        Student studentProxy = (Student) studentProxyFactory.getProxy();
        studentProxy.study();
    }
}
