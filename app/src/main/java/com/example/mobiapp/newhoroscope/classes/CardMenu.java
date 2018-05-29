package com.example.mobiapp.newhoroscope.classes;

import android.graphics.drawable.Drawable;

/**
 * Created by mobi app on 29.11.2017.
 */

public class CardMenu {

    String title;
    Drawable img;
    String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CardMenu(String title, Drawable img, String text) {

        this.title = title;
        this.img = img;
        this.text = text;
    }
}
