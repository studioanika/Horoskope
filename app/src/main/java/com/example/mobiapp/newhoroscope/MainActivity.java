package com.example.mobiapp.newhoroscope;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.example.mobiapp.newhoroscope.activity.DescriptionActivity;
import com.example.mobiapp.newhoroscope.adapter.NewsAdapter;
import com.example.mobiapp.newhoroscope.fragments.FragmentHoro;
import com.example.mobiapp.newhoroscope.fragments.FragmentInfo;
import com.example.mobiapp.newhoroscope.fragments.FragmentNews;
import com.example.mobiapp.newhoroscope.fragments.FragmentSelectAction;
import com.example.mobiapp.newhoroscope.retrofit.App;
import com.example.mobiapp.newhoroscope.retrofit.classes.News;
import com.example.mobiapp.newhoroscope.spacenavigationview.SpaceItem;
import com.example.mobiapp.newhoroscope.spacenavigationview.SpaceNavigationView;
import com.example.mobiapp.newhoroscope.spacenavigationview.SpaceOnClickListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private FragmentNews fragmentNews;

    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<News> list = new ArrayList<>();
    List<News> listFilter = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    SearchView searchView;
    RelativeLayout rel, rel_news;
    InterstitialAd interstitial;
    boolean isShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_home_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_all_zodiac));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_info_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_site));



        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onCentreButtonClick() {
                fragment = new FragmentSelectAction();
                transactionFragment();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                switch (itemIndex){
                    case 0:
                        rel_news.setVisibility(View.VISIBLE);
                        hideSearch();
                        Log.e("rel_news", "visible");
                        break;
                    case 1:
                        //rel_news.setVisibility(View.VISIBLE);
                       // visibleSearch();
                        fragment = new FragmentHoro();
                        transactionFragment();
                        Log.e("rel_news", "visible");
                        break;
                    case 2:
                        fragment = new FragmentInfo();
                        transactionFragment();
                        break;
                    case 3:
                        try {
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse("https://anika-cs.by/"));
                            startActivity(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
                spaceNavigationView.rotateButton();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

            }
        });

        initView();
        loadNews();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.text_share));
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void transactionFragment(){
        rel_news.setVisibility(View.GONE);
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    private void initView() {
        rel_news = (RelativeLayout) findViewById(R.id.relNews);
        searchView = (SearchView) findViewById(R.id.news_search_1);
        recyclerView = (RecyclerView) findViewById(R.id.news_recycler);
        progressBar = (ProgressBar) findViewById(R.id.news_progress);
        rel = (RelativeLayout) findViewById(R.id.news_rel);
        Log.e("fragment_news", "init_view");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recyclerView.setAdapter(new NewsAdapter(filter(s),MainActivity.this));
                return false;
            }
        });



    }

    @Override
    protected void onPostResume() {
        MobileAds.initialize(this,"ca-app-pub-8644281007634725~2462156094");

        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-8644281007634725/5415622496");
        AdRequest adRequesti = new AdRequest.Builder().build();
        interstitial.loadAd(adRequesti);
        isShow = false;
        super.onPostResume();
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
        recyclerView.setAdapter(new NewsAdapter(list,this));

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

    public void startDescription(String title, String href, String img){
        try {
            Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
            intent.putExtra("title",title);
            intent.putExtra("href",href);
            intent.putExtra("img",img);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //if(interstitial.isLoaded()) interstitial.show();
            moveTaskToBack(true); return true;
            //onDestroy();
        } return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        if(!isShow) {
            if(interstitial.isLoaded()) interstitial.show();
            isShow = true;
            return false;
        }else return super.moveTaskToBack(nonRoot);

    }
}
