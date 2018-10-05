package ru.nikijava.androidacademynewsapp.data.models;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.nikijava.androidacademynewsapp.delegate_adapter.Item;

public class Achievement implements Item {

    private final @DrawableRes int ico;
    @NonNull private final String text;
    @Nullable private String url;

    public Achievement(@DrawableRes int ico, @NonNull String text, @Nullable String url) {
        this.ico = ico;
        this.text = text;
        this.url = url;
    }

    public static Achievement fromLink(Link link) {
        return new Achievement(link.getIco(), link.getName(), link.getUrl());
    }

    public int getIco() {
        return ico;
    }

    @NonNull
    public String getText() {
        return text;
    }

    @Nullable
    public String getUrl() {
        return url;
    }
}
