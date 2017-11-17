package com.bill.testgit.view.news;


import com.bill.testgit.bean.news.NewsInfo;

import java.util.List;

/**
 * Created by Bill on 2017/2/8.
 */

public interface INewsFragment {

    void getNewsInfoList(List<NewsInfo.NewsBean> newsInfos);

}
