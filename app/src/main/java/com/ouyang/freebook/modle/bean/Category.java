package com.ouyang.freebook.modle.bean;

public class Category {
    private String Id;
    private String Name;
    private long Count;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getCount() {
        return Count;
    }

    public void setCount(long count) {
        Count = count;
    }
}
