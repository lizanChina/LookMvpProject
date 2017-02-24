package com.zlc.lookmvp.presenter.meizi;

import android.util.Log;

import com.zlc.lookmvp.bean.meizi.MeiziInfo;
import com.zlc.lookmvp.http.HttpUtils;
import com.zlc.lookmvp.http.helper.RetrofitHelper;
import com.zlc.lookmvp.http.service.IMeiziService;
import com.zlc.lookmvp.model.meizi.IMeiziModel;
import com.zlc.lookmvp.model.meizi.MeiziModelImpl;
import com.zlc.lookmvp.view.meizi.IMeiziFragment;

import rx.Observable;

/**
 * Created by zlc on 2017/2/8.
 */

public class MeiziPresenterImpl implements IMeiziPrestener{

    private IMeiziModel meiziModel;
    private IMeiziFragment iMeiziFragment;
    public MeiziPresenterImpl(IMeiziFragment iMeiziFragment){
        meiziModel = new MeiziModelImpl();
        this.iMeiziFragment = iMeiziFragment;
    }


    @Override
    public void getMeiziInfo(String url,int page) {

        Observable<MeiziInfo> observable = RetrofitHelper.getService(url, IMeiziService.class).getMeizhiData(page);
        HttpUtils.requestNetByGet(observable, new HttpUtils.OnResultListener<MeiziInfo>() {
            @Override
            public void onSuccess(MeiziInfo meiziInfo) {
                if(meiziInfo!=null)
                    iMeiziFragment.getMeiziDataList(meiziInfo.results);
            }

            @Override
            public void onError(Throwable error, String msg) {
                Log.e("妹子info error",msg);
            }
        });


    }
}
