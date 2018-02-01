package com.example.varia.testtask.mvp.view;


import com.arellomobile.mvp.MvpView;

public interface MainActivityView extends MvpView {

    void showLoad();
    void hideLoad(int count);
    void showError();

}
