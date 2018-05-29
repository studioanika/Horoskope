package com.example.mobiapp.newhoroscope.classes;

import android.content.Context;

import com.example.mobiapp.newhoroscope.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mobi app on 29.11.2017.
 */

public class GenerateCardMenu {

    Context context;

    public GenerateCardMenu(Context context) {
        this.context = context;
    }

    private CardMenu generateHoro(){
        CardMenu cardMenu = new CardMenu("Гороскоп", context.getResources().getDrawable(R.drawable.horoscope),"Гороскоп - астрономически рассчитанная схема расположения планет на небе в момент рождения человека, составляемая для астрологических предсказаний судьбы человека, какого-либо события и т.п...");

        return cardMenu;
    }

    private CardMenu generatePortret(){
        CardMenu cardMenu = new CardMenu("Звездный портрет", context.getResources().getDrawable(R.drawable.zvezdnuy_portret),"Звездный портрет - это астрологический портрет, описание личности человека, с точки зрения расположения в момент рождения планeт нашей солнечной системы...");

        return cardMenu;
    }

    private CardMenu generateName(){
        CardMenu cardMenu = new CardMenu("Тайна имени", context.getResources().getDrawable(R.drawable.tayna_name),"Мы даже не всегда знаем что означает это слово, но втайне гордимся, зная что подобное имя носил какой-нибудь весомый в человеческой истории персонаж....");

        return cardMenu;
    }

    private CardMenu generateSName(){
        CardMenu cardMenu = new CardMenu("Совместимость имен", context.getResources().getDrawable(R.drawable.sov_name),"В этом разделе вы узнаете не только о совместимости ваших характеров, но и получите подсказку, как избежать острых углов в совместной жизни...");

        return cardMenu;
    }
    private CardMenu generateSZ(){
        CardMenu cardMenu = new CardMenu("Совместимость знаков", context.getResources().getDrawable(R.drawable.sovm_znak),"Знание основных принципов, согласно которым складываются отношения в паре, поможет понять, что чувствует любимый человек, как себя вести, чтобы удовольствие от общения было взаимным...");

        return cardMenu;
    }

    public List<CardMenu> getAllList(){
        List<CardMenu> cardMenus = new ArrayList<>();

        cardMenus.add(generateHoro());
        cardMenus.add(generateName());
        cardMenus.add(generatePortret());
        cardMenus.add(generateSZ());
        cardMenus.add(generateSName());

        return cardMenus;
    }

}
