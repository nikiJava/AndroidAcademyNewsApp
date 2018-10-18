package ru.nikijava.androidacademynewsapp.data.models;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.nikijava.androidacademynewsapp.delegate_adapter.Item;

public class Achievement implements Item {

    private final @DrawableRes int icon;
    @NonNull private final String text;
    @Nullable private final String url;

    public Achievement(@DrawableRes final int icon,
                       @NonNull final String text,
                       @Nullable final String url
    ) {
        this.icon = icon;
        this.text = text;
        this.url = url;
    }

    public int getIcon() {
        return icon;
    }

    @NonNull
    public String getText() {
        return text;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public static Achievement fromLink(@NonNull final Link link) {
        return new Achievement(link.getIcon(), link.getName(), link.getUrl());
    }
}
