package com.hc.basics.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ：hc
 * @date ：Created in 2021/10/17 22:14
 * @modified ：
 */
public class CglibProxyFactory implements MethodInterceptor {

    /**
     * 工具类
     */
    private final Enhancer enhancer = new Enhancer();

    private final Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        // 设置要创建子类的父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调
        enhancer.setCallback(this);
        // 通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 打印方法执行信息
        System.out.println(target.getClass().getName() + "." + method.getName());
        // 前置通知
        System.out.println("记录日志");
        Object result = method.invoke(target, args);
        System.out.println("清理数据");
        return result;
    }
}
