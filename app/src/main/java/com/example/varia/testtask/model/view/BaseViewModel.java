package com.example.varia.testtask.model.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.varia.testtask.R;
import com.example.varia.testtask.ui.holder.BaseViewHolder;

public abstract class BaseViewModel {

    public abstract LayoutTypes getType();

    public BaseViewHolder createViewHolder(ViewGroup parent){
        return onCreateViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(getType().getValue(), parent, false));
    }

    protected abstract BaseViewHolder onCreateViewHolder(View view);

    public enum LayoutTypes{
        MainItem(R.layout.item_list_card),
        Loading(R.layout.item_loading);

        private final int id;

        LayoutTypes(int id) {
            this.id = id;
        }

        public int getValue(){
            return id;
        }
    }

}
