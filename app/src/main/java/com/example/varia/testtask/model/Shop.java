package com.example.varia.testtask.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Shop extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("city")
    @Expose
    private Integer city;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("metro")
    @Expose
    private Integer metro;
    @SerializedName("metro_string")
    @Expose
    private String metroString;
    @SerializedName("opening_hours")
    @Expose
    private String openingHours;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("district_type")
    @Expose
    private String districtType;
    @SerializedName("street_name")
    @Expose
    private String streetName;
    @SerializedName("street_type")
    @Expose
    private String streetType;
    @SerializedName("house")
    @Expose
    private String house;
    @SerializedName("housing")
    @Expose
    private String housing;
    @SerializedName("index")
    @Expose
    private String index;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("zoom")
    @Expose
    private Integer zoom;
    @SerializedName("search")
    @Expose
    private String search;

    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @SerializedName("abilities")
    @Expose
    private RealmList<String> abilities;
    @SerializedName("options")
    @Expose
    private RealmList<String> options;
    @SerializedName("nearest")
    @Expose
    private RealmList<Integer> nearest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMetro() {
        return metro;
    }

    public void setMetro(Integer metro) {
        this.metro = metro;
    }

    public String getMetroString() {
        return metroString;
    }

    public void setMetroString(String metroString) {
        this.metroString = metroString;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictType() {
        return districtType;
    }

    public void setDistrictType(String districtType) {
        this.districtType = districtType;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public RealmList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(RealmList<String> abilities) {
        this.abilities = abilities;
    }

    public RealmList<String> getOptions() {
        return options;
    }

    public void setOptions(RealmList<String> options) {
        this.options = options;
    }

    public RealmList<Integer> getNearest() {
        return nearest;
    }

    public void setNearest(RealmList<Integer> nearest) {
        this.nearest = nearest;
    }

}
