package com.ouyang.freebook.modle.bean;


import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class BookChapterData extends LitePalSupport {
    @Column(unique = true)
    private int id;//小说id
    private String name;//小说名
    private List<BookChapterList> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookChapterList> getList() {
        return list;
    }

    public void setList(List<BookChapterList> list) {
        this.list = list;
    }
}
