package com.example.mobiapp.newhoroscope.classes;

import android.content.Context;

import com.example.mobiapp.newhoroscope.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mobi app on 30.11.2017.
 */

public class GenerateHoro {

    Context context;

    public GenerateHoro(Context context) {
        this.context = context;
    }

    public List<ItemHoro> getAllZnack(){
        List<ItemHoro> array = new ArrayList<>();

        //array.add(new ItemHoro("Общий","", context.getResources().getDrawable(R.drawable.all_zodiak),true));
        array.add(new ItemHoro("Овен","aries", context.getResources().getDrawable(R.drawable.oven),false));
        array.add(new ItemHoro("Телец","taurus", context.getResources().getDrawable(R.drawable.teletc),false));
        array.add(new ItemHoro("Близнецы","gemini", context.getResources().getDrawable(R.drawable.bliznetcy),false));
        array.add(new ItemHoro("Рак","cancer", context.getResources().getDrawable(R.drawable.rak),false));
        array.add(new ItemHoro("Лев","leo", context.getResources().getDrawable(R.drawable.lev),false));
        array.add(new ItemHoro("Дева","virgo", context.getResources().getDrawable(R.drawable.deva),false));
        array.add(new ItemHoro("Весы","libra", context.getResources().getDrawable(R.drawable.vesy),false));
        array.add(new ItemHoro("Скорпион","scorpio", context.getResources().getDrawable(R.drawable.scorpio),false));
        array.add(new ItemHoro("Стрелец","sagittarius", context.getResources().getDrawable(R.drawable.streletc),false));
        array.add(new ItemHoro("Козерог","capricorn", context.getResources().getDrawable(R.drawable.kozerog),false));
        array.add(new ItemHoro("Водолей","aquarius", context.getResources().getDrawable(R.drawable.vodoley),false));
        array.add(new ItemHoro("Рыбы","pisces", context.getResources().getDrawable(R.drawable.fish),false));


        return array;
    }

    public List<ItemHoro> getAllType(){
        List<ItemHoro> array = new ArrayList<>();

        array.add(new ItemHoro("Общий","", context.getResources().getDrawable(R.drawable.all_type),true));
        array.add(new ItemHoro("Любовный","love", context.getResources().getDrawable(R.drawable.love),false));
        array.add(new ItemHoro("Бизнес","business", context.getResources().getDrawable(R.drawable.business),false));
        array.add(new ItemHoro("Здоровье","health", context.getResources().getDrawable(R.drawable.health),false));

        return array;
    }

    public List<ItemHoro> getAllTypeDay(){
        List<ItemHoro> array = new ArrayList<>();

        array.add(new ItemHoro("Сегодня","", context.getResources().getDrawable(R.drawable.today),true));
        array.add(new ItemHoro("Завтра","tomorrow", context.getResources().getDrawable(R.drawable.tomorow),false));
        array.add(new ItemHoro("Неделя","week", context.getResources().getDrawable(R.drawable.wek),false));

        return array;
    }

}
