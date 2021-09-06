package com.hc.design.templatemethod;

/**
 * 具体类，这里实现的方法将会被AbstractDisplay中的模板方法display调用
 * @author ：hc
 * @date ：Created in 2021/9/6 20:12
 * @modified By：
 */
public class StringDisplay extends AbstractDisplay{

    private final String s;

    public StringDisplay(String s) {
        this.s = s;
    }

    @Override
    public void open() {
        printLine();
    }

    @Override
    public void print() {
        System.out.println("|" + s + "|");
    }

    @Override
    public void close() {
        printLine();
    }

    /**
     * 按照字符串长度打印合适的边框
     */
    private void printLine() {
        System.out.print("+");
        for (int i = 0; i < s.length(); i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}
