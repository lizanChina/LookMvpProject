package com.bill.testgit.http.helper;


import com.bill.testgit.application.MyApplication;
import com.bill.testgit.util.Logger;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;

/**
 * Created by Bill on 2016/10/19.
 * 缓存帮助类
 */

public class CacheHelper {

    private Cache mCache;
    //设置缓存目录
    private static File cacheFile;
    private static long maxSize = 8 * 1024 * 1024;

    private CacheHelper() {


        cacheFile = new File(MyApplication.getContext().getCacheDir().getAbsolutePath(), "mycache");
        if (!cacheFile.exists()) {
            cacheFile.mkdir();
        }
        Logger.d("cache path is: " + cacheFile.getAbsolutePath());
    }


    private static CacheHelper helper;

    public static CacheHelper getInstance() {
        if (helper == null) {
            synchronized (CacheHelper.class) {
                if (helper == null) {
                    helper = new CacheHelper();
                }
            }
        }
        return helper;
    }


    //返回缓存对象
    public Cache getCache() {
        if (mCache == null)
            mCache = new Cache(cacheFile, maxSize);

        return mCache;
    }

    //返回缓存对象
    public void printCacheSize() {
        if (mCache == null) {
            Logger.e("mCache is null");
            return;
        }

        Logger.d("mCache maxSize is: " + mCache.maxSize());
        try {
            Logger.d("mCache maxSize is: " + mCache.size());
        } catch (IOException e) {
            Logger.e("get cache size failed");
            e.printStackTrace();
        }

    }
}
