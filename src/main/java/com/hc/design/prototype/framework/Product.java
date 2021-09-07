package com.hc.design.prototype.framework;

/**
 * Prototype（原型）
 * Prototype角色负责定义用于复制现有实例来生成新实例的方法
 * @author ：hc
 * @date ：Created in 2021/9/7 20:15
 * @modified By：
 */
public interface Product extends Cloneable{

    /**
     * 使用
     * @param s s
     */
    void use(String s);

    /**
     * 通过复制创建产品
     * @return Product
     */
    Product createClone();
}
