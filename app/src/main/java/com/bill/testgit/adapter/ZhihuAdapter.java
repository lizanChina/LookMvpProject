package com.bill.testgit.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bill.testgit.R;
import com.bill.testgit.adapter.common.CommonRecyclerViewAdapter;
import com.bill.testgit.adapter.common.CommonRecyclerViewHolder;
import com.bill.testgit.bean.zhihu.ZhihuInfo;
import com.bill.testgit.util.DensityUtil;
import com.bill.testgit.util.ImageUtil;

import java.util.List;

/**
 * Created by Bill on 2017/2/8.
 * 知乎数据适配器
 */

public class ZhihuAdapter extends CommonRecyclerViewAdapter<ZhihuInfo.ZhihuBean> {


    public ZhihuAdapter(Context context, List<ZhihuInfo.ZhihuBean> datas) {
        super(context, datas);
    }

    @Override
    public View initView() {
        mView = View.inflate(mContext, R.layout.recycle_zhihu_item, null);
        return mView;
    }

    @Override
    public void setData(CommonRecyclerViewHolder holder, int position) {

        ZhihuInfo.ZhihuBean info = mDatas.get(position);
        if (holder != null) {
            holder.setText(R.id.id_tv_zhihu_title, info.title);

            ImageView id_iv_news = holder.getView(R.id.id_iv_zhihu);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) id_iv_news.getLayoutParams();
            int w = params.width = DensityUtil.dip2px(mContext, 93);
            params.height = (w * 3) / 4;
            id_iv_news.setLayoutParams(params);
            ImageUtil.show(id_iv_news, info.images[0]);
        }
    }

}
