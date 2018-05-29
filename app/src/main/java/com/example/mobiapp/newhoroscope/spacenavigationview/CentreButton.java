package com.example.mobiapp.newhoroscope.spacenavigationview;

/**
 * Created by mobi app on 24.11.2017.
 */

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.MotionEvent;

/**
 * Created by Chatikyan on 10.11.2016.
 */

public class CentreButton extends FloatingActionButton {

    public CentreButton(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean result = super.onTouchEvent(ev);
        if (!result) {
            if(ev.getAction() == MotionEvent.ACTION_UP) {
                cancelLongPress();
            }
            setPressed(false);
        }
        return result;
    }
}