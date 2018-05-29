package com.example.mobiapp.newhoroscope.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mobiapp.newhoroscope.R;

/**
 * Created by mobi app on 06.12.2017.
 */

public class FragmentInfo extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_instruction, container, false);

        ImageView img1 = (ImageView) v.findViewById(R.id.i_img1);
        ImageView img2 = (ImageView) v.findViewById(R.id.i_img2);
        ImageView img3 = (ImageView) v.findViewById(R.id.i_img3);
        ImageView img4 = (ImageView) v.findViewById(R.id.i_img4);
        ImageView img5 = (ImageView) v.findViewById(R.id.i_img5);
        ImageView img6 = (ImageView) v.findViewById(R.id.i_img6);
        ImageView img7 = (ImageView) v.findViewById(R.id.i_img7);
        ImageView img8 = (ImageView) v.findViewById(R.id.i_img8);

        Glide.with(img1.getContext()).load(R.drawable.bg_inst).into(img1);
        Glide.with(img1.getContext()).load(R.drawable.select_zodiac).into(img2);
        Glide.with(img1.getContext()).load(R.drawable.type_horoscopess).into(img3);
        Glide.with(img1.getContext()).load(R.drawable.select_day).into(img4);
        Glide.with(img1.getContext()).load(R.drawable.birthday_select).into(img5);
        Glide.with(img1.getContext()).load(R.drawable.tayna_imeni).into(img6);
        Glide.with(img1.getContext()).load(R.drawable.sovm_znakov).into(img7);
        Glide.with(img1.getContext()).load(R.drawable.sovm_imen).into(img8);

        return v;
    }
}
