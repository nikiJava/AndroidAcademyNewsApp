package ru.nikijava.androidacademynewsapp.data.models;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.nikijava.androidacademynewsapp.delegate_adapter.Item;

public class Contact implements Item {

    @DrawableRes private int ico;
    @Nullable private String url;
    @NonNull private String name;

    public Contact(int ico, @Nullable String url, @NonNull String name) {
        this.ico = ico;
        this.url = url;
        this.name = name;
    }

    public static Contact fromLink(Link link) {
        return new Contact(link.getIco(), link.getUrl(), link.getName());
    }

    public int getIco() {
        return ico;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getUrl() {
        return url;
    }
}
