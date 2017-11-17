package com.bill.testgit.ui.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bill.testgit.R;
import com.bill.testgit.adapter.NewsAdapter;
import com.bill.testgit.bean.news.NewsInfo;
import com.bill.testgit.presenter.news.INewsPresenter;
import com.bill.testgit.presenter.news.NewsPresenterImpl;
import com.bill.testgit.util.SwipeRefreshUtil;
import com.bill.testgit.view.custom_view.GridItemDividerDecoration;
import com.bill.testgit.view.news.INewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Bill on 2017/2/8.
 * 新闻Fragment
 */

public class NewsFragment extends BaseFragment implements INewsFragment, SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.id_recycle_news)
    RecyclerView mRecycleNews;
    @InjectView(R.id.id_news_swipe)
    SwipeRefreshLayout mNewsSwipe;
    private INewsPresenter newsPresenter;
    private String url = "http://c.m.163.com";
    private int currentIndex = 0;
    private NewsAdapter mNewsAdapter;
    private List<NewsInfo.NewsBean> newsInfos;
    private LinearLayoutManager linearLayoutManager;
    private boolean loading;

    //    private String url = "http://192.168.1.108:8001/shopping/";
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_news, null);
        return view;
    }

    @Override
    public void initData() {

        SwipeRefreshUtil.setSiwpeLayout(mNewsSwipe, mActivity, this);
        newsInfos = new ArrayList<>();
        newsPresenter = new NewsPresenterImpl(this);
        newsPresenter.getNewsInfo("", currentIndex);
        setRecycleView();
    }

    private void setRecycleView() {
        mRecycleNews.addItemDecoration(new GridItemDividerDecoration(getContext(), R.dimen.divider_height, R.color.divider_color));
        mRecycleNews.setItemAnimator(new DefaultItemAnimator());
        mRecycleNews.setLayoutManager(linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListener() {
        super.initListener();

        mRecycleNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        currentIndex += 20;
                        loadMoreData();
                    }
                }
            }
        });
    }

    private void loadMoreData() {
        Log.e("当前index", currentIndex + "");
        newsPresenter.getNewsInfo("", currentIndex);
    }


    @Override
    public void getNewsInfoList(List<NewsInfo.NewsBean> newsInfos) {

        if (newsInfos == null || newsInfos.size() <= 0)
            return;
        mNewsSwipe.setRefreshing(false);
        loading = false;
        this.newsInfos.addAll(newsInfos);
        Log.e("网易新闻集合 info", newsInfos.size() + "");
        if (mNewsAdapter == null) {
            mRecycleNews.setAdapter(mNewsAdapter = new NewsAdapter(mActivity, this.newsInfos));
        } else {
            mNewsAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                newsInfos.clear();
                currentIndex = 0;
                loading = false;
                newsPresenter.getNewsInfo("", currentIndex);
            }
        }, 1000);
    }
}
