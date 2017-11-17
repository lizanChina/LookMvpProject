package com.bill.testgit.bean.meizi;

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

}
