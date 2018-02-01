package com.example.varia.testtask.mvp.view;


import com.arellomobile.mvp.MvpView;
import com.example.varia.testtask.model.view.BaseViewModel;
import com.example.varia.testtask.model.view.ShopViewModel;

public interface ShopsContentView extends MvpView {

    void addItems(BaseViewModel model);

    void showError();

    void showPaginationProgress();
    void hidePaginationProgress();
}
