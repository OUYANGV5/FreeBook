package com.ouyang.freebook.modle.interceptor;

import android.util.Log;

import com.ouyang.freebook.modle.Cookie;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Response;

public class SetCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        Headers headers=chain.request().headers();
        for(String s:headers.names()){
            Log.e("head",headers.get(s));
        }
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            //解析Cookie
            for (String header : originalResponse.headers("Set-Cookie")) {
                String cookie=header.substring(0, header.indexOf(";")).trim();
                Log.d("cookie","设置Cookie:"+cookie);
                String[] keyAndValue=cookie.split("=");
                if(keyAndValue.length<2){
                    Cookie.putCookie(keyAndValue[0],"");
                }else {
                    Cookie.putCookie(keyAndValue[0],keyAndValue[1]);
                }
            }
        }
        return originalResponse;
    }
}
