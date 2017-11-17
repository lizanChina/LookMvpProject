package com.zlc.lookmvp.bean.news;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zlc on 2017/2/7.
 */

public class NewsInfo {

    @SerializedName("T1348647909107")
    public List<NewsBean> newsList;


    public static class NewsBean implements Serializable {

        @SerializedName("docid")
        public String docid;
        @SerializedName("title")
        public String title;
        @SerializedName("digest")
        public String digest;
        @SerializedName("imgsrc")
        public String imgsrc;
        @SerializedName("source")
        public String source;
        @SerializedName("ptime")
        public String ptime;
        @SerializedName("tag")
        public String tag;
    }

}
