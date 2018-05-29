package com.example.mobiapp.newhoroscope.retrofit.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mobi app on 29.11.2017.
 */

public class Name {

    @SerializedName("name_ru")
    @Expose
    private String nameRu;
    @SerializedName("name_eng")
    @Expose
    private String nameEng;

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

}
