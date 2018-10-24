package com.ouyang.freebook.modle.converter;

import android.graphics.Bitmap;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class MyConverterFactory extends Converter.Factory {
    private MyConverterFactory() {
        super();
    }

    public static MyConverterFactory create() {
        return new MyConverterFactory();
    }
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if(type.equals(Bitmap.class)){
            return new ImageResponseConverter();
        }
        return super.responseBodyConverter(type, annotations, retrofit);
    }
}
