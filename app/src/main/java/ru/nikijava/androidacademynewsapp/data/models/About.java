package ru.nikijava.androidacademynewsapp.data.models;

import android.support.annotation.NonNull;

import ru.nikijava.androidacademynewsapp.delegate_adapter.Item;

public class About implements Item {

    @NonNull private String text;

    public About(@NonNull String text) {
        this.text = text;
    }

    @NonNull
    public String getText() {
        return text;
    }

}
