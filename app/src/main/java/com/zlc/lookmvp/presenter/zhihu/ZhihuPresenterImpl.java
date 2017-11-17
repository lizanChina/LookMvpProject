package com.zlc.lookmvp.presenter.zhihu;

import android.util.Log;

import com.zlc.lookmvp.bean.zhihu.ZhihuInfo;
import com.zlc.lookmvp.http.HttpUtils;
import com.zlc.lookmvp.http.helper.RetrofitHelper;
import com.zlc.lookmvp.http.service.IZhihuService;
import com.zlc.lookmvp.model.zhihu.IZhihuModel;
import com.zlc.lookmvp.model.zhihu.ZhihuModelImpl;
import com.zlc.lookmvp.view.zhihu.IZhihuFragment;

import rx.Observable;

/**
 * Created by waylent on 2017/2/8.
 */

public class ZhihuPresenterImpl implements IZhihuPresenter {

    private IZhihuModel zhihuModel;
    private IZhihuFragment iZhihuFragment;

    public ZhihuPresenterImpl(IZhihuFragment iZhihuFragment) {
        zhihuModel = new ZhihuModelImpl();
        this.iZhihuFragment = iZhihuFragment;
    }


    @Override
    public void getZhihuInfo(String url, String date) {

        Observable<ZhihuInfo> observable = RetrofitHelper.getService(url, IZhihuService.class).getTheDaily(date);
        HttpUtils.requestNetByGet(observable, new HttpUtils.OnResultListener<ZhihuInfo>() {
            @Override
            public void onSuccess(ZhihuInfo zhihuInfo) {
                if (zhihuInfo != null)
                    iZhihuFragment.getZhihuInfoList(zhihuInfo.date, zhihuInfo.stories);
            }

            @Override
            public void onError(Throwable error, String msg) {
                Log.e("error msg", msg);
            }
        });
    }

    @Override
    public void getLastZhihuNews(String url) {

        Observable<ZhihuInfo> observable = RetrofitHelper.getService(url, IZhihuService.class).getLastDaily();
        HttpUtils.requestNetByGet(observable, new HttpUtils.OnResultListener<ZhihuInfo>() {
            @Override
            public void onSuccess(ZhihuInfo zhihuInfo) {
                if (zhihuInfo != null)
                    iZhihuFragment.getZhihuInfoList(zhihuInfo.date, zhihuInfo.stories);
            }

            @Override
            public void onError(Throwable error, String msg) {
                Log.e("error msg", msg);
            }
        });
    }
}
