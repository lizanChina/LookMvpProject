package com.bill.testgit.http.service;


import com.bill.testgit.bean.news.NewsInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Bill on 2017/2/9.
 */

public interface INewsService {


    @GET("http://c.m.163.com/nc/article/headline/T1348647909107/{id}-20.html")
    Observable<NewsInfo> getNews(@Path("id") int id);

}
