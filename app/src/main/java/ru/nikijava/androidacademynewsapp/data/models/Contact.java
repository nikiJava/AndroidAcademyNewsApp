package ru.nikijava.androidacademynewsapp.data.models;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.nikijava.androidacademynewsapp.delegate_adapter.Item;

public class Contact implements Item {

    @DrawableRes private final int icon;
    @Nullable private final String url;
    @NonNull private final String name;

    public Contact(final int icon, @Nullable final String url, @NonNull final String name) {
        this.icon = icon;
        this.url = url;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public static Contact fromLink(@NonNull final Link link) {
        return new Contact(link.getIcon(), link.getUrl(), link.getName());
    }
}
