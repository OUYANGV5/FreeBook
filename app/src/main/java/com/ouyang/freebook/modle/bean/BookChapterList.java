package com.ouyang.freebook.modle.bean;

import java.util.List;

/*
    BookChapter的上一层包装,普通卷或vip卷
 */
public class BookChapterList {
    private String name;
    private List<BookChapter> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookChapter> getList() {
        return list;
    }

    public void setList(List<BookChapter> list) {
        this.list = list;
    }
}
