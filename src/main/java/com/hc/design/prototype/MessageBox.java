package com.hc.design.prototype;

import com.hc.design.prototype.framework.Product;


/**
 * ConcretePrototype角色负责实现复制现有实例的方法并生成新实例的方法。
 * @author ：hc
 * @date ：Created in 2021/9/7 20:48
 * @modified By：
 */
public class MessageBox implements Product {

    private final char decochar;

    public MessageBox(char decochar) {
        this.decochar = decochar;
    }

    @Override
    public void use(String s) {
        int length = s.getBytes().length;
        for (int i = 0; i < length + 4; i++) {
            System.out.print(decochar);
        }
        System.out.println("");
        System.out.println(decochar + " " + s + " " + decochar);
        for (int i = 0; i < length + 4; i++) {
            System.out.print(decochar);
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
