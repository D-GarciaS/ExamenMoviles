package com.example.damiangarcia.tareasettings.Beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Damian Garcia on 10/15/2017.
 */

public class Category implements Parcelable {
    private int idCategory;
    private String name;

    public Category() {
    }

    public Category(int idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idCategory);
        dest.writeString(this.name);
    }

    protected Category(Parcel in) {
        this.idCategory = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
