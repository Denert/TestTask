package com.example.varia.testtask.model.view;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<List<ShopViewModel>> selected = new MutableLiveData<List<ShopViewModel>>();

    public void setSelected(List<ShopViewModel> shops){
        selected.setValue(shops);
    }

    public LiveData<List<ShopViewModel>> getSelected() {
        return selected;
    }

}
