package ru.nikijava.androidacademynewsapp.data;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Category implements Serializable {
    private final int id;
    @NonNull private final String name;

    public Category(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
