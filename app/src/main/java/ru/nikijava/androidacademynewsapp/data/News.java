package ru.nikijava.androidacademynewsapp.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import androidx.annotation.NonNull;

public class News implements Serializable {

    @NonNull private final String title;
    @NonNull private final String imageUrl;
    @NonNull private final Category category;
    @NonNull private final Date publishDate;
    @NonNull private final String previewText;
    @NonNull private final String fullText;

    public News(
            @NonNull String title,
            @NonNull String imageUrl,
            @NonNull Category category,
            @NonNull Date publishDate,
            @NonNull String previewText,
            @NonNull String fullText
    ) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.category = category;
        this.publishDate = publishDate;
        this.previewText = previewText;
        this.fullText = fullText;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", category=" + category +
                ", publishDate=" + publishDate +
                ", previewText='" + previewText + '\'' +
                ", fullText='" + fullText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        final News news = (News) o;
        return Objects.equals(title, news.title) &&
                Objects.equals(imageUrl, news.imageUrl) &&
                category == news.category &&
                Objects.equals(publishDate, news.publishDate) &&
                Objects.equals(previewText, news.previewText) &&
                Objects.equals(fullText, news.fullText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, imageUrl, category, publishDate, previewText, fullText);
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    @NonNull
    public Category getCategory() {
        return category;
    }

    @NonNull
    public Date getPublishDate() {
        return publishDate;
    }

    @NonNull
    public String getPreviewText() {
        return previewText;
    }

    @NonNull
    public String getFullText() {
        return fullText;
    }
}
