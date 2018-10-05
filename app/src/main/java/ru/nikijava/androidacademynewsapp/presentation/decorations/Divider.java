package ru.nikijava.androidacademynewsapp.presentation.decorations;

import ru.nikijava.androidacademynewsapp.delegate_adapter.Item;

public final class Divider implements Item {

    private static Divider divider;

    public Divider() {
    }

    public static synchronized Divider getInstance() {
        if (divider == null) divider = new Divider();
        return divider;
    }
}
