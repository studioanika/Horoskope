package com.example.mobiapp.newhoroscope.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobiapp.newhoroscope.MainActivity;
import com.example.mobiapp.newhoroscope.R;
import com.example.mobiapp.newhoroscope.retrofit.App;
import com.example.mobiapp.newhoroscope.retrofit.classes.DescriptionArticle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionActivity extends AppCompatActivity {

    ImageView img;
    TextView tvTitle, tvText;
    RelativeLayout rel;

    String href = "";
    String img_ = "";

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        href = intent.getStringExtra("href");
        img_ = intent.getStringExtra("img");

        getSupportActionBar().setTitle(title);

        initView();
        getArticle();
    }

    private void initView() {

       img = (ImageView) findViewById(R.id.descr_img);
       tvTitle = (TextView) findViewById(R.id.descr_title);
       tvText = (TextView) findViewById(R.id.descr_text);
       rel = (RelativeLayout) findViewById(R.id.descr_rel);

        MobileAds.initialize(this,"ca-app-pub-8644281007634725~2462156094");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void getArticle(){
        rel.setVisibility(View.VISIBLE);
        App.getApi().getDescriptionArticle(href).enqueue(new Callback<List<DescriptionArticle>>() {
            @Override
            public void onResponse(Call<List<DescriptionArticle>> call, Response<List<DescriptionArticle>> response) {
                try {
                    DescriptionArticle article = response.body().get(0);
                    Glide.with(img.getContext()).load(img_).into(img);
                    tvTitle.setText(article.getTitle());
                    tvText.setText(article.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rel.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<DescriptionArticle>> call, Throwable t) {
            showErrorSnack();
            }
        });
    }

    private void showErrorSnack(){
        Snackbar snackbar = Snackbar.make(tvText,"Ошибка соединения..." , Snackbar.LENGTH_LONG)
                .setAction("ПОВТОРИТЬ?", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            getArticle();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        snackbar.setDuration(3000);
        // 8 секунд
        snackbar.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                try {
                    startActivity(new Intent(DescriptionActivity.this, MainActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // do what you want to be done on home button click event
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
