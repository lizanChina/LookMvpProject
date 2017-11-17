package com.zlc.lookmvp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zlc.lookmvp.R;
import com.zlc.lookmvp.adapter.common.CommonRecyclerViewAdapter;
import com.zlc.lookmvp.adapter.common.CommonRecyclerViewHolder;
import com.zlc.lookmvp.bean.news.NewsInfo;
import com.zlc.lookmvp.util.DensityUtil;
import com.zlc.lookmvp.util.ImageUtil;

import java.util.List;

/**
 * Created by zlc on 2017/2/8.
 * 新闻数据适配器
 */

public class NewsAdapter extends CommonRecyclerViewAdapter<NewsInfo.NewsBean> {


    public NewsAdapter(Context context, List<NewsInfo.NewsBean> datas) {
        super(context, datas);
    }

    @Override
    public View initView() {
        mView = View.inflate(mContext, R.layout.recycle_news_item, null);
        return mView;
    }

    @Override
    public void setData(CommonRecyclerViewHolder holder, int position) {
        NewsInfo.NewsBean newsBean = mDatas.get(position);
        if (holder != null) {
            holder.setText(R.id.id_tv_news_title, newsBean.title);
            holder.setText(R.id.id_tv_news_desc, newsBean.source);

            ImageView id_iv_news = holder.getView(R.id.id_iv_news);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) id_iv_news.getLayoutParams();
            int w = params.width = DensityUtil.dip2px(mContext, 93);
            params.height = (w * 3) / 4;
            id_iv_news.setLayoutParams(params);
            ImageUtil.show(id_iv_news, newsBean.imgsrc);

        }
    }
}
