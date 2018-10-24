package com.ouyang.freebook.modle.interceptor;

import android.util.Log;

import com.ouyang.freebook.modle.Cookie;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder=chain.request().newBuilder();
        //给当前请求添加cookie
        //builder.addHeader("Cookie",Cookie.getCookieString());
        Log.d("cookie","当前请求:"+chain.request().toString()+",附加cookie:"+Cookie.getCookieString());

        //添加请求头
        builder.addHeader("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Mobile Safari/537.36");
        return chain.proceed(builder.build());
    }
}
