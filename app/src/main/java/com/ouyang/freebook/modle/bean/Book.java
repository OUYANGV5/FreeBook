package com.ouyang.freebook.modle.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private long Id;//小说id
    private String Name;//小说名字
    private String Author;//作者
    private String Img;//图片
    private String Desc;//简介
    private String CName;
    private double Score;//评分

    public Book(){

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(Id);
        dest.writeString(Name);
        dest.writeString(Author);
        dest.writeString(Img);
        dest.writeString(Desc);
        dest.writeString(CName);
        dest.writeDouble(Score);
    }
    public Book(Parcel source) {
        Id=source.readLong();
        Name=source.readString();
        Author=source.readString();
        Img=source.readString();
        Desc=source.readString();
        CName=source.readString();
        Score=source.readDouble();
    }
    public static final Creator<Book> CREATOR = new Creator<Book>() {

        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


}
