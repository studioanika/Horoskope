package com.example.mobiapp.newhoroscope.retrofit;

import com.example.mobiapp.newhoroscope.retrofit.classes.DescriptionArticle;
import com.example.mobiapp.newhoroscope.retrofit.classes.Horo;
import com.example.mobiapp.newhoroscope.retrofit.classes.News;
import com.example.mobiapp.newhoroscope.retrofit.classes.ResponseName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mobi app on 28.11.2017.
 */

public interface Api {

    @GET("horoscope/get_article.php")
    Call<List<News>> getArticle();

    @GET("horoscope/get_name.php")
    Call<List<News>> getNames();

    @GET("horoscope/get_name_text.php")
    Call<List<ResponseName>> getName(@Query("name") String name);

    @GET("horoscope/get_article_descr.php")
    Call<List<DescriptionArticle>> getDescriptionArticle(@Query("href") String href);

    @GET("horoscope/get_horo.php")
    Call<List<Horo>> getHoto(@Query("znak") String znak, @Query("tm") String tm, @Query("kn") String kn);
}