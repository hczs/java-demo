package com.hc.design.iterator;

import java.util.ArrayList;

/**
 * @author: houcheng
 * @date: 2021/9/3 11:31
 * @version: V1.0
 * @description: 测试类
 * @modify:
 */
public class Main {

    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(5);
        bookShelf.add(new Book("Java编程思想（第四版）"));
        bookShelf.add(new Book("Java并发编程的艺术"));
        bookShelf.add(new Book("Java 8实战"));
        bookShelf.add(new Book("Java核心技术(卷1）第8版"));
        bookShelf.add(new Book("图解Java多线程设计模式"));
        Iterator iterator = bookShelf.iterator();
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println(book.getName());
        }
    }
}
