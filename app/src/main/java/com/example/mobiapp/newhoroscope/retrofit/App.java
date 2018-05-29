package com.example.mobiapp.newhoroscope.retrofit;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mobi app on 28.11.2017.
 */
public class App extends Application {

    private static Api umoriliApi;
    private Retrofit retrofit;
    private static final String APP_PREFERENCES = "config";
    private SharedPreferences mSettings;

    @Override
    public void onCreate() {
        super.onCreate();

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://anika-cs.by/server/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        umoriliApi = retrofit.create(Api.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static Api getApi() {
        return umoriliApi;
    }
}