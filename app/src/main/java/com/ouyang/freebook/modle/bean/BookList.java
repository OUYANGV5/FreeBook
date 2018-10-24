package com.ouyang.freebook.modle.bean;

import java.util.List;

public class BookList {
    private List<Book> BookList;
    private int Page;
    private boolean HasNext;

    public List<Book> getBookList() {
        return BookList;
    }

    public void setBookList(List<Book> bookList) {
        BookList = bookList;
    }

    public int getPage() {
        return Page;
    }

    public void setPage(int page) {
        Page = page;
    }

    public boolean isHasNext() {
        return HasNext;
    }

    public void setHasNext(boolean hasNext) {
        HasNext = hasNext;
    }
}
