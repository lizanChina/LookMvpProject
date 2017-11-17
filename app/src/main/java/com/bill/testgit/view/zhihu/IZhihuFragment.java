package com.bill.testgit.view.zhihu;


import com.bill.testgit.bean.zhihu.ZhihuInfo;

import java.util.List;

/**
 * Created by Bill on 2017/2/8.
 */

public interface IZhihuFragment {

    void getZhihuInfoList(String date, List<ZhihuInfo.ZhihuBean> zhihuInfos);

}
