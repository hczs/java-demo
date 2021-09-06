package com.hc.design.factorymethod.idcard;

import com.hc.design.factorymethod.framework.Product;

/**
 * 具体的产品
 * @author ：hc
 * @date ：Created in 2021/9/6 20:57
 * @modified By：
 */
public class IDCard extends Product {

    private final String owner;

    public IDCard(String owner) {
        System.out.println("制作" + owner + "的ID卡。");
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.println("使用" + owner + "的ID卡。");
    }

    public String getOwner() {
        return owner;
    }
}
