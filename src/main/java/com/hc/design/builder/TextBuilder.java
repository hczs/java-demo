package com.hc.design.builder;

/**
 * @author ：hc
 * @date ：Created in 2021/9/8 20:50
 * @modified By：
 */
public class TextBuilder extends Builder{

    private StringBuffer buffer = new StringBuffer();

    @Override
    public void makeTitle(String title) {
        buffer.append("===============================\n");
        buffer.append("[").append(title).append("]\n");
    }

    @Override
    public void makeString(String str) {
        buffer.append("💢").append(str).append("\n");
    }

    @Override
    public void makeItems(String[] items) {
        for (String item : items) {
            buffer.append(" ·").append(item).append("\n");
        }
    }

    @Override
    public void close() {
        buffer.append("===============================\n");
    }

    public String getResult() {
        return buffer.toString();
    }
}
