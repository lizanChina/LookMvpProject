package com.zlc.lookmvp.presenter.news;

import android.util.Log;

import com.zlc.lookmvp.bean.news.NewsInfo;
import com.zlc.lookmvp.http.HttpUtils;
import com.zlc.lookmvp.http.helper.RetrofitHelper;
import com.zlc.lookmvp.http.service.INewsService;
import com.zlc.lookmvp.model.news.INewsModel;
import com.zlc.lookmvp.model.news.NewsModelImpl;
import com.zlc.lookmvp.view.news.INewsFragment;

import rx.Observable;

/**
 * Created by waylent on 2017/2/8.
 */

public class NewsPresenterImpl implements INewsPresenter {

    private INewsModel newsModel;
    private INewsFragment iNewsFragment;

    public NewsPresenterImpl(INewsFragment iNewsFragment) {

        newsModel = new NewsModelImpl();
        this.iNewsFragment = iNewsFragment;
    }


    @Override
    public void getNewsInfo(String url, int id) {
        Observable<NewsInfo> observable = RetrofitHelper.getService(url, INewsService.class).getNews(id);
        HttpUtils.requestNetByGet(observable, new HttpUtils.OnResultListener<NewsInfo>() {

            @Override
            public void onSuccess(NewsInfo newsInfo) {
                if (newsInfo != null) {
                    iNewsFragment.getNewsInfoList(newsInfo.newsList);
                }
            }

            @Override
            public void onError(Throwable error, String msg) {

                Log.e("error msg", msg);
            }
        });
    }
}
