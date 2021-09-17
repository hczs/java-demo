package com.hc.design.bridge;

/**
 * @author ：hc
 * @date ：Created in 2021/9/17 22:22
 * @modified By：
 */
public class Display {

    private DisplayImpl impl;

    public Display(DisplayImpl impl) {
        this.impl = impl;
    }

    public void open() {
        impl.rawOpen();
    }

    public void print() {
        impl.rawPrint();
    }

    public void close() {
        impl.rawClose();
    }

    public final void display() {
        open();
        print();
        close();
    }

}
