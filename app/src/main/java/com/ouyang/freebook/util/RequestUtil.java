package com.ouyang.freebook.util;

import com.ouyang.freebook.modle.RequestConfig;
import com.ouyang.freebook.modle.converter.MyConverterFactory;
import com.ouyang.freebook.modle.interceptor.AddCookieInterceptor;
import com.ouyang.freebook.modle.interceptor.SetCookieInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RequestUtil {
    private static Retrofit retrofit;
    static {
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new SetCookieInterceptor())
                .addInterceptor(new AddCookieInterceptor())
                .connectTimeout(30,TimeUnit.SECONDS)
                /*.sslSocketFactory(SSLSocketClient.getSSLSocketFactory(),SSLSocketClient.getEmptyTrustManager())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())*/
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(RequestConfig.URL_API_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MyConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(okHttpClient).build();
    }

    public static <T> T get(Class<T> c){
        return retrofit.create(c);
    }
}
