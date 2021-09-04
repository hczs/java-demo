package com.hc.design.iterator;

/**
 * @author: houcheng
 * @date: 2021/9/3 11:26
 * @version: V1.0
 * @description: 遍历书架的迭代器
 * @modify:
 */
public class BookShelfIterator implements Iterator{

    private BookShelf bookShelf;

    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < bookShelf.getLength();
    }

    @Override
    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
}
