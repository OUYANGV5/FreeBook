/**
 * Copyright 2018 bejson.com
 */
package com.ouyang.freebook.modle.bean;

import java.util.List;

/**
 * Auto-generated: 2018-10-22 11:46:41
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ResponseDataList<T> extends BaseResponseData {
    private List<T> data;

    public void setData(List<T> data) {
        this.data = data;
    }
    public List<T> getData() {
        return data;
    }

}