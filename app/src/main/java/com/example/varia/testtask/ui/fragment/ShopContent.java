package com.example.varia.testtask.ui.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.varia.testtask.MyApplication;
import com.example.varia.testtask.R;
import com.example.varia.testtask.common.BaseAdapter;
import com.example.varia.testtask.model.view.BaseViewModel;
import com.example.varia.testtask.model.view.ProgressViewModel;
import com.example.varia.testtask.model.view.SharedViewModel;
import com.example.varia.testtask.mvp.presenter.ShopsContentPresenter;
import com.example.varia.testtask.mvp.view.ShopsContentView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopContent extends MvpAppCompatFragment implements ShopsContentView {


    private RecyclerView mRootView;
    private BaseAdapter adapter;

    @InjectPresenter
    ShopsContentPresenter presenter;

    public ShopContent() {
        MyApplication.getApplicationComponent().inject(this);
        adapter = new BaseAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = (RecyclerView) inflater.inflate(R.layout.fragment_task2, container, false);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRootView.setLayoutManager(manager);
        mRootView.setAdapter(adapter);
        mRootView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = manager.getChildCount();
                int totalItemCount = manager.getItemCount();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        presenter.getDataOnScrolled();
                    }
            }
        });

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedViewModel model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        model.getSelected().observe(this, example -> {
            adapter.addItems(example);
            adapter.insertItem(new ProgressViewModel());
        });
    }

    public static Fragment newInstance() {
        return new ShopContent();
    }

    @Override
    public void addItems(BaseViewModel model) {
        adapter.insertItem(model);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showPaginationProgress() {

    }

    @Override
    public void hidePaginationProgress() {
        adapter.deleteProgress();
    }
}
