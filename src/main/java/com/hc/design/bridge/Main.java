package com.hc.design.bridge;

/**
 * @author ：hc
 * @date ：Created in 2021/9/17 22:31
 * @modified By：
 */
public class Main {

    public static void main(String[] args) {
        Display d1 = new Display(new StringDisplayImpl("Hello, China!"));
        Display d2 = new CountDisplay(new StringDisplayImpl("Hello, World!"));
        CountDisplay d3 = new CountDisplay(new StringDisplayImpl("Hello, Universe!"));

        d1.display();
        d2.display();
        d3.display();
        d3.multiDisplay(5);
    }
}
