package ru.nikijava.androidacademynewsapp.data;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import ru.nikijava.androidacademynewsapp.data.models.LanguageLocale;

public class LanguageRepository {

    private static final String LOCALE = "locale";
    @NonNull private final SharedPreferences sharedPreferences;

    public LanguageRepository(@NonNull final SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void changeLocale(LanguageLocale languageLocale) {
        sharedPreferences.edit().putString(LOCALE, languageLocale.toString()).apply();
    }

    public LanguageLocale getLocale() {
        String value = sharedPreferences.getString(LOCALE, LanguageLocale.RUSSIAN.toString());
        return LanguageLocale.valueOf(value);
    }

}
