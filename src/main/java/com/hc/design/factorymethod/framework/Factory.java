package com.hc.design.factorymethod.framework;

/**
 * 属于框架的一方，负责生成Product角色的抽象类，里面不用new来生成实例，而是调用生成实例的具体方法来生成实例，
 * 防止父类和其他类耦合，具体咋创建实例的实现也是由子类去写，创建者只是定义一个框架，比如创建完实例应该干嘛如何如何
 * @author ：hc
 * @date ：Created in 2021/9/6 20:53
 * @modified By：
 */
public abstract class Factory {

    /**
     * 定义创建product的模板
     * @param owner owner
     * @return Product
     */
    public final Product create(String owner) {
        Product product = createProduct(owner);
        registerProduct(product);
        return product;
    }

    /**
     * 交由子类实现具体创建什么产品
     * @param owner owner
     * @return Product
     */
    public abstract Product createProduct(String owner);

    /**
     * 交给子类实现具体怎么注册产品
     * @param product product
     */
    public abstract void registerProduct(Product product);
}
