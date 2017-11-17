package com.bill.testgit.http.service;


import com.bill.testgit.bean.meizi.MeiziInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Bill on 2017/2/9.
 */

public interface IMeiziService {


    @GET("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/{page}")
    Observable<MeiziInfo> getMeizhiData(@Path("page") int page);

}
