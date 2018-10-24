package com.ouyang.freebook.modle.request;

import com.ouyang.freebook.modle.bean.BookChapterData;
import com.ouyang.freebook.modle.bean.BookContent;
import com.ouyang.freebook.modle.bean.BookDetails;
import com.ouyang.freebook.modle.bean.ResponseData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BookRequest {
    /*
        小说简介，包含最新章节
     */
    @GET("info/{bookId}.html")
    Observable<ResponseData<BookDetails>> getBookDetails(@Path("bookId") String bookId);

    /*
        获取小说章节正文
     */
    @GET("book/{bookId}/{chapterId}.html")
    Observable<ResponseData<BookContent>> getBookContent(@Path("bookId") String bookId, @Path("chapterId") String chapterId);

    /*
        获取所有章节，慎用，流量大
     */
    @GET("book/{bookId}/")
    Observable<ResponseData<BookChapterData>> getBookChapter(@Path("bookId") String bookId);

}
