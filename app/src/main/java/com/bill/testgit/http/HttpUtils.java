package com.bill.testgit.http;


import android.text.TextUtils;
import android.widget.Toast;

import com.bill.testgit.application.MyApplication;
import com.bill.testgit.util.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Bill on 2016/10/19.
 * 网络请求工具类
 */

public class HttpUtils {


    //Post方式请求网络
    //Bill: public <T>这个T是个修饰符的功能，表示是个泛型方法，就像有static修饰的方法是个静态方法一样。表示传入参数有泛型
    public static <T> void requestNet(Observable observable, final OnResultListener resultListener) {

        Logger.d("requestNet");

        setSubscriber(observable, new Subscriber<T>() {
            @Override
            public void onCompleted() {
                Logger.d("HttpUtils requestNetByPost onCompleted");
            }

            @Override
            public void onError(Throwable error) {
                Logger.d("HttpUtils requestNetByPost onError");
                if (error != null && resultListener != null) {
                    resultListener.onError(error, error.getMessage());
                } else if (resultListener != null) {
                    Logger.d("网络不给力");
                    resultListener.onError(new Exception("网络不给力"), "");
                    Toast.makeText(MyApplication.getContext(), "网络不给力", Toast.LENGTH_LONG).show();
                    return;
                }

                String e = error.getMessage();
                int code = 0;
                if (!TextUtils.isEmpty(e)) {
                    try {
                        e = e.substring(e.length() - 3, e.length());
                        code = Integer.valueOf(e);
                    } catch (Exception e1) {
                        Toast.makeText(MyApplication.getContext(), "网络不给力", Toast.LENGTH_LONG).show();
                    }
                }
                Logger.e("onError code==：", code + "");
                if (code >= 300 && code < 500) {
                    Toast.makeText(MyApplication.getContext(), "您的请求迷路了，请稍后再试", Toast.LENGTH_LONG).show();
                } else if (code >= 500) {
                    Toast.makeText(MyApplication.getContext(), "服务器异常，请稍后再试", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MyApplication.getContext(), "网络不给力", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNext(T t) {
                Logger.d("HttpUtils requestNetByPost onNext");
                if (resultListener != null) {
                    resultListener.onSuccess(t);
                }
            }
        });
    }

    //Get方式请求网络
    public static void requestNetByPost(Observable observable, final OnResultListener resultListener) {

        Logger.d("requestNetByPost");
        requestNet(observable, resultListener);
    }

    //Get方式请求网络
    public static void requestNetByGet(Observable observable, final OnResultListener resultListener) {

        Logger.d("requestNetByGet");
        requestNet(observable, resultListener);
    }


    //订阅事件
    public static <T> void setSubscriber(Observable<T> observable, Subscriber<T> subscriber) {

        Logger.d("setSubscriber");

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    //接口回调
    public interface OnResultListener<T> {

        void onSuccess(T t);

        void onError(Throwable error, String msg);
    }

}
