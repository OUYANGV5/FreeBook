package com.ouyang.freebook.modle.request;

import android.text.GetChars;

import com.ouyang.freebook.modle.bean.HotInfo;
import com.ouyang.freebook.modle.bean.ResponseData;
import com.ouyang.freebook.modle.bean.ResponseDataList;
import com.ouyang.freebook.modle.bean.SearchBookInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SearchRequest {
    /*
        小说搜索
     */
    @GET("https://sou.jiaston.com/search.aspx?key={name}&page={page}&siteid=app2")
    Observable<ResponseDataList<SearchBookInfo>> searchBook(@Path("name") String name, @Path("page") int tpage);

    /*
        获取热门图书关键词
     */
    @GET("https://shuapi.jiaston.com/StaticFiles/HotBook1000.html")
    Observable<ResponseDataList<HotInfo>> getHotInfo();
}
