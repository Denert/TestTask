package com.example.varia.testtask;


import android.app.Application;

import com.example.varia.testtask.di.component.ApplicationComponent;
import com.example.varia.testtask.di.component.DaggerApplicationComponent;
import com.example.varia.testtask.di.module.ApplicationModule;

public class MyApplication extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();
    }

    private void initComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
