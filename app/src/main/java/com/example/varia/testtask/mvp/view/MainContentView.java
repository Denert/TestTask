package com.example.varia.testtask.mvp.view;


import android.location.Location;

import com.arellomobile.mvp.MvpView;
import com.example.varia.testtask.model.view.ShopViewModel;

import java.util.List;

public interface MainContentView extends MvpView {
    void showLoading();
    void endLoading(int count);
    void showError();
    void sendData(List<ShopViewModel> shops);
}
