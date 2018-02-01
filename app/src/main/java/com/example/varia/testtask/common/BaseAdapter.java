package com.example.varia.testtask.common;


import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;

import com.example.varia.testtask.model.view.BaseViewModel;
import com.example.varia.testtask.ui.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseViewModel>> {

    List<BaseViewModel> list = new ArrayList<>();
    private ArrayMap<Integer, BaseViewModel> mTypeInstances = new ArrayMap<>();


    @Override
    public BaseViewHolder<BaseViewModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        return mTypeInstances.get(viewType).createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<BaseViewModel> holder, int position) {
        holder.bindViewHolder(getItem(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private BaseViewModel getItem(int position){
        return list.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType().getValue();
    }

    public void registerTypeInstance(BaseViewModel item){
        if(!mTypeInstances.containsKey(item.getType().getValue())){
            mTypeInstances.put(item.getType().getValue(), item);
        }
    }

    public void addItems(List<? extends BaseViewModel> newItems){
        for (BaseViewModel newItem : newItems){
            registerTypeInstance(newItem);
        }

        list.addAll(newItems);
        notifyDataSetChanged();
    }

    public void insertItem(BaseViewModel newItem){
        registerTypeInstance(newItem);
        list.add(newItem);
        notifyDataSetChanged();
    }

    public void deleteProgress() {
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getType() == BaseViewModel.LayoutTypes.Loading){
                list.remove(i);
            }
        }
    }
}
