package ru.nikijava.androidacademynewsapp.data.models;

import ru.nikijava.androidacademynewsapp.R;

public enum Link {

    FACEBOOK("Facebook", R.drawable.ic_facebook,
            "https://www.facebook.com/profile.php?id=100009845167990"),
    TELEGRAM("Telegram", R.drawable.ic_telegram, "https://telegram.me/NikiJava"),
    WELLMARK("Wellmark", R.drawable.logo_work, "https://www.wellmark.ru/");

    private int ico;
    private String url;
    private String name;

    Link(String name, int ico, String url) {
        this.name = name;
        this.ico = ico;
        this.url = url;
    }

    public int getIco() {
        return ico;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
