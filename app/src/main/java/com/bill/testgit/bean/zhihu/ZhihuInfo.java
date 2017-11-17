package com.bill.testgit.bean.zhihu;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Bill on 2017/2/8.
 */

public class ZhihuInfo {


    @SerializedName("date")
    public String date;
    @SerializedName("top_stories")
    public ArrayList<ZhihuBean> mZhihuDailyItems;
    @SerializedName("stories")
    public ArrayList<ZhihuBean> stories;

    public static class ZhihuBean {
        @SerializedName("images")
        public String[] images;
        @SerializedName("type")
        public int type;
        @SerializedName("id")
        public String id;
        @SerializedName("title")
        public String title;
        public String date;
        public boolean hasFadedIn = false;
    }

}
