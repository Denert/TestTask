package com.example.varia.testtask.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Response extends RealmObject {

    @SerializedName("shop")
    @Expose
    private RealmList<Shop> shop;

    public RealmList<Shop> getShop() {
        return shop;
    }

    public void setShop(RealmList<Shop> shop) {
        this.shop = shop;
    }

}
