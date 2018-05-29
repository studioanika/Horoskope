package com.example.mobiapp.newhoroscope.retrofit.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mobi app on 04.12.2017.
 */

public class ResponseName {

    @SerializedName("text")
    @Expose
    private List<String> text = null;

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

}
