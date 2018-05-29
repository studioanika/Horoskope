package com.example.mobiapp.newhoroscope.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mobi app on 04.12.2017.
 */

public class Prefs {

    Context context;
    private static final String APP_PREFERENCES = "config";
    private static final String APP_PREFERENCES_NAME_TAINA = "name_taina";
    private static final String APP_PREFERENCES_DATE = "date";
    private static final String APP_PREFERENCES_NAME_M = "name1";
    private static final String APP_PREFERENCES_NAME_ZH = "name2";
    private SharedPreferences mSettings;

    public Prefs(Context context) {
        this.context = context;
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

    }
    public String  getNameT(){
        return mSettings.getString(APP_PREFERENCES_NAME_TAINA,"0");
    }

    public void setNameT(String s){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_NAME_TAINA, s);
        editor.apply();
    }

    public String  getDate(){
        return mSettings.getString(APP_PREFERENCES_DATE,"0");
    }

    public void setDate(String s){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_DATE, s);
        editor.apply();
    }

    public String  getName_m(){
        return mSettings.getString(APP_PREFERENCES_NAME_M,"0");
    }

    public void setName_m(String s){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_NAME_M, s);
        editor.apply();
    }

    public String  getName_zh(){
        return mSettings.getString(APP_PREFERENCES_NAME_ZH,"0");
    }

    public void setName_zh(String s){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_NAME_ZH, s);
        editor.apply();
    }

}
