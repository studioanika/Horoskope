package com.example.mobiapp.newhoroscope.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobiapp.newhoroscope.MainActivity;
import com.example.mobiapp.newhoroscope.R;
import com.example.mobiapp.newhoroscope.fragments.FragmentNews;
import com.example.mobiapp.newhoroscope.retrofit.classes.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mobi app on 28.11.2017.
 */

public class NewsAdapter extends  RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<News> list = new ArrayList<>();
    MainActivity activity;

    public NewsAdapter(List<News> list, MainActivity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        //ViewHolder vh = new ViewHolder(v);
        return new NewsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final News news = list.get(position);

        holder.tvTitle.setText(news.getTitle());
        holder.tvText.setText(news.getText());
        Glide.with(holder.cardView.getContext()).load(news.getImg()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startDescription(news.getTitle(), news.getUrl(), news.getImg());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //List<TypePA> list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView tvTitle, tvDesc, tvText;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(View v) {
            super(v);

            tvTitle = (TextView) v.findViewById(R.id.news_title);
            tvText = (TextView) v.findViewById(R.id.news_text);
            imageView = (ImageView) v.findViewById(R.id.news_image);
            cardView = (CardView) v.findViewById(R.id.news_card);

        }
    }

}
