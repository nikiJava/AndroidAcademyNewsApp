package ru.nikijava.androidacademynewsapp.presentation.news;

import androidx.annotation.LayoutRes;
import ru.nikijava.adapterdelegate.Item;
import ru.nikijava.androidacademynewsapp.data.News;

public class NewsAdapterItem implements Item {

    private final News news;
    private final @LayoutRes int layoutId;

    public NewsAdapterItem(News news, @LayoutRes int layoutId) {
        this.news = news;
        this.layoutId = layoutId;
    }

    @Override
    public int getLayoutId() {
        return layoutId;
    }

    public News getNews() {
        return news;
    }
}
