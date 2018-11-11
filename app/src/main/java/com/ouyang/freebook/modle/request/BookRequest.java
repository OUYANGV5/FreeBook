package com.ouyang.freebook.modle.request;

import com.ouyang.freebook.modle.bean.BookChapterData;
import com.ouyang.freebook.modle.bean.BookContent;
import com.ouyang.freebook.modle.bean.BookDetails;
import com.ouyang.freebook.modle.bean.ResponseData;
import com.ouyang.freebook.modle.bean.comment.CommentResponseData;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface BookRequest {
    /*
        小说简介，包含最新章节
     */
    @GET("info/{bookId}.html")
    Observable<ResponseData<BookDetails>> getBookDetails(@Path("bookId") String bookId);


    /*
        获取评论
     */
    @GET("http://changyan.sohu.com/api/2/topic/load?client_id=cytklx2S1&topic_url=%20")
    Observable<CommentResponseData> getComment(@Query("topic_source_id") String bookId, @Query("page_size") String commentSize, @Query("hot_size") String hotCommentSize);

    /*
        获取小说章节正文
     */
    @GET("book/{bookId}/{chapterId}.html")
    Observable<ResponseData<BookContent>> getBookContent(@Path("bookId") String bookId, @Path("chapterId") String chapterId);

    /*
        获取所有章节，流量较大
     */
    @GET("book/{bookId}/")
    Observable<ResponseData<BookChapterData>> getBookChapter(@Path("bookId") String bookId);


}
