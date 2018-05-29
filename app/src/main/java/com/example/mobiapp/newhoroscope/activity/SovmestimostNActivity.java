package com.example.mobiapp.newhoroscope.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mobiapp.newhoroscope.MainActivity;
import com.example.mobiapp.newhoroscope.R;
import com.example.mobiapp.newhoroscope.data.Prefs;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SovmestimostNActivity extends AppCompatActivity {

    EditText etM, etZh;
    Button btn;
    TextView tvName, tvText;
    ProgressBar progress;

    Prefs prefs;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sovmestimost_n);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prefs = new Prefs(this);
        initView();

    }

    private void initView() {

        etM = (EditText) findViewById(R.id.sn_editM);
        etZh = (EditText) findViewById(R.id.sn_editZH);
        btn = (Button) findViewById(R.id.sn_btn);
        tvName = (TextView) findViewById(R.id.sn_name);
        tvText = (TextView) findViewById(R.id.sn_text);
        progress = (ProgressBar) findViewById(R.id.sn_progress);

        if(prefs.getName_m().length()>2) etM.setText(prefs.getName_m());
        if(prefs.getName_zh().length()>2) etZh.setText(prefs.getName_zh());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs.setName_m(etM.getText().toString());
                prefs.setName_zh(etZh.getText().toString());
                new NewThread(etM.getText().toString(), etZh.getText().toString()).execute();
            }
        });

        MobileAds.initialize(this,"ca-app-pub-8644281007634725~2462156094");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                try {
                    startActivity(new Intent(SovmestimostNActivity.this, MainActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // do what you want to be done on home button click event
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class NewThread extends AsyncTask<String, Void, String> {

        String nameM, nameW;
        String results = "";

        public NewThread(String nameM, String nameW){

            this.nameM = nameW;
            this.nameW = nameM;

        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... arg) {

            // класс который захватывает страницу
            Document doc;
            try {
                String encodeNameM = java.net.URLEncoder.encode(nameM,"cp1251");
                String encodeNameW = java.net.URLEncoder.encode(nameW,"cp1251");
                String url = "http://kakzovut.ru/sovmestimost.html?imya1="+encodeNameM+"&imya2="+encodeNameW;
                //String encodeNameM = java.net.URLEncoder.encode(url,"UTF-8");
                doc = Jsoup.connect(url).get();
                Elements mainHeaderElements = doc.select("div[class=maincontent]");
                Elements p = mainHeaderElements.select("p");
                if(p.size()>3) {
                    for (int i = 0; i < p.size(); i++) {
                        String pP = p.get(i).text();
                        String gf = "";
                        results = results + pP;
                    }
                }
                else
                    results = "Для данных имен нет информации о совместимости";
                String s = "dsd";

            } catch (Exception e) {
                e.printStackTrace();
                results = "Для данных имен нет информации о совместимости";
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            progress.setVisibility(View.GONE);
            tvText.setText(results);
            //textResult.setText(results);
        }
    }
}
