/**
 * Copyright 2018 bejson.com
 */
package com.ouyang.freebook.modle.bean;
import java.util.Date;
import java.util.List;

/*
 *   书本详情
 */
public class BookDetails {

    private long Id;
    private String Name;
    private String Img;
    private String Author;
    private String Desc;
    private int CId;
    private String CName;//类别
    private String LastTime;
    private long FirstChapterId;
    private String LastChapter;
    private long LastChapterId;
    private String BookStatus;
    private List<SameUserBooks> SameUserBooks;
    private List<SameCategoryBooks> SameCategoryBooks;
    private BookVote BookVote;
    public void setId(long Id) {
        this.Id = Id;
    }
    public long getId() {
        return Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getName() {
        return Name;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }
    public String getImg() {
        return Img;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }
    public String getAuthor() {
        return Author;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }
    public String getDesc() {
        return Desc;
    }

    public void setCId(int CId) {
        this.CId = CId;
    }
    public int getCId() {
        return CId;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }
    public String getCName() {
        return CName;
    }

    public void setLastTime(String LastTime) {
        this.LastTime = LastTime;
    }
    public String getLastTime() {
        return LastTime;
    }

    public void setFirstChapterId(long FirstChapterId) {
        this.FirstChapterId = FirstChapterId;
    }
    public long getFirstChapterId() {
        return FirstChapterId;
    }

    public void setLastChapter(String LastChapter) {
        this.LastChapter = LastChapter;
    }
    public String getLastChapter() {
        return LastChapter;
    }

    public void setLastChapterId(long LastChapterId) {
        this.LastChapterId = LastChapterId;
    }
    public long getLastChapterId() {
        return LastChapterId;
    }

    public void setBookStatus(String BookStatus) {
        this.BookStatus = BookStatus;
    }
    public String getBookStatus() {
        return BookStatus;
    }

    public void setSameUserBooks(List<SameUserBooks> SameUserBooks) {
        this.SameUserBooks = SameUserBooks;
    }
    public List<SameUserBooks> getSameUserBooks() {
        return SameUserBooks;
    }

    public void setSameCategoryBooks(List<SameCategoryBooks> SameCategoryBooks) {
        this.SameCategoryBooks = SameCategoryBooks;
    }
    public List<SameCategoryBooks> getSameCategoryBooks() {
        return SameCategoryBooks;
    }

    public void setBookVote(BookVote BookVote) {
        this.BookVote = BookVote;
    }
    public BookVote getBookVote() {
        return BookVote;
    }

}