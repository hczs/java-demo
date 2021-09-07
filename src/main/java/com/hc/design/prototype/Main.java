package com.hc.design.prototype;

import com.hc.design.prototype.framework.Manager;
import com.hc.design.prototype.framework.Product;

/**
 * @author ：hc
 * @date ：Created in 2021/9/7 20:06
 * @modified By：
 */
public class Main {

    public static void main(String[] args) {
        // 准备
        Manager manager = new Manager();
        UnderlinePen underlinePen = new UnderlinePen('~');
        MessageBox mBox = new MessageBox('*');
        MessageBox sBox = new MessageBox('/');
        manager.register("strong message", underlinePen);
        manager.register("warning box", mBox);
        manager.register("slash box", sBox);

        // 生成
        Product p1 = manager.create("strong message");
        p1.use("hello world!");

        Product p2 = manager.create("warning box");
        p2.use("hello world!");

        Product p3 = manager.create("slash box");
        p3.use("hello world!");


    }
}
