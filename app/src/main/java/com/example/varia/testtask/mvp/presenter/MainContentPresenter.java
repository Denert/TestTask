package com.example.varia.testtask.mvp.presenter;


import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.varia.testtask.MyApplication;
import com.example.varia.testtask.model.Response;
import com.example.varia.testtask.model.Shop;
import com.example.varia.testtask.model.view.ShopViewModel;
import com.example.varia.testtask.mvp.view.MainContentView;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class MainContentPresenter extends MvpPresenter<MainContentView> {
    @Inject
    Context context;

    Location myLocation;

    public MainContentPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    private void saveToDb(RealmObject object){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(object));
    }

    private List<ShopViewModel> getDataFromDb(int page){
        String[] sortFields = {"distance"};
        Sort[] sortOrder = {Sort.ASCENDING};
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Shop> results = realm.where(Shop.class)
                .findAllSorted(sortFields, sortOrder);
        List<ShopViewModel> shops = new ArrayList<>();
        for (int i = page * 30; i < (page + 1) * 30; i++){
            shops.add(new ShopViewModel(results.get(i)));
        }
        return shops;
    }

    public void loadFirstData(){

        new Async().execute();
    }

    class Async extends AsyncTask<Void, Void, Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getViewState().showLoading();
            myLocation = new Location("locA");
            myLocation.setLongitude(50d);
            myLocation.setLongitude(60d);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            getViewState().endLoading(integer);
            getViewState().sendData(getDataFromDb(0));
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            String json = null;

            try {
                InputStream inputStream = context.getAssets().open("shops.json");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                json = new String(buffer, "UTF-8");

            } catch (IOException e) {
                e.printStackTrace();
            }

            Response response = new Gson().fromJson(json, Response.class);
            Location location = new Location("location B");
            for (int i = 0; i < response.getShop().size(); i++){
                location.setLatitude(Double.valueOf(response.getShop().get(i).getLatitude()));
                location.setLongitude(Double.valueOf(response.getShop().get(i).getLongitude()));
                response.getShop().get(i).setDistance(myLocation.distanceTo(location));
                saveToDb(response.getShop().get(i));
            }
            Realm realm = Realm.getDefaultInstance();
            return realm.where(Shop.class).findAll().size();
        }
    }
}
