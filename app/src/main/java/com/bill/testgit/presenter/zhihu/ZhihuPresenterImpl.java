package com.bill.testgit.presenter.zhihu;

import android.util.Log;

import com.bill.testgit.bean.zhihu.ZhihuInfo;
import com.bill.testgit.http.HttpUtils;
import com.bill.testgit.http.helper.RetrofitHelper;
import com.bill.testgit.http.service.IZhihuService;
import com.bill.testgit.model.zhihu.IZhihuModel;
import com.bill.testgit.model.zhihu.ZhihuModelImpl;
import com.bill.testgit.view.zhihu.IZhihuFragment;

import rx.Observable;

/**
 * Created by Bill on 2017/2/8.
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
