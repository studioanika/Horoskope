package com.example.mobiapp.newhoroscope.spacenavigationview;

/**
 * Created by mobi app on 24.11.2017.
 */

public interface SpaceOnClickListener {

    void onCentreButtonClick();

    void onItemClick(int itemIndex, String itemName);

    void onItemReselected(int itemIndex, String itemName);
}