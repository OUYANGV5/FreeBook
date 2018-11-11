package com.ouyang.freebook.modle.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
    private String Id;
    private String Name;
    private long Count;

    protected Category(Parcel in) {
        Id = in.readString();
        Name = in.readString();
        Count = in.readLong();
    }

    public Category() {
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Name);
        dest.writeLong(Count);
    }
}
