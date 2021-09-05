package com.hc.design.adapter.objectadapter;

/**
 * 扮演适配器的角色
 * @author ：hc
 * @date ：Created in 2021/9/5 15:42
 * @modified By：
 */
public class PrintBanner extends Print {

    private final Banner banner;

    public PrintBanner(String string) {
        this.banner = new Banner(string);
    }

    @Override
    public void printWeak() {
        banner.showWithParen();
    }

    @Override
    public void printStrong() {
        banner.showWithAster();
    }
}
