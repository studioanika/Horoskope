package com.example.mobiapp.newhoroscope.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.mobiapp.newhoroscope.MainActivity;
import com.example.mobiapp.newhoroscope.R;
import com.example.mobiapp.newhoroscope.data.Prefs;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PortretActivity extends AppCompatActivity {

    TextView btn, card1_title, card1_text, card1_descr,card2_title, card2_text, card2_descr,card3_title, card3_text, card3_descr;
    CardView card;
    ScrollView scrollView;
    ProgressBar progress;

    String descr1 = "";
    String descr2 = "";
    String descr3 = "";

    int DIALOG_DATE = 1;
    int myYear = 2016;
    int myMonth = 01;
    int myDay = 01;

    boolean isDescr1 = false;
    boolean isDescr2 = false;
    boolean isDescr3 = false;

    String text11, text22, text33;

    String link = "http://1001goroskop.ru/astroportret/?";

    Prefs prefs;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portret);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prefs = new Prefs(this);
        initView();
    }

    private void initView(){

        btn = (TextView) findViewById(R.id.portret_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_DATE);
            }
        });

        progress = (ProgressBar) findViewById(R.id.portret_progress);
        card1_title = (TextView) findViewById(R.id.portret_card1_title);
        card1_text = (TextView) findViewById(R.id.portret_card1_text);
        card1_descr = (TextView) findViewById(R.id.portret_card1_descr);
        card2_title = (TextView) findViewById(R.id.portret_card2_title);
        card2_text = (TextView) findViewById(R.id.portret_card2_text);
        card2_descr = (TextView) findViewById(R.id.portret_card2_descr);
        card3_title = (TextView) findViewById(R.id.portret_card3_title);
        card3_text = (TextView) findViewById(R.id.portret_card3_text);
        card3_descr = (TextView) findViewById(R.id.portret_card3_descr);
        scrollView = (ScrollView) findViewById(R.id.portret_scroll);
        card = (CardView) findViewById(R.id.portret_card);

        if(prefs.getDate().length()>2) {
            card.setVisibility(View.GONE);
            //scrollView.setVisibility(View.VISIBLE);
            String[] arr = prefs.getDate().split("/");
            btn.setText(prefs.getDate());
            new Parse(arr[0], arr[1], arr[2]).execute();
        }else {
            card.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }

        card1_descr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isDescr1){
                    card1_text.setText(descr1);
                    card1_descr.setText("СКРЫТЬ");
                    isDescr1 = true;
                }else {
                    card1_text.setText(text11);
                    card1_descr.setText("ПОДРОБНЕЕ");
                    isDescr1 = false;
                }
            }
        });

        card2_descr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isDescr2){
                    card2_text.setText(descr2);
                    card2_descr.setText("СКРЫТЬ");
                    isDescr2 = true;
                }else {
                    card2_text.setText(text22);
                    card2_descr.setText("ПОДРОБНЕЕ");
                    isDescr2 = false;
                }
            }
        });

        card3_descr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isDescr3){
                    card3_text.setText(descr3);
                    card3_descr.setText("СКРЫТЬ");
                    isDescr3 = true;
                }else {
                    card3_text.setText(text33);
                    card3_descr.setText("ПОДРОБНЕЕ");
                    isDescr3 = false;
                }
            }
        });

        MobileAds.initialize(this,"ca-app-pub-8644281007634725~2462156094");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear+1;
            myDay = dayOfMonth;

            String sDay = String.valueOf(myDay);
            String sMonth = String.valueOf(myMonth);
            String sYear = String.valueOf(myYear);
            if(myDay<10) sDay = "0"+myDay;
            if(myMonth<10) sMonth = "0"+myMonth;
            btn.setText(sDay+"/"+sMonth+"/"+sYear);
            prefs.setDate(sDay+"/"+sMonth+"/"+sYear);

            new Parse(sDay, sMonth, sYear).execute();


        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                try {
                    startActivity(new Intent(PortretActivity.this, MainActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // do what you want to be done on home button click event
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    class Parse extends AsyncTask<String, String, Void> {

        Elements news;
        Elements opp;
        String day, month, year;
        String znakZadiaka1, znakZadiaka2, znakZadiaka3;




        public Parse(String day, String month, String year){
            this.day = day;
            this.month = month;
            this.year = year;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);

        }
        @Override
        protected Void doInBackground(String... urls) {
            org.jsoup.nodes.Document doc2;
            String myLink = link+ "day="+day + "&month="+month + "&year="+year + "&goro=all";
            try {

                doc2 = Jsoup.connect(myLink).get();
                Element news2 = doc2.getElementById("astroportret");
                opp = news2.select("li");
//                Elements opp = news2.select("li");
//                System.out.println(news.size());
                System.out.println(news2.text());
                znakZadiaka1 = opp.get(0).select("em").text() + " " + opp.get(0).select("a").get(0).text();
                znakZadiaka2 = opp.get(1).select("em").text() + " " + opp.get(1).select("a").get(0).text();
                znakZadiaka3 = opp.get(2).select("em").text() + " " + opp.get(2).select("a").get(0).text();

                text11 = opp.get(0).select("div").text();
                text22 = opp.get(1).select("div").text();
                text33 = opp.get(2).select("div").text();

                text11 = text11.replaceAll(">>>","");
                text22 = text22.replaceAll(">>>","");
                text33 = text33.replaceAll(">>>","");

                String l1 = String.valueOf(opp.get(0).select("a").get(1).attr("href"));
                String l2 = String.valueOf(opp.get(1).select("a").get(1).attr("href"));
                String l3 = String.valueOf(opp.get(2).select("a").get(1).attr("href"));

                doc2 = Jsoup.connect(l1).get();
                opp = doc2.select("p");

                for(int i=0; i<opp.size()-5; i++){

                    if(i!=0) descr1 = descr1+"\n"+ opp.get(i).text();
                    else descr1 = opp.get(i).text();

                }

                doc2 = Jsoup.connect(l2).get();
                opp = doc2.select("p");

                for(int i=0; i<opp.size()-5; i++){

                    if(i!=0) descr2 = descr2+"\n"+ opp.get(i).text();
                    else descr2 = opp.get(i).text();

                }

                doc2 = Jsoup.connect(l3).get();
                opp = doc2.select("p");

                for(int i=0; i<opp.size()-5; i++){

                    if(i!=0) descr3 = descr3+"\n"+ opp.get(i).text();
                    else descr3 = opp.get(i).text();

                }

                Log.e("descr1", descr1);
                Log.e("descr2", descr2);
                Log.e("descr3", descr3);
                //Log.e("lin1", String.valueOf(opp.get(0).select("a").get(1).attr("href")));

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            card1_title.setText(znakZadiaka1);
            card2_title.setText(znakZadiaka2);
            card3_title.setText(znakZadiaka3);

            card1_text.setText(text11);
            card2_text.setText(text22);
            card3_text.setText(text33);
            if(prefs.getDate().length()>2) {
                card.setVisibility(View.GONE);
            }
            progress.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

        }
    }
}
