package com.hc.design.adapter.classadapter;

/**
 * 扮演适配器的角色
 * @author ：hc
 * @date ：Created in 2021/9/5 15:42
 * @modified By：
 */
public class PrintBanner extends Banner implements Print {

    public PrintBanner(String string) {
        super(string);
    }

    @Override
    public void printWeak() {
        showWithParen();
    }

    @Override
    public void printStrong() {
        showWithAster();
    }
}
