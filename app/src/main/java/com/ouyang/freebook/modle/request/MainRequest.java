package com.ouyang.freebook.modle.request;


import com.ouyang.freebook.modle.bean.Banner;
import com.ouyang.freebook.modle.bean.CategoryBooks;
import com.ouyang.freebook.modle.bean.ResponseDataList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
    书城请求
    包括轮播图，大部分分类图书
 */
public interface MainRequest {

    /*
        获取轮播图
     */
    @GET("v5/base/banner_{sex}.html")
    Observable<ResponseDataList<Banner>> getBanner(@Path("sex") String sex);

    /*
        获取热门分类书籍
     */
    @GET("v5/base/{sex}.html")
    Observable<ResponseDataList<CategoryBooks>> getBase(@Path("sex") String sex);
}
