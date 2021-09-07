package com.hc.design.prototype.framework;

import java.util.HashMap;

/**
 * Client角色负责使用复制实例的方法生成新的实例
 * @author ：hc
 * @date ：Created in 2021/9/7 20:18
 * @modified By：
 */
public class Manager {

    private final HashMap<String, Product> showCase = new HashMap<>();

    public void register(String name, Product proto) {
        showCase.put(name, proto);
    }

    public Product create(String protoName) {
        Product product = showCase.get(protoName);
        return product.createClone();
    }
}
