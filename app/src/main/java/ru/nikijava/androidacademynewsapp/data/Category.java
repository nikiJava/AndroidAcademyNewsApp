package ru.nikijava.androidacademynewsapp.data;

public enum Category {

    DARWIN_AWARDS(1, "Darwin Awards"),
    CRIMINAL(2, "Criminal"),
    ANIMALS(3, "Animals"),
    MUSIC(4, "Music");

    private final int id;
    private final String name;

    Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Category getById(int id) {
        Category type;
        switch (id) {
            case 1:
                type = DARWIN_AWARDS;
                break;
            case 2:
                type = CRIMINAL;
                break;
            case 3:
                type = ANIMALS;
                break;
            case 4:
                type = MUSIC;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
