package ru.nikijava.androidacademynewsapp.domain;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class BrowserInteractor {

    public void openUrlInBrowser(String url, Context context) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(browserIntent);
    }

}
