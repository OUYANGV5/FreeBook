
package com.ouyang.freebook.modle.bean;


public class SameUserBooks {

    private long Id;
    private String Name;
    private String Author;
    private String Img;
    private long LastChapterId;
    private String LastChapter;
    private int Score;
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

    public void setAuthor(String Author) {
        this.Author = Author;
    }
    public String getAuthor() {
        return Author;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }
    public String getImg() {
        return Img;
    }

    public void setLastChapterId(long LastChapterId) {
        this.LastChapterId = LastChapterId;
    }
    public long getLastChapterId() {
        return LastChapterId;
    }

    public void setLastChapter(String LastChapter) {
        this.LastChapter = LastChapter;
    }
    public String getLastChapter() {
        return LastChapter;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }
    public int getScore() {
        return Score;
    }

}