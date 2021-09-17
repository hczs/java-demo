package com.hc.design.bridge;

/**
 * @author ：hc
 * @date ：Created in 2021/9/17 22:26
 * @modified By：
 */
public class CountDisplay extends Display{

    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }

    /**
     * 循环显示times次
     * @param times 循环显示次数
     */
    public void multiDisplay(int times) {
        open();
        for (int i = 0; i < times; i++) {
            print();
        }
        close();
    }
}
