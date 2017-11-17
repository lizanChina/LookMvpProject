package com.bill.testgit.presenter.news;

import android.util.Log;

import com.bill.testgit.bean.news.NewsInfo;
import com.bill.testgit.http.HttpUtils;
import com.bill.testgit.http.helper.RetrofitHelper;
import com.bill.testgit.http.service.INewsService;
import com.bill.testgit.model.news.INewsModel;
import com.bill.testgit.model.news.NewsModelImpl;
import com.bill.testgit.view.news.INewsFragment;

import rx.Observable;

/**
 * Created by Bill on 2017/2/8.
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
