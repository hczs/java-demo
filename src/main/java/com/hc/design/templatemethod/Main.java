package com.hc.design.templatemethod;

/**
 * 模板方法模式，父类定义处理流程框架，子类去具体实现
 * @author ：hc
 * @date ：Created in 2021/9/6 20:10
 * @modified By：
 */
public class Main {

    public static void main(String[] args) {
        AbstractDisplay display = new CharDisplay('a');
        display.display();
        AbstractDisplay display1 = new StringDisplay("hello,world!");
        display1.display();
    }
}
