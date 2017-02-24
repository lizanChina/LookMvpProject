package com.zlc.lookmvp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.zlc.lookmvp.R;
import com.zlc.lookmvp.ui.fragment.MeiziFragment;
import com.zlc.lookmvp.ui.fragment.NewsFragment;
import com.zlc.lookmvp.ui.fragment.ZhihuFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.nav_view)
    NavigationView navView;
    @InjectView(R.id.drawer)
    DrawerLayout drawer;
    Fragment currentFragment;
    SimpleArrayMap<Integer, String> mTitleArryMap = new SimpleArrayMap<>();
    private ZhihuFragment zhihuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        initData();


        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        switchFragment(zhihuFragment = new ZhihuFragment());
        currentFragment = zhihuFragment;
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switchFragment(getFragmentById(item.getItemId()));
                Log.e("条目内容===",item.toString());
                drawer.closeDrawer(GravityCompat.END);
                return true;
            }
        });

    }

    private void initData() {

    }

    private Fragment getFragmentById(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.zhihuitem:
                fragment = new ZhihuFragment();
                break;
            case R.id.topnewsitem:
                fragment=new NewsFragment();
                break;
            case R.id.meiziitem:
                fragment=new MeiziFragment();
                break;

        }
        return fragment;
    }

    private void switchFragment(Fragment fragment) {

        if (currentFragment == null || !currentFragment.getClass().getName().equals(fragment.getClass().getName()))
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
                    .commit();
        currentFragment = fragment;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_open:
                    drawer.openDrawer(GravityCompat.END);
                    break;
            }
            return true;
        }};
}
