package com.ouyang.freebook.modle.converter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class ImageResponseConverter implements Converter<ResponseBody,Bitmap> {

    @Override
    public Bitmap convert(ResponseBody value) throws IOException {
        Bitmap bitmap=BitmapFactory.decodeStream(value.byteStream());
        return bitmap;
    }

}
