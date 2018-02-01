package com.example.varia.testtask.ui.activity;

import android.content.Context;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.varia.testtask.MyApplication;
import com.example.varia.testtask.R;
import com.example.varia.testtask.mvp.view.MainActivityView;
import com.example.varia.testtask.ui.fragment.MainContent;
import com.example.varia.testtask.ui.fragment.ShopContent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView, AppBarLayout.OnOffsetChangedListener {

    @Inject
    Context mContext;

    @BindView(R.id.textView)
    TextView mMainContent;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.appbar)
    AppBarLayout appBar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.materialup_tabs)
    TabLayout tabLayout;

    @BindView(R.id.materialup_viewpager)
    ViewPager viewPager;

    @BindView(R.id.background)
    FrameLayout layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplication.getApplicationComponent().inject(this);
        ButterKnife.bind(this);

        Realm.init(mContext);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());


        refreshLayout.setOnRefreshListener(() ->
                refreshLayout.postDelayed(() ->
                        refreshLayout.setRefreshing(false), 3000
                )
        );

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showLoad() {
        mMainContent.setText("Загрузка справочника...");
    }

    @Override
    public void hideLoad(int count) {
        if (count != 0)
            mMainContent.setText("Загружено " + count + " объектов");
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Ошибка загрузки", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        float percentage = ((float)Math.abs(verticalOffset)/appBarLayout.getTotalScrollRange());
        layout.setAlpha(percentage);
        refreshLayout.setEnabled(verticalOffset == 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        appBar.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appBar.removeOnOffsetChangedListener(this);
    }

    private static class TabsAdapter extends FragmentPagerAdapter {
        private static final int TAB_COUNT = 2;

        TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
                    return MainContent.newInstance();
                case 1:
                    return ShopContent.newInstance();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Вкладка " + String.valueOf(position + 1);
        }
    }
}
