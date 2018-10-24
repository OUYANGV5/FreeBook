
package com.ouyang.freebook.modle.bean;

public class BookContent {

    private long id;
    private String name;
    private long cid;
    private String cname;
    private int pid;
    private long nid;
    private String content;
    private int hasContent;
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }
    public long getCid() {
        return cid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
    public String getCname() {
        return cname;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
    public int getPid() {
        return pid;
    }

    public void setNid(long nid) {
        this.nid = nid;
    }
    public long getNid() {
        return nid;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setHasContent(int hasContent) {
        this.hasContent = hasContent;
    }
    public int getHasContent() {
        return hasContent;
    }

}