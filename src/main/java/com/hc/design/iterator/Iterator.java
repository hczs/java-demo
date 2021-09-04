package com.hc.design.iterator;

/**
 * @author: houcheng
 * @date: 2021/9/3 11:14
 * @version: V1.0
 * @description: 遍历集合的接口
 * @modify:
 */
public interface Iterator {

    /**
     * 集合存在下一个元素时，返回true，不存在下一个元素时，返回false
     * @return boolean
     */
    boolean hasNext();

    /**
     * 返回下一个元素
     * 【注意】：这里不只是单纯返回下一个元素，其实还隐含着一个操作，就是迭代器移向下一个元素
     * @return Object
     */
    Object next();
}
