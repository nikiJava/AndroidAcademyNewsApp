package ru.nikijava.androidacademynewsapp.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.Locale;

public class LocaleDelegate {

    public Context setLocale(@NonNull String language, @NonNull Context context) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration(context.getResources().getConfiguration());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        return context.createConfigurationContext(config);
    }

}
