package com.bill.testgit.ui.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bill.testgit.R;
import com.bill.testgit.adapter.MeiziAdapter;
import com.bill.testgit.bean.meizi.MeiziInfo;
import com.bill.testgit.presenter.meizi.MeiziPresenterImpl;
import com.bill.testgit.util.Logger;
import com.bill.testgit.util.SwipeRefreshUtil;
import com.bill.testgit.view.custom_view.GridItemDividerDecoration;
import com.bill.testgit.view.meizi.IMeiziFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Bill on 2017/2/8.
 * 妹子Fragment
 */

public class MeiziFragment extends BaseFragment implements IMeiziFragment, SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.id_meizi_recycle)
    RecyclerView mMeiziRecycle;
    @InjectView(R.id.id_meizi_swipe)
    SwipeRefreshLayout mMeiziSwipe;
    private MeiziPresenterImpl meiziPrestener;
    private MeiziAdapter mMeiziAdapter;

    private String type = "福利";
    private int page = 1;
    private int count = 50;

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

        SwipeRefreshUtil.setSiwpeLayout(mMeiziSwipe, mActivity, this);
        meiziInfos = new ArrayList<>();
        meiziPrestener = new MeiziPresenterImpl(this);
        meiziPrestener.getMeiziInfo(type, count, page);
        setRecycleView();
    }

    private void setRecycleView() {

        mMeiziRecycle.addItemDecoration(new GridItemDividerDecoration(getContext(), R.dimen.divider_height, R.color.divider_color));
        mMeiziRecycle.setItemAnimator(new DefaultItemAnimator());
        mMeiziRecycle.setLayoutManager(linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));

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
                if (dy > 0 && linearLayoutManager != null) //向下滚动
                {
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (!loading && (visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = true;
                        page += 1;
                        loadMoreData();
                    }
                }
            }
        };
    }

    private void loadMoreData() {
        Logger.d("loadMoreData now page is: " + page);
        meiziPrestener.getMeiziInfo(type, count, page);

    }


    @Override
    public void getMeiziDataList(List<MeiziInfo.MeiziBean> meiziInfos) {

        Logger.d("getMeiziDataList");

        mMeiziSwipe.setRefreshing(false);
        if (meiziInfos == null || meiziInfos.size() <= 0) {
            Logger.d("no data from server");
            return;
        }
        loading = false;
        this.meiziInfos.addAll(meiziInfos);
        if (mMeiziAdapter == null) {
            mMeiziRecycle.setAdapter(mMeiziAdapter = new MeiziAdapter(mActivity, this.meiziInfos));
        } else {
            mMeiziAdapter.notifyDataSetChanged();
        }

        mMeiziRecycle.addOnScrollListener(mloadmoreListener);
    }

    @Override
    public void onRefresh() {

        Logger.d("onRefresh now");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                meiziInfos.clear();
                page = 1;
                loading = false;
                meiziPrestener.getMeiziInfo(type, count, page);
            }
        }, 1000);
    }
}
