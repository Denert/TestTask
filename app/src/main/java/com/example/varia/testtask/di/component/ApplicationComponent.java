package com.example.varia.testtask.di.component;


import com.example.varia.testtask.di.module.ApplicationModule;
import com.example.varia.testtask.mvp.presenter.MainContentPresenter;
import com.example.varia.testtask.ui.activity.MainActivity;
import com.example.varia.testtask.ui.fragment.MainContent;
import com.example.varia.testtask.ui.fragment.ShopContent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity activity);
    void inject(MainContentPresenter presenter);
    void inject(MainContent fragment);
    void inject(ShopContent fragment);

}
