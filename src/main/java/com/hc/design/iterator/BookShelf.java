package com.hc.design.iterator;

/**
 * @author: houcheng
 * @date: 2021/9/3 11:22
 * @version: V1.0
 * @description: BookShelf书架，存放书的集合类
 * @modify:
 */
public class BookShelf implements Aggregate{

    private Book[] books;

    private int last = 0;

    /**
     * 初始化书架
     * @param maxSize 最大容量
     */
    public BookShelf(int maxSize) {
        this.books = new Book[maxSize];
    }

    /**
     * 根据下标获取书
     * @param index 下标
     * @return 书对象
     */
    public Book getBookAt(int index) {
        return books[index];
    }

    /**
     * 添加书
     * @param book 书对象
     */
    public void add(Book book) {
        this.books[last] = book;
        last++;
    }

    /**
     * 获取当前书架长度
     * @return
     */
    public int getLength() {
        return last;
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
