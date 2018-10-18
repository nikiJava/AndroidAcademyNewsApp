package ru.nikijava.androidacademynewsapp.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.Locale;

public class LocaleDelegate {

    private static final String TAG = LocaleDelegate.class.getSimpleName();

    public Context setLocale(@NonNull final String language, @NonNull final Context context) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration config = new Configuration(resources.getConfiguration());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
            resources.updateConfiguration(config, resources.getDisplayMetrics());
            return context;
        }
        return context.createConfigurationContext(config);
    }
}
