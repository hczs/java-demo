package com.hc.design.iterator;

/**
 * @author: houcheng
 * @date: 2021/9/3 11:13
 * @version: V1.0
 * @description: 表示集合的接口，Aggregate有使聚集，集合的意思
 * @modify:
 */
public interface Aggregate {
    /**
     * 获取遍历集合的接口
     * @return Iterator
     */
    Iterator iterator();
}
