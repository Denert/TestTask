package com.example.varia.testtask.mvp.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.varia.testtask.model.Shop;
import com.example.varia.testtask.model.view.ProgressViewModel;
import com.example.varia.testtask.model.view.ShopViewModel;
import com.example.varia.testtask.mvp.view.ShopsContentView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class ShopsContentPresenter extends MvpPresenter<ShopsContentView> {

    private boolean isLoading = false;
    private int page = 0;
    private int dbSize = 0;

    private Callable<List<ShopViewModel>> getDataFromDb(int page){
        return () -> {
            String[] sortFields = {"distance"};
            Sort[] sortOrder = {Sort.ASCENDING};
            Realm realm = Realm.getDefaultInstance();
            RealmResults<Shop> results = realm.where(Shop.class)
                    .findAllSorted(sortFields, sortOrder);
            dbSize = results.size();
            List<ShopViewModel> shops = new ArrayList<>();
            for (int i = page * 30; i < (page + 1) * 30; i++) {
                shops.add(new ShopViewModel(results.get(i)));
            }
            return shops;
        };
    }

    private Observable<ShopViewModel> getDbDataObservable() {
        return Observable.fromCallable(getDataFromDb(page++))
                .flatMap(Observable::fromIterable);
    }

    public void getDataOnScrolled() {

        if(isLoading)
            return;

        isLoading = true;
        page++;

        getDbDataObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    getViewState().hidePaginationProgress();
                    if (!(dbSize - (page * 30) < 30)) {
                        getViewState().addItems(new ProgressViewModel());
                    }
                    isLoading = false;
                })
                .subscribe(shopViewModel -> getViewState().addItems(shopViewModel),
                        throwable -> {
                    throwable.printStackTrace();
                    getViewState().showError();
                });

    }

}
