package com.zlc.lookmvp.view.zhihu;


import com.zlc.lookmvp.bean.zhihu.ZhihuInfo;

import java.util.List;

/**
 * Created by zlc on 2017/2/8.
 */

public interface IZhihuFragment {

    void getZhihuInfoList(String date, List<ZhihuInfo.ZhihuBean> zhihuInfos);

}
