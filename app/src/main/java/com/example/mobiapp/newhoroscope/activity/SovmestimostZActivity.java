package com.example.mobiapp.newhoroscope.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mobiapp.newhoroscope.MainActivity;
import com.example.mobiapp.newhoroscope.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SovmestimostZActivity extends AppCompatActivity {


    Spinner spM, spZh;
    Button btn;
    TextView tvName, tvText;
    Document doc;
    String p = null;
    String textResult = "";
    ProgressBar progress;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sovmestimost_z);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();
    }

    private void initView() {

        progress = (ProgressBar) findViewById(R.id.sz_progress);
        spM = (Spinner) findViewById(R.id.sz_spM);
        spZh = (Spinner) findViewById(R.id.sz_spZH);
        btn = (Button) findViewById(R.id.sz_btn);
        tvName = (TextView) findViewById(R.id.sz_name);
        tvText = (TextView) findViewById(R.id.sz_text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int znak1 = getZnak1Int(spM.getSelectedItem().toString());
                int znak2 = getZnak2Int(spZh.getSelectedItem().toString());

                new NewThread(znak1,znak2).execute();
            }
        });
        MobileAds.initialize(this,"ca-app-pub-8644281007634725~2462156094");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public int getZnak1Int(String znak1){

        int i = 0;

        switch (znak1){
            case "Овен":
                i= 14;
                break;

            case "Телец":
                i= 10;
                break;
            case "Близнецы":
                i= 15;
                break;
            case "Рак":
                i= 8;
                break;
            case "Лев":
                i= 9;
                break;
            case "Дева":
                i= 16;
                break;
            case "Весы":
                i= 17;
                break;
            case "Скорпион":
                i= 18;
                break;
            case "Стрелец":
                i= 19;
                break;
            case "Козерог":
                i= 20;
                break;
            case "Водолей":
                i= 21;
                break;
            case "Рыбы":
                i= 22;
                break;
            default:
                i=0;
                break;

        }
        return i;

    }
    public int getZnak2Int(String znak1){

        int i = 0;

        switch (znak1){
            case "Овен":
                i= 23;
                break;

            case "Телец":
                i= 13;
                break;
            case "Близнецы":
                i= 24;
                break;
            case "Рак":
                i= 11;
                break;
            case "Лев":
                i= 12;
                break;
            case "Дева":
                i= 25;
                break;
            case "Весы":
                i= 26;
                break;
            case "Скорпион":
                i= 27;
                break;
            case "Стрелец":
                i= 28;
                break;
            case "Козерог":
                i= 29;
                break;
            case "Водолей":
                i= 30;
                break;
            case "Рыбы":
                i= 31;
                break;
            default:
                i=0;
                break;

        }
        return i;

    }
    public class NewThread extends AsyncTask<String, Void, String> {

        int znak1, znak2;

        public NewThread(int znak1, int znak2){

            this.znak1 = znak1;
            this.znak2 = znak2;


        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... arg) {

            // класс который захватывает страницу

            try {
                doc = Jsoup.connect("http://www.tarotaro.ru/portal/love/thermometer/"+znak1+"/"+znak2).get();
                Elements mainHeaderElements = doc.select("div.zodiak-about_body");
                p = mainHeaderElements.text();
                String s = p.toString();
                textResult = s;


            } catch (Exception e) {
                e.printStackTrace();
                textResult = "Проверьте подключение к интернету";
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            progress.setVisibility(View.GONE);
            tvText.setText(textResult);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                try {
                    startActivity(new Intent(SovmestimostZActivity.this, MainActivity.class));
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
