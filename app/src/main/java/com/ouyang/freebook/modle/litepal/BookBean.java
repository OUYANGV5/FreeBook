package com.ouyang.freebook.modle.litepal;

import com.alibaba.fastjson.annotation.JSONField;
import com.ouyang.freebook.modle.bean.BookVote;

import org.litepal.crud.LitePalSupport;
/*
    用于存储到数据库中的book对象
 */
public class BookBean extends LitePalSupport {
    private long id;
    private long bookId;//小说id
    private String Name;//小说名字
    private String Author;//作者
    private String Img;//图片
    private String Desc;//简介
    private String CName;
    private double Score;//评分

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }
}
