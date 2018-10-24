package com.ouyang.freebook.modle.request;


import com.ouyang.freebook.modle.bean.BookList;
import com.ouyang.freebook.modle.bean.Category;
import com.ouyang.freebook.modle.bean.ResponseData;
import com.ouyang.freebook.modle.bean.ResponseDataList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryRequest {
    /*
        获取分类信息
     */
    @GET("BookCategory.html")
    Observable<ResponseDataList<Category>> getCategoryList();

    /*
        分类请求小说
     */
    @GET("Categories/{category}/{sort}/{index}.html")
    Observable<ResponseData<BookList>> getBookBySortAndCategory(@Path("category") String category,@Path("sort") String sort,@Path("index") int index);
}
