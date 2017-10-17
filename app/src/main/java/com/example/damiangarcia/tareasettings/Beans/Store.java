package com.example.damiangarcia.tareasettings.Beans;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Store implements Parcelable {

    private Integer id;
    private String name;
    private String phone;
    private City city;
    private Integer thumbnail;
    private Double latitude;
    private Double longitude;

    public Store(){
        name = "";
        phone =  "";
        city = new City();
        thumbnail = new Integer(0);
        latitude = new Double(0);
        longitude = new Double(0);
    }

    public Store(Integer id, String name, String phone, City city, Integer thumbnail, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.thumbnail = thumbnail;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Integer thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeParcelable(this.city, flags);
        dest.writeValue(this.thumbnail);
        dest.writeValue(this.latitude);
        dest.writeValue(this.longitude);
    }

    protected Store(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.phone = in.readString();
        this.city = in.readParcelable(City.class.getClassLoader());
        this.thumbnail = (Integer) in.readValue(Integer.class.getClassLoader());
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel source) {
            return new Store(source);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };
}
