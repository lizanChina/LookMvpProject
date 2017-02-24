package com.zlc.lookmvp.ui.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zlc.lookmvp.R;
import com.zlc.lookmvp.adapter.MeiziAdapter;
import com.zlc.lookmvp.bean.meizi.MeiziInfo;
import com.zlc.lookmvp.presenter.meizi.MeiziPresenterImpl;
import com.zlc.lookmvp.util.SwipeRefreshUtil;
import com.zlc.lookmvp.view.custom_view.GridItemDividerDecoration;
import com.zlc.lookmvp.view.meizi.IMeiziFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by zlc on 2017/2/8.
 * 妹子Fragment
 */

public class MeiziFragment extends BaseFragment implements IMeiziFragment,SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.id_meizi_recycle)
    RecyclerView mMeiziRecycle;
    @InjectView(R.id.id_meizi_swipe)
    SwipeRefreshLayout mMeiziSwipe;
    private MeiziPresenterImpl meiziPrestener;
    private MeiziAdapter mMeiziAdapter;
    //妹子地址
    private String url = "http://gank.io";
    private int page = 1;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView.OnScrollListener mloadmoreListener;
    private boolean loading;
    private List<MeiziInfo.MeiziBean> meiziInfos;

    @Override
    public View initView() {
        mView = View.inflate(mActivity, R.layout.fragment_meizi, null);
        return mView;
    }

    @Override
    public void initData() {

        SwipeRefreshUtil.setSiwpeLayout(mMeiziSwipe,mActivity,this);
        meiziInfos = new ArrayList<>();
        meiziPrestener = new MeiziPresenterImpl(this);
        meiziPrestener.getMeiziInfo("",page);
        setRecycleView();
    }

    private void setRecycleView() {

        mMeiziRecycle.addItemDecoration(new GridItemDividerDecoration(getContext(), R.dimen.divider_height, R.color.divider_color));
        mMeiziRecycle.setItemAnimator(new DefaultItemAnimator());
        mMeiziRecycle.setLayoutManager(linearLayoutManager =new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected void initListener() {
        super.initListener();
        mloadmoreListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && linearLayoutManager!=null) //向下滚动
                {
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (!loading && (visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = true;
                        page+=1;
                        loadMoreData();
                    }
                }
            }
        };
    }

    private void loadMoreData() {
        Log.e("当前页数==",page+"");
        meiziPrestener.getMeiziInfo("",page);

    }


    @Override
    public void getMeiziDataList(List<MeiziInfo.MeiziBean> meiziInfos) {

        mMeiziSwipe.setRefreshing(false);
        if(meiziInfos==null || meiziInfos.size()<=0)
            return;
        loading = false;
        this.meiziInfos.addAll(meiziInfos);
        Log.e("妹子数据集合==",meiziInfos.size()+"");
        if(mMeiziAdapter==null) {
            mMeiziRecycle.setAdapter(mMeiziAdapter = new MeiziAdapter(mActivity, this.meiziInfos));
        }else{
            mMeiziAdapter.notifyDataSetChanged();
        }

        mMeiziRecycle.addOnScrollListener(mloadmoreListener);
    }

    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                meiziInfos.clear();
                page = 1;
                loading = false;
                meiziPrestener.getMeiziInfo("",page);
            }
        },1000);
    }
}
