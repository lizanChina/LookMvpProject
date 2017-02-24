package com.zlc.lookmvp.view.news;


import com.zlc.lookmvp.bean.news.NewsInfo;

import java.util.List;

/**
 * Created by zlc on 2017/2/8.
 */

public interface INewsFragment {

    void getNewsInfoList(List<NewsInfo.NewsBean> newsInfos);

}
