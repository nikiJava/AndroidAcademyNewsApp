package ru.nikijava.androidacademynewsapp.data.models;

import android.support.annotation.NonNull;

public enum LanguageLocale {
    RUSSIAN("ru"), ENGLISH("en");

    @NonNull private String name;

    LanguageLocale(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }

}
