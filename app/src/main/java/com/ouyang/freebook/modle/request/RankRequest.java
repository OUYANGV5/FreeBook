package com.ouyang.freebook.modle.request;

import com.ouyang.freebook.modle.bean.BookList;
import com.ouyang.freebook.modle.bean.ResponseData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RankRequest {

    @GET("top/{sex}/top/{sort}/{time}/{index}.html")
    Observable<ResponseData<BookList>> getRankList(@Path("sex") String sex,@Path("sort") String sort,@Path("time") String time,@Path("index") int index);
}
