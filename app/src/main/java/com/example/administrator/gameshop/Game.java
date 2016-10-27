package com.example.administrator.gameshop;

/**
 * Created by Administrator on 2016/10/24.
 */

public class Game {
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
}
