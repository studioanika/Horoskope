package com.example.mobiapp.newhoroscope.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.example.mobiapp.newhoroscope.R;
import com.example.mobiapp.newhoroscope.adapter.NewsAdapter;
import com.example.mobiapp.newhoroscope.retrofit.App;
import com.example.mobiapp.newhoroscope.retrofit.classes.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mobi app on 28.11.2017.
 */

public class FragmentNews extends Fragment {

    View v;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<News> list = new ArrayList<>();
    List<News> listFilter = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    SearchView searchView;
    FragmentNews fragmentNews;
    RelativeLayout rel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_news, container, false);
        fragmentNews = this;
        return v;
    }

    private void initView() {
        searchView = (SearchView) v.findViewById(R.id.news_search_1);
        recyclerView = (RecyclerView) v.findViewById(R.id.news_recycler);
        progressBar = (ProgressBar) v.findViewById(R.id.news_progress);
        rel = (RelativeLayout) v.findViewById(R.id.news_rel);
        Log.e("fragment_news", "init_view");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //recyclerView.setAdapter(new NewsAdapter(filter(s),fragmentNews));
                return false;
            }
        });

    }

    public void visibleSearch(){
        searchView.setVisibility(View.VISIBLE);
        rel.setVisibility(View.VISIBLE);
    }
    public void hideSearch(){
        searchView.setVisibility(View.GONE);
        rel.setVisibility(View.GONE);
    }

    private void loadNews(){
        Log.e("fragment_news", "load_news_start");
        if(list!=null && list.size()!=0)
        progressBar.setVisibility(View.VISIBLE);
        App.getApi().getArticle().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                progressBar.setVisibility(View.GONE);

                list = response.body();
                listFilter = response.body();
                setRecycler();
                Log.e("fragment_news", "load_news_succesfull");

            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                showErrorSnack();
                Log.e("fragment_news", "load_news_error"+t.toString());
            }
        });
    }

    private void showErrorSnack() {

        Snackbar snackbar = Snackbar.make(recyclerView, "Отсутствует подключение к интернету...", Snackbar.LENGTH_LONG)
                .setAction("Повторить", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadNews();

                    }
                });
        snackbar.setDuration(8000); // 8 секунд
        snackbar.show();
    }

    private void setRecycler(){
        Log.e("fragment_news", "load_setRecycler");
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setAdapter(new NewsAdapter(list,this));

    }

    private List<News> filter(String s){
        ArrayList<News> arrayList = new ArrayList<>();

        for (News n:list
             ) {

            if(n.getText().contains(s)||n.getTitle().contains(s)){
                arrayList.add(n);
            }


        }

        return arrayList;
    }

    public void startDescription(int position){
        //if(list.size()!=0) startActivity(new Intent(getActivity(),));
    }

    @Override
    public void onResume() {
        initView();
        loadNews();
        super.onResume();
    }
}
