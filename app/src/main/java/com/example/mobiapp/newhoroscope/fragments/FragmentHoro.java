package com.example.mobiapp.newhoroscope.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobiapp.newhoroscope.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by mobi app on 07.12.2017.
 */

public class FragmentHoro extends Fragment {

    RelativeLayout progressBar;
    Document doc;
    String link = "http://1001goroskop.ru/";

    TextView tvTitle, tvText;


    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_horo, container, false);



        return v;
    }

    private void initView(){

        tvTitle = (TextView) v.findViewById(R.id.all_horo_title);
        tvText = (TextView) v.findViewById(R.id.all_horo_text);
        progressBar = (RelativeLayout) v.findViewById(R.id.horo_rel);

        new NewThread().execute();

    }

    public class NewThread extends AsyncTask<String, Void, String> {
        Elements news;
        Elements titles;
        String znak;
        String type;
        String day;
        String results;
        String title;


        @Override
        protected String doInBackground(String... arg) {

            String myLink = link;
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

    @Override
    public void onResume() {
        initView();
        super.onResume();
    }
}
