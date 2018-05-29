package com.example.mobiapp.newhoroscope.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiapp.newhoroscope.MainActivity;
import com.example.mobiapp.newhoroscope.R;
import com.example.mobiapp.newhoroscope.data.Prefs;
import com.example.mobiapp.newhoroscope.retrofit.App;
import com.example.mobiapp.newhoroscope.retrofit.classes.ResponseName;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TainaActivity extends AppCompatActivity {

    SearchView searchView;
    TextView tvTitle, tvText;
    ProgressBar progressBar;
    Prefs prefs;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taina);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prefs = new Prefs(this);
        initView();

    }

    private void initView() {

        searchView = (SearchView) findViewById(R.id.taina_search);
        tvText = (TextView) findViewById(R.id.taina_text);
        tvTitle = (TextView) findViewById(R.id.taina_title);
        progressBar = (ProgressBar) findViewById(R.id.taina_progress);

        searchView.setQueryHint("Введите имя");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getName(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        if(prefs.getNameT().length()>1) getName(prefs.getNameT());
        MobileAds.initialize(this,"ca-app-pub-8644281007634725~2462156094");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //searchView.setFocusable(true);
        //searchView.setQuery("Введите имя", false);
    }

    private void getName(final String s){
        progressBar.setVisibility(View.VISIBLE);
        Log.e("taina_response_S",s);
        String query = s;
        try {
            query = URLEncoder.encode(query, "utf-8");
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        Log.e("taina_response_query",query);

        App.getApi().getName(s).enqueue(new Callback<List<ResponseName>>() {
            @Override
            public void onResponse(Call<List<ResponseName>> call, Response<List<ResponseName>> response) {

                if(response.body().get(0).getText().size()!=0) {
                    Log.e("taina_response",response.body().get(0).getText().get(0));
                    tvTitle.setText("Тайна имени "+s);
                    tvText.setText(response.body().get(0).getText().get(0));
                    prefs.setNameT(s);
                }else {
                    Toast.makeText(tvText.getContext(),"Ничего не найдено",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<ResponseName>> call, Throwable t) {
                showErrorSnack();
                Log.e("taina_error",t.toString());
                progressBar.setVisibility(View.GONE);
            }
        });



    }

    private void showErrorSnack(){
        Snackbar snackbar = Snackbar.make(tvText,"Ошибка соединения..." , Snackbar.LENGTH_LONG)
                .setAction("ПОВТОРИТЬ?", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                           getName(searchView.getQuery().toString());
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
                    startActivity(new Intent(TainaActivity.this, MainActivity.class));
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
