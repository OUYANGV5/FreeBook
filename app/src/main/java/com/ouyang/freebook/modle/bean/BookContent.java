
package com.ouyang.freebook.modle.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class BookContent {

    private String id;//小说id
    private String name;//小说名
    private String cid;//章节id
    private String cname;//章节
    private String pid;//上一章节？,如果没有，则是-1
    private String nid;//下一章节？
    private String content;
    private int hasContent;
    private int line;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHasContent() {
        return hasContent;
    }

    public void setHasContent(int hasContent) {
        this.hasContent = hasContent;
    }
}