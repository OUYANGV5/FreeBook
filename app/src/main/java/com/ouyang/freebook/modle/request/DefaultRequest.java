package com.ouyang.freebook.modle.request;

import android.graphics.Bitmap;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface DefaultRequest {

    @FormUrlEncoded
    @POST
    Observable<String> postHtml(@Url String url, @FieldMap Map<String, String> map);

    @GET
    Observable<Bitmap> getImage(@Url String url);
}
