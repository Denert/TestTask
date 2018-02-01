package com.example.varia.testtask.di.module;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private Application mApplication;

    public ApplicationModule(Application mApplication){
        this.mApplication = mApplication;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mApplication;
    }

}
