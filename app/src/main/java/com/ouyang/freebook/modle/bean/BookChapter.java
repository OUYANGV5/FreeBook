package com.ouyang.freebook.modle.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class BookChapter {
    private String id;
    private String name;
    private int hasContent;

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

    public int getHasContent() {
        return hasContent;
    }

    public void setHasContent(int hasContent) {
        this.hasContent = hasContent;
    }
}
