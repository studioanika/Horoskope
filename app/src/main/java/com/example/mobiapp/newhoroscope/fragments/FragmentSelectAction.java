package com.example.mobiapp.newhoroscope.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobiapp.newhoroscope.R;
import com.example.mobiapp.newhoroscope.activity.HoroActivity;
import com.example.mobiapp.newhoroscope.activity.PortretActivity;
import com.example.mobiapp.newhoroscope.activity.SovmestimostNActivity;
import com.example.mobiapp.newhoroscope.activity.SovmestimostZActivity;
import com.example.mobiapp.newhoroscope.activity.TainaActivity;
import com.example.mobiapp.newhoroscope.adapter.TypeActionAdapter;
import com.example.mobiapp.newhoroscope.classes.GenerateCardMenu;

/**
 * Created by mobi app on 30.11.2017.
 */

public class FragmentSelectAction extends Fragment {

    View v;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    FragmentSelectAction fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_select_action, container, false);
        fragment = this;

        return v;
    }

    private void initView() {

        recyclerView = (RecyclerView) v.findViewById(R.id.select_action_recycler);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        GenerateCardMenu generateCardMenu = new GenerateCardMenu(fragment.getContext());
        recyclerView.setAdapter(new TypeActionAdapter(fragment, generateCardMenu.getAllList()));

    }

    public void startAction(int position){

        switch (position){
            case 0:
                startActivity(new Intent(getActivity(), HoroActivity.class));
                break;
            case 1:
                startActivity(new Intent(getActivity(), TainaActivity.class));
                break;
            case 2:
                startActivity(new Intent(getActivity(), PortretActivity.class));
                break;
            case 3:
                startActivity(new Intent(getActivity(), SovmestimostZActivity.class));
                break;
            case 4:
                startActivity(new Intent(getActivity(), SovmestimostNActivity.class));
                break;
        }

    }

    @Override
    public void onResume() {
        initView();
        super.onResume();
    }
}
