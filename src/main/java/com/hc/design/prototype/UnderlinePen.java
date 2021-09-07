package com.hc.design.prototype;

import com.hc.design.prototype.framework.Product;


/**
 * ConcretePrototype角色负责实现复制现有实例的方法并生成新实例的方法。
 * @author ：hc
 * @date ：Created in 2021/9/7 20:58
 * @modified By：
 */
public class UnderlinePen implements Product {

    private final char ulchar;

    public UnderlinePen(char ulchar) {
        this.ulchar = ulchar;
    }


    @Override
    public void use(String s) {
        int length = s.getBytes().length;
        System.out.println("\"" + s + "\"");
        for (int i = 0; i < length; i++) {
            System.out.print(ulchar);
        }
        System.out.println("");
    }

    @Override
    public Product createClone() {
        Product p = null;
        try {
            p = (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}
