package com.ouyang.freebook.modle.pojo;

public class StringIndex {
    private int start;
    private int end;

    public StringIndex(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {

        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
