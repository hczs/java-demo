package com.hc.basics.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ：hc
 * @date ：Created in 2021/10/17 21:45
 * @modified ：
 */
public class MyInvocationHandler implements InvocationHandler {

    private final Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 打印方法执行信息
        System.out.println(target.getClass().getName() + "." + method.getName());
        // 前置通知
        System.out.println("记录日志");
        Object result = method.invoke(target, args);
        System.out.println("清理数据");
        return result;
    }

    public Object getProxy() {
        // target.getClass().getInterfaces() 这个是获取目标对象实现的接口，也就是jdk的动态代理的对象必须得实现至少一个接口才可以被代理
        // 第三个参数是一个InvocationHandler，可以写到方法里，也可以以这种形式写下来，实现接口然后this
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}
