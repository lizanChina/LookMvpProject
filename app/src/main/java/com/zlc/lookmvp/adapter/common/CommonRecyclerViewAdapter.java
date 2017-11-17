package com.zlc.lookmvp.adapter.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zlc on 2017/2/8.
 * RecycleView通用数据适配器
 */

public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<CommonRecyclerViewHolder> {


    protected Context mContext;
    protected List<T> mDatas;
    protected View mView;
    private CommonRecyclerViewHolder viewHolder;

    public CommonRecyclerViewAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }


    @Override
    public CommonRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = initView();
        viewHolder = new CommonRecyclerViewHolder(mContext, mView);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mDatas != null && mDatas.size() > 0 ? mDatas.size() : 0;
    }

    @Override
    public void onBindViewHolder(CommonRecyclerViewHolder holder, int position) {
        setData(holder, position);
    }


    public CommonRecyclerViewHolder getViewHolder() {
        return viewHolder;
    }

    public abstract View initView();

    public abstract void setData(CommonRecyclerViewHolder holder, int position);

}
