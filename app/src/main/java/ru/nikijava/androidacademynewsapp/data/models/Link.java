package ru.nikijava.androidacademynewsapp.data.models;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import ru.nikijava.androidacademynewsapp.R;

public enum Link {

    FACEBOOK("Facebook", R.drawable.ic_facebook,
            "https://www.facebook.com/profile.php?id=100009845167990"),
    TELEGRAM("Telegram", R.drawable.ic_telegram, "https://telegram.me/NikiJava"),
    WELLMARK("Wellmark", R.drawable.logo_work_cropped, "https://www.wellmark.ru/");

    private final @DrawableRes int icon;

    @NonNull
    private final String url;

    @NonNull
    private final String name;

    Link(@NonNull final String name, final @DrawableRes int icon, @NonNull final String url) {
        this.name = name;
        this.icon = icon;
        this.url = url;
    }

    public int getIcon() {
        return icon;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getUrl() {
        return url;
    }
}
