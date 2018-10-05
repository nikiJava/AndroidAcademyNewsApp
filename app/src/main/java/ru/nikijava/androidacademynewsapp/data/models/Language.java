package ru.nikijava.androidacademynewsapp.data.models;

import android.support.annotation.NonNull;

import ru.nikijava.androidacademynewsapp.delegate_adapter.Item;

public class Language implements Item {
    @NonNull private final String text;
    @NonNull private final LanguageLocale languageLocale;

    public Language(@NonNull String text, @NonNull LanguageLocale languageLocale) {
        this.text = text;
        this.languageLocale = languageLocale;
    }

    @NonNull
    public LanguageLocale getLanguageLocale() {
        return languageLocale;
    }

    @NonNull
    public String getText() {
        return text;
    }
}