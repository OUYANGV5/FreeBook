/**
 * Copyright 2018 bejson.com
 */
package com.ouyang.freebook.modle.bean;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/*
 *   书本详情
 */
public class BookDetails implements Parcelable {

    private String Id;
    private String Name;
    private String Img;
    private String Author;
    private String Desc;
    private String CId;
    private String CName;//类别
    private String LastTime;
    private String FirstChapterId;
    private String LastChapter;
    private String LastChapterId;
    private String BookStatus;
    private List<SameUserBooks> SameUserBooks;
    private List<SameCategoryBooks> SameCategoryBooks;
    private BookVote BookVote;

    public BookDetails() {
    }

    protected BookDetails(Parcel in) {
        Id = in.readString();
        Name = in.readString();
        Img = in.readString();
        Author = in.readString();
        Desc = in.readString();
        CId = in.readString();
        CName = in.readString();
        LastTime = in.readString();
        FirstChapterId = in.readString();
        LastChapter = in.readString();
        LastChapterId = in.readString();
        BookStatus = in.readString();
    }

    public static final Creator<BookDetails> CREATOR = new Creator<BookDetails>() {
        @Override
        public BookDetails createFromParcel(Parcel in) {
            return new BookDetails(in);
        }

        @Override
        public BookDetails[] newArray(int size) {
            return new BookDetails[size];
        }
    };

    public void setId(String Id) {
        this.Id = Id;
    }
    public String getId() {
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

    public void setCId(String CId) {
        this.CId = CId;
    }
    public String getCId() {
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

    public void setFirstChapterId(String FirstChapterId) {
        this.FirstChapterId = FirstChapterId;
    }
    public String getFirstChapterId() {
        return FirstChapterId;
    }

    public void setLastChapter(String LastChapter) {
        this.LastChapter = LastChapter;
    }
    public String getLastChapter() {
        return LastChapter;
    }

    public void setLastChapterId(String LastChapterId) {
        this.LastChapterId = LastChapterId;
    }
    public String getLastChapterId() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Name);
        dest.writeString(Img);
        dest.writeString(Author);
        dest.writeString(Desc);
        dest.writeString(CId);
        dest.writeString(CName);
        dest.writeString(LastTime);
        dest.writeString(FirstChapterId);
        dest.writeString(LastChapter);
        dest.writeString(LastChapterId);
        dest.writeString(BookStatus);

    }
}