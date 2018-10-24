package com.ouyang.freebook.modle.bean;


import java.util.List;

public class BookChapterData {
    private String id;
    private String name;
    private List<BookChapterList> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
