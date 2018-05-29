package com.example.mobiapp.newhoroscope.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobiapp.newhoroscope.R;
import com.example.mobiapp.newhoroscope.activity.HoroActivity;
import com.example.mobiapp.newhoroscope.classes.CardMenu;
import com.example.mobiapp.newhoroscope.classes.ItemHoro;

import java.util.List;

/**
 * Created by mobi app on 01.12.2017.
 */

public class ZnakAdapter extends RecyclerView.Adapter<ZnakAdapter.ViewHolder> {

    HoroActivity activity;
    List<ItemHoro> list;

    public ZnakAdapter(HoroActivity activity, List<ItemHoro> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public ZnakAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_horo, parent, false);

        //ViewHolder vh = new ViewHolder(v);
        return new ZnakAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ZnakAdapter.ViewHolder holder, final int position) {

        ItemHoro news = list.get(position);

        holder.tvText.setText(news.getName());
        Glide.with(holder.tvText.getContext()).load(news.getImg()).into(holder.imageView);
        holder.checkBox.setChecked(news.isCheck());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("type_adapter","b"+holder.checkBox.isChecked());
                if(!holder.checkBox.isChecked()){
                }else {
                    resetAllCheck(position);
                }
                notifyDataSetChanged();
            }
        });

    }

    private void resetAllCheck(int position) {

        for (ItemHoro item:list
                ) {
            item.setCheck(false);
        }
        list.get(position).setCheck(true);
        activity.setZnak(list.get(position).getName_eng());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView tvText;
        ImageView imageView;
        RadioButton checkBox;

        public ViewHolder(View v) {
            super(v);


            tvText = (TextView) v.findViewById(R.id.list_horo_text);
            imageView = (ImageView) v.findViewById(R.id.list_horo_img);
            checkBox = (RadioButton) v.findViewById(R.id.list_horo_check);

        }
    }
}
