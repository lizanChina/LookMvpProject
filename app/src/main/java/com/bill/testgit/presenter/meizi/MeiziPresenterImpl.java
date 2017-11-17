package com.bill.testgit.presenter.meizi;

import com.bill.testgit.bean.meizi.MeiziInfo;
import com.bill.testgit.http.HttpUtils;
import com.bill.testgit.http.Urls;
import com.bill.testgit.http.helper.CacheHelper;
import com.bill.testgit.http.helper.RetrofitHelper;
import com.bill.testgit.http.service.IMeiziService;
import com.bill.testgit.model.meizi.IMeiziModel;
import com.bill.testgit.model.meizi.MeiziModelImpl;
import com.bill.testgit.util.Logger;
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
    public void getMeiziInfo(String type, int count, int page) {

        Logger.d("MeiziPresenterImpl Urls.baseUrl is: " + Urls.GANHUO_API);

        Logger.d("MeiziPresenterImpl type is: " + type);
        Logger.d("MeiziPresenterImpl count is: " + count);
        Logger.d("MeiziPresenterImpl page is: " + page);

        Observable<MeiziInfo> observable = RetrofitHelper.getService(Urls.GANHUO_API, IMeiziService.class).getMeizhiData(type, count, page);
        HttpUtils.requestNetByGet(observable, new HttpUtils.OnResultListener<MeiziInfo>() {
            @Override
            public void onSuccess(MeiziInfo meiziInfo) {

                CacheHelper.getInstance().printCacheSize();

                if (meiziInfo != null) {
                    Logger.d("meiziInfo.results.size() is: " + meiziInfo.results.size());
                    for (int i = 0; i < meiziInfo.results.size(); i++) {
                        MeiziInfo.printMeiziBean(meiziInfo.results.get(i));
                    }
                    iMeiziFragment.getMeiziDataList(meiziInfo.results);
                }
            }

            @Override
            public void onError(Throwable error, String msg) {
                Logger.e("onError error", msg);
            }
        });


    }
}
