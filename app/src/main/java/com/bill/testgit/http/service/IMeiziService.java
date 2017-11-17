package com.bill.testgit.http.service;


import com.bill.testgit.bean.meizi.MeiziInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Bill on 2017/2/9.
 */

public interface IMeiziService {


    @GET("http://gank.io/api/data/{type}/{count}/{page}")
    Observable<MeiziInfo> getMeizhiData(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page);

}
