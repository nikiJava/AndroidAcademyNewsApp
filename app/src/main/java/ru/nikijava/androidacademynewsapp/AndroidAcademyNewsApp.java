package ru.nikijava.androidacademynewsapp;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

public class AndroidAcademyNewsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initVectors();
    }

    private void initVectors() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
