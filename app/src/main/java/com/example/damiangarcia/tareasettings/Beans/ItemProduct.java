package com.example.damiangarcia.tareasettings.Beans;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemProduct implements Parcelable {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;
    private int image;
    private String title, location, phone, description;
    Category category;
    Store store;

    public ItemProduct() {
        code = 0;
        image = 0;
        title = "";
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeInt(this.image);
        dest.writeString(this.title);
        dest.writeString(this.location);
        dest.writeString(this.phone);
        dest.writeString(this.description);
        dest.writeParcelable(this.category, flags);
        dest.writeParcelable(this.store, flags);
    }

    protected ItemProduct(Parcel in) {
        this.code = in.readInt();
        this.image = in.readInt();
        this.title = in.readString();
        this.location = in.readString();
        this.phone = in.readString();
        this.description = in.readString();
        this.category = in.readParcelable(Category.class.getClassLoader());
        this.store = in.readParcelable(Store.class.getClassLoader());
    }

    public static final Creator<ItemProduct> CREATOR = new Creator<ItemProduct>() {
        @Override
        public ItemProduct createFromParcel(Parcel source) {
            return new ItemProduct(source);
        }

        @Override
        public ItemProduct[] newArray(int size) {
            return new ItemProduct[size];
        }
    };
}
