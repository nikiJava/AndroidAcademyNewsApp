package ru.nikijava.androidacademynewsapp.data.models;

import android.support.annotation.NonNull;

public enum LanguageLocale {
    RUSSIAN("ru"), ENGLISH("en");

    @NonNull private final String name;

    LanguageLocale(@NonNull final String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
