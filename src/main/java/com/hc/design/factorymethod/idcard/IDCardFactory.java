package com.hc.design.factorymethod.idcard;

import com.hc.design.factorymethod.framework.Factory;
import com.hc.design.factorymethod.framework.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体加工方
 * @author ：hc
 * @date ：Created in 2021/9/6 21:00
 * @modified By：
 */
public class IDCardFactory extends Factory {

    private final List<IDCard> owners = new ArrayList<>();

    @Override
    public Product createProduct(String owner) {
        return new IDCard(owner);
    }

    @Override
    public void registerProduct(Product product) {
        owners.add((IDCard) product);
    }

    public List<IDCard> getOwners() {
        return this.owners;
    }
}
