package com.hc.design.builder;

/**
 * 定义决定文档结构的方法
 * @author ：hc
 * @date ：Created in 2021/9/8 20:35
 * @modified By：
 */
public abstract class Builder {

    /**
     * 创建标题
     * @param title 标题
     */
    public abstract void makeTitle(String title);

    /**
     * 创建正文
     * @param str 正文内容
     */
    public abstract void makeString(String str);

    /**
     * 创建条目
     * @param items 条目数组
     */
    public abstract void makeItems(String[] items);

    /**
     * 关闭
     */
    public abstract void close();
}
