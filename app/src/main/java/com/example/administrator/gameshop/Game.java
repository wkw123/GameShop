package com.example.administrator.gameshop;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/24.
 */

public class Game implements Parcelable {
    private int id;
    private String name;
    private String picture_1;
    private String picture_2;
    private String picture_3;
    private String picture_4;
    private String picture_5;
    private int price;
    private String sort_1;
    private String sort_2;
    private String sort_3;

    public Game() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture_1() {
        return picture_1;
    }

    public void setPicture_1(String picture_1) {
        this.picture_1 = picture_1;
    }

    public String getPicture_2() {
        return picture_2;
    }

    public void setPicture_2(String picture_2) {
        this.picture_2 = picture_2;
    }

    public String getPicture_3() {
        return picture_3;
    }

    public void setPicture_3(String picture_3) {
        this.picture_3 = picture_3;
    }

    public String getPicture_4() {
        return picture_4;
    }

    public void setPicture_4(String picture_4) {
        this.picture_4 = picture_4;
    }

    public String getPicture_5() {
        return picture_5;
    }

    public void setPicture_5(String picture_5) {
        this.picture_5 = picture_5;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSort_1() {
        return sort_1;
    }

    public void setSort_1(String sort_1) {
        this.sort_1 = sort_1;
    }

    public String getSort_2() {
        return sort_2;
    }

    public void setSort_2(String sort_2) {
        this.sort_2 = sort_2;
    }

    public String getSort_3() {
        return sort_3;
    }

    public void setSort_3(String sort_3) {
        this.sort_3 = sort_3;
    }

    @Override
    public String toString() {
        return "id = " + id + " name = " + name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(picture_1);
        dest.writeString(picture_2);
        dest.writeString(picture_3);
        dest.writeString(picture_4);
        dest.writeString(picture_5);
        dest.writeInt(price);
        dest.writeString(sort_1);
        dest.writeString(sort_2);
        dest.writeString(sort_3);

    }
    public static final Parcelable.Creator<Game> CREATOR=new Parcelable.Creator<Game>() {

                     @Override
               public Game createFromParcel(Parcel source) {

                     Game game=new Game();
                         game.id = source.readInt();
                         game.name=source.readString();
                         game.picture_1 = source.readString();
                         game.picture_2 = source.readString();
                         game.picture_3 = source.readString();
                         game.picture_4 = source.readString();
                         game.picture_5 = source.readString();
                         game.price = source.readInt();
                         game.sort_1 = source.readString();
                         game.sort_2 = source.readString();
                         game.sort_3 = source.readString();
                       return game;
                 }

                @Override
              public Game[] newArray(int size) {
                     // TODO Auto-generated method stub
                    return new Game[size];
                }
            };

}
