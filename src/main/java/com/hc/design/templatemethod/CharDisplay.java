package com.hc.design.templatemethod;

/**
 * 具体类，这里实现的方法将会被AbstractDisplay中的模板方法display调用
 * @author ：hc
 * @date ：Created in 2021/9/6 20:08
 * @modified By：
 */
public class CharDisplay extends AbstractDisplay{

    private final char ch;

    public CharDisplay(char ch) {
        this.ch = ch;
    }

    @Override
    public void open() {
        System.out.print("<<");
    }

    @Override
    public void print() {
        System.out.print(ch);
    }

    @Override
    public void close() {
        System.out.println(">>");
    }
}
