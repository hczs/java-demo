package com.hc.design.factorymethod.framework;

/**
 * 属于框架的一方，定义了工厂方法模式中生成的实例所有的接口
 * @author ：hc
 * @date ：Created in 2021/9/6 20:50
 * @modified By：
 */
public abstract class Product {

    /**
     * 任意产品都是可以use的
     */
    public abstract void use();
}
