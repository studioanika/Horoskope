package com.example.mobiapp.newhoroscope.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobiapp.newhoroscope.MainActivity;
import com.example.mobiapp.newhoroscope.R;
import com.example.mobiapp.newhoroscope.adapter.DayAdapter;
import com.example.mobiapp.newhoroscope.adapter.NewsAdapter;
import com.example.mobiapp.newhoroscope.adapter.TypeActionAdapter;
import com.example.mobiapp.newhoroscope.adapter.TypeAdapter;
import com.example.mobiapp.newhoroscope.adapter.ZnakAdapter;
import com.example.mobiapp.newhoroscope.classes.GenerateHoro;
import com.example.mobiapp.newhoroscope.retrofit.App;
import com.example.mobiapp.newhoroscope.retrofit.classes.Horo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoroActivity extends AppCompatActivity {

    RelativeLayout relZnack, relType, relDay;
    RecyclerView recyclerZnack, recyclerType,recyclerDay;
    ProgressBar progressBar;
    Document doc;
    String link = "http://1001goroskop.ru/?";

    TextView tvTitle, tvText;

    private RecyclerView.LayoutManager mLayoutManager;

    String znak = "";
    String type = "";
    String day = "";

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();
    }

    private void initView(){
        Log.e("horo","initView");

        tvTitle = (TextView) findViewById(R.id.title_horo);
        tvText = (TextView) findViewById(R.id.text_horo);

        relZnack = (RelativeLayout) findViewById(R.id.relZnack);
        relType = (RelativeLayout) findViewById(R.id.relType);
        relDay = (RelativeLayout) findViewById(R.id.relDay);

        recyclerZnack = (RecyclerView) findViewById(R.id.recyclerZnack);
        recyclerType = (RecyclerView) findViewById(R.id.recyclerType);
        recyclerDay = (RecyclerView) findViewById(R.id.recyclerDay);


        relZnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerZnack.getVisibility()==View.VISIBLE) recyclerZnack.setVisibility(View.GONE);
                else recyclerZnack.setVisibility(View.VISIBLE);
                Log.e("horo","click_rel_znak");
            }
        });

        relType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerType.getVisibility()==View.VISIBLE) recyclerType.setVisibility(View.GONE);
                else recyclerType.setVisibility(View.VISIBLE);
                Log.e("horo","click_rel_type");
            }
        });

        relDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerDay.getVisibility()==View.VISIBLE) recyclerDay.setVisibility(View.GONE);
                else recyclerDay.setVisibility(View.VISIBLE);
                Log.e("horo","click_rel_day");
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progress_horo);

        setRecycler();

        MobileAds.initialize(this,"ca-app-pub-8644281007634725~2462156094");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void setRecycler(){

        Log.e("horo","setRecycler");

        GenerateHoro generateHoro = new GenerateHoro(this);

        Log.e("horo","GenerateHoro");


        recyclerZnack.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(recyclerZnack.getContext());
        recyclerZnack.setLayoutManager(mLayoutManager);
        recyclerZnack.setAdapter(new ZnakAdapter(this,generateHoro.getAllZnack()));
        Log.e("horo","recyclerZnack_end");


        recyclerType.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(recyclerType.getContext());
        recyclerType.setLayoutManager(mLayoutManager);
        recyclerType.setAdapter(new TypeAdapter(this,generateHoro.getAllType()));
        Log.e("horo","recyclerType_end");

        recyclerDay.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(recyclerDay.getContext());
        recyclerDay.setLayoutManager(mLayoutManager);
        recyclerDay.setAdapter(new DayAdapter(this,generateHoro.getAllTypeDay()));
        Log.e("horo","recyclerDay_end");

        getHoro();

    }

    private void getHoro(){
        progressBar.setVisibility(View.VISIBLE);
        new NewThread(znak, type, day).execute();
    }

    private void showErrorSnack(){
        Snackbar snackbar = Snackbar.make(tvText,"Ошибка соединения..." , Snackbar.LENGTH_LONG)
                .setAction("ПОВТОРИТЬ?", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            getHoro();
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
                    startActivity(new Intent(HoroActivity.this, MainActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // do what you want to be done on home button click event
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setZnak(String s){
        znak = s;
        Log.e("horo","setZnak"+s);
        recyclerZnack.setVisibility(View.GONE);
        getHoro();
    }
    public void setDay(String s){
        day = s;
        Log.e("horo","setDay"+s);
        recyclerDay.setVisibility(View.GONE);
        getHoro();
    }
    public void setType(String s){
        type = s;
        Log.e("horo","setType"+s);
        recyclerType.setVisibility(View.GONE);
        getHoro();
    }

    public class NewThread extends AsyncTask<String, Void, String> {
        Elements news;
        Elements titles;
        String znak;
        String type;
        String day;
        String results;
        String title;


        public NewThread(String znak, String type, String day){

            this.znak = znak;
            this.day = day;
            this.type = type;

        }


        @Override
        protected String doInBackground(String... arg) {

            String myLink = link +"znak="+ znak +"&kn=" + day+"&tm="+ type;
            Log.e("link", myLink);

            try {
                doc   = Jsoup.connect(myLink).get();
                news = doc.select("div[itemprop=description]");
                titles = doc.select("h1[itemprop=name]");
                results= news.get(0).text();
                title= titles.get(0).text();
                String s = results;


            } catch (Exception e) {
                //Toast.makeText(Horoscope.this,"Проверьте подлючение к интернету",Toast.LENGTH_SHORT).show();
                results = "Проверьте подключение к интернету....";
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            String s = "";
            progressBar.setVisibility(View.GONE);
            tvText.setText(results);
            tvTitle.setText(title);
            Log.e("link", results);
        }
    }

}
