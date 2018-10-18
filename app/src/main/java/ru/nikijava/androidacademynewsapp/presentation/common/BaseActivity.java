package ru.nikijava.androidacademynewsapp.presentation.common;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ru.nikijava.androidacademynewsapp.data.LanguageRepository;
import ru.nikijava.androidacademynewsapp.utils.LocaleDelegate;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    protected LanguageRepository languageRepository;
    private final LocaleDelegate localeDelegate = new LocaleDelegate();

    @Override
    protected void attachBaseContext(final Context newBase) {
        languageRepository = new LanguageRepository(
                PreferenceManager.getDefaultSharedPreferences(newBase));
        super.attachBaseContext(
                localeDelegate.setLocale(languageRepository.getLocale().getName().toLowerCase(), newBase));
    }
}
