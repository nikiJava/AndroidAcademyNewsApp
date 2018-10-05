package ru.nikijava.androidacademynewsapp.data.models;

import android.support.annotation.DrawableRes;

import ru.nikijava.androidacademynewsapp.delegate_adapter.Item;

public class Avatar implements Item {

    private final @DrawableRes int ico;

    public Avatar(@DrawableRes int ico) {
        this.ico = ico;
    }

    public int getIco() {
        return ico;
    }
}
