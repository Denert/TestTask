package com.example.varia.testtask.ui.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.varia.testtask.MyApplication;
import com.example.varia.testtask.R;
import com.example.varia.testtask.model.view.SharedViewModel;
import com.example.varia.testtask.model.view.ShopViewModel;
import com.example.varia.testtask.mvp.presenter.MainContentPresenter;
import com.example.varia.testtask.mvp.view.MainContentView;
import com.example.varia.testtask.ui.activity.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainContent extends MvpAppCompatFragment implements MainContentView {

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    private SharedViewModel model;

    private NestedScrollView mRootView;

    @BindView(R.id.button2)
    Button startLoad;

    @InjectPresenter
    MainContentPresenter presenter;

    public MainContent() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = (NestedScrollView) inflater.inflate(R.layout.fragment_task1, container, false);
        ButterKnife.bind(this, mRootView);

        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        startLoad.setOnClickListener(v -> presenter.loadFirstData());
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static Fragment newInstance() {
        return new MainContent();
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showLoad();
    }

    @Override
    public void endLoading(int count) {
        ((MainActivity) getActivity()).hideLoad(count);
    }

    @Override
    public void showError() {
        ((MainActivity) getActivity()).showError();
    }

    @Override
    public void sendData(List<ShopViewModel> shops) {
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        model.setSelected(shops);
    }
}
