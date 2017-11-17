package com.bill.testgit.http.service;


import com.bill.testgit.bean.zhihu.ZhihuInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Bill on 2017/2/9.
 */

public interface IZhihuService {

    @GET("http://news-at.zhihu.com/api/4/news/before/{date}")
    Observable<ZhihuInfo> getTheDaily(@Path("date") String date);

    @GET("api/4/news/latest")
    Observable<ZhihuInfo> getLastDaily();


}
