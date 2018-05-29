package com.example.mobiapp.newhoroscope.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobiapp.newhoroscope.R;
import com.example.mobiapp.newhoroscope.classes.CardMenu;
import com.example.mobiapp.newhoroscope.fragments.FragmentSelectAction;
import com.example.mobiapp.newhoroscope.retrofit.classes.News;

import java.util.List;

/**
 * Created by mobi app on 30.11.2017.
 */

public class TypeActionAdapter extends RecyclerView.Adapter<TypeActionAdapter.ViewHolder> {

    FragmentSelectAction fragmentSelectAction;
    List<CardMenu> list;

    public TypeActionAdapter(FragmentSelectAction fragmentSelectAction, List<CardMenu> list) {
        this.fragmentSelectAction = fragmentSelectAction;
        this.list = list;
    }

    @Override
    public TypeActionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_menu, parent, false);

        //ViewHolder vh = new ViewHolder(v);
        return new TypeActionAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TypeActionAdapter.ViewHolder holder, final int position) {

        CardMenu news = list.get(position);

        holder.tvTitle.setText(news.getTitle());
        holder.tvText.setText(news.getText());
        Glide.with(holder.cardView.getContext()).load(news.getImg()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentSelectAction.startAction(position);
            }
        });
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentSelectAction.startAction(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView tvTitle, tvText;
        ImageView imageView;
        CardView cardView;
        Button btn;

        public ViewHolder(View v) {
            super(v);

            tvTitle = (TextView) v.findViewById(R.id.card_menu_title);
            tvText = (TextView) v.findViewById(R.id.card_menu_text);
            imageView = (ImageView) v.findViewById(R.id.card_menu_img);
            cardView = (CardView) v.findViewById(R.id.card_menu_card);
            btn = (Button) v.findViewById(R.id.card_menu_btn);

        }
    }


}
