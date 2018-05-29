package com.example.mobiapp.newhoroscope.classes;

import android.graphics.drawable.Drawable;

/**
 * Created by mobi app on 30.11.2017.
 */

public class ItemHoro {

    String name;
    String name_eng;
    Drawable img;
    boolean isCheck;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_eng() {
        return name_eng;
    }

    public void setName_eng(String name_eng) {
        this.name_eng = name_eng;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public ItemHoro(String name, String name_eng, Drawable img, boolean isCheck) {

        this.name = name;
        this.name_eng = name_eng;
        this.img = img;
        this.isCheck = isCheck;
    }
}
