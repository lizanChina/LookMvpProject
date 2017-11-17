package com.bill.testgit.ui.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bill.testgit.R;
import com.bill.testgit.adapter.ZhihuAdapter;
import com.bill.testgit.bean.zhihu.ZhihuInfo;
import com.bill.testgit.presenter.zhihu.IZhihuPresenter;
import com.bill.testgit.presenter.zhihu.ZhihuPresenterImpl;
import com.bill.testgit.util.SwipeRefreshUtil;
import com.bill.testgit.view.custom_view.GridItemDividerDecoration;
import com.bill.testgit.view.zhihu.IZhihuFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Bill on 2017/2/8.
 * 知乎Fragment
 */

public class ZhihuFragment extends BaseFragment implements IZhihuFragment, SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.id_recycle_zhihu)
    RecyclerView mZhihuRecycle;
    @InjectView(R.id.id_zhihu_swipe)
    SwipeRefreshLayout mZhihuSwipe;
    private IZhihuPresenter zhihuPresenter;
    private String url = "http://news-at.zhihu.com";
    private String currentLoadDate = "0";
    private ZhihuAdapter mZhihuAdapter;
    private List<ZhihuInfo.ZhihuBean> zhihuInfos;
    private LinearLayoutManager linearLayoutManager;
    private boolean loading;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_zhihu, null);
        return view;
    }

    @Override
    public void initData() {

        SwipeRefreshUtil.setSiwpeLayout(mZhihuSwipe, mActivity, this);
        zhihuInfos = new ArrayList<>();
        zhihuPresenter = new ZhihuPresenterImpl(this);
        zhihuPresenter.getLastZhihuNews(url);
        setRecycleView();
    }

    private void setRecycleView() {

        mZhihuRecycle.addItemDecoration(new GridItemDividerDecoration(getContext(), R.dimen.divider_height, R.color.divider_color));
        mZhihuRecycle.setItemAnimator(new DefaultItemAnimator());
        mZhihuRecycle.setLayoutManager(linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }


    @Override
    public void getZhihuInfoList(String date, List<ZhihuInfo.ZhihuBean> zhihuInfos) {

        if (zhihuInfos == null || zhihuInfos.size() <= 0)
            return;
        loading = false;
        mZhihuSwipe.setRefreshing(false);
        this.zhihuInfos.addAll(zhihuInfos);
        this.currentLoadDate = date;
        Log.e("知乎集合 info", zhihuInfos.size() + "");
        if (mZhihuAdapter == null) {
            mZhihuRecycle.setAdapter(mZhihuAdapter = new ZhihuAdapter(mActivity, this.zhihuInfos));
        } else {
            mZhihuAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void initListener() {
        super.initListener();
        mZhihuRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && linearLayoutManager != null) {
                    int totalCout = linearLayoutManager.getItemCount();
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    if (!loading && (visibleItemCount + firstVisibleItemPosition) >= totalCout) {
                        loading = true;
                        loadMoreData();
                    }
                }
            }
        });
    }

    private void loadMoreData() {

        Log.e("currentLoadDate==", currentLoadDate);
        zhihuPresenter.getZhihuInfo("", currentLoadDate);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                zhihuInfos.clear();
                currentLoadDate = "0";
                loading = false;
                zhihuPresenter.getLastZhihuNews(url);
            }
        }, 1000);
    }
}
