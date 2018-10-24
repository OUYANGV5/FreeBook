
package com.ouyang.freebook.modle.bean;


public class BaseResponseData{

    private int status;
    private String info;
    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }


}