package com.bill.testgit.presenter.meizi;

import android.util.Log;

import com.bill.testgit.bean.meizi.MeiziInfo;
import com.bill.testgit.http.HttpUtils;
import com.bill.testgit.http.helper.RetrofitHelper;
import com.bill.testgit.http.service.IMeiziService;
import com.bill.testgit.model.meizi.IMeiziModel;
import com.bill.testgit.model.meizi.MeiziModelImpl;
import com.bill.testgit.util.LogUtil;
import com.bill.testgit.view.meizi.IMeiziFragment;

import rx.Observable;

/**
 * Created by Bill on 2017/2/8.
 */

public class MeiziPresenterImpl implements IMeiziPrestener {

    private IMeiziModel meiziModel;
    private IMeiziFragment iMeiziFragment;

    public MeiziPresenterImpl(IMeiziFragment iMeiziFragment) {
        meiziModel = new MeiziModelImpl();
        this.iMeiziFragment = iMeiziFragment;
    }


    @Override
    public void getMeiziInfo(String url, int page) {

        Log.d(LogUtil.LogTag, "MeiziPresenterImpl url is: " + url);
        Log.d(LogUtil.LogTag, "MeiziPresenterImpl page is: " + page);

        Observable<MeiziInfo> observable = RetrofitHelper.getService(url, IMeiziService.class).getMeizhiData(page);
        HttpUtils.requestNetByGet(observable, new HttpUtils.OnResultListener<MeiziInfo>() {
            @Override
            public void onSuccess(MeiziInfo meiziInfo) {
                if (meiziInfo != null) {
                    Log.d(LogUtil.LogTag, "meiziInfo.results.size() is: " + meiziInfo.results.size());
                    iMeiziFragment.getMeiziDataList(meiziInfo.results);
                }
            }

            @Override
            public void onError(Throwable error, String msg) {
                Log.e("妹子info error", msg);
            }
        });


    }
}
