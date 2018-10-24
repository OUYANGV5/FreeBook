
package com.ouyang.freebook.modle.bean;

public class SameCategoryBooks {

    private long Id;
    private String Name;
    private String Img;
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

    public void setImg(String Img) {
        this.Img = Img;
    }
    public String getImg() {
        return Img;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }
    public int getScore() {
        return Score;
    }

}