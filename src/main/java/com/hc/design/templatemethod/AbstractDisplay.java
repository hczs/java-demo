package com.hc.design.templatemethod;

/**
 * 抽象类角色，不仅负责实现模板方法（display），还要负责声明在模板方法中所使用到的抽象方法（open、print、close）
 * 这些抽象方法由子类去实现
 * @author ：hc
 * @date ：Created in 2021/9/6 20:04
 * @modified By：
 */
public abstract class AbstractDisplay {

    /**
     * open，交给子类实现
     */
    public abstract void open();

    /**
     * print，交给子类实现
     */
    public abstract void print();

    /**
     * close，交给子类实现
     */
    public abstract void close();

    /**
     * 定义处理流程的框架
     */
    public final void display() {
        open();
        for (int i = 0; i < 5; i++) {
            print();
        }
        close();
    }
}
