package com.bill.testgit.bean.meizi;

import com.bill.testgit.util.Logger;

import java.util.List;

/**
 * Created by Bill on 2017/2/7.
 */

public class MeiziInfo {


    public List<MeiziBean> results;

    public static class MeiziBean {

        public String objectId;
        public String url;
        public String type;
        public String desc;
        public String who;
        public boolean used;
        public boolean hasFadedIn = false;
        public int imageWidth;
        public int imageHeight;
    }

    public static void printMeiziBean(MeiziBean obj) {

        Logger.d("-----------------------------------");

        Logger.d("objectId is: " + obj.objectId);
        Logger.d("url is: " + obj.url);
        Logger.d("type is: " + obj.type);
        Logger.d("desc is: " + obj.desc);
        Logger.d("who is: " + obj.who);
        Logger.d("used is: " + obj.used);
        Logger.d("hasFadedIn is: " + obj.hasFadedIn);
        Logger.d("imageWidth is: " + obj.imageWidth);
        Logger.d("imageHeight is: " + obj.imageHeight);

        Logger.d("-----------------------------------");
    }

}
