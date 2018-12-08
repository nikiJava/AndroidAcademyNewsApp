package ru.nikijava.androidacademynewsapp.presentation.news.list;

import android.view.View;

import com.bumptech.glide.RequestManager;

import androidx.annotation.NonNull;
import ru.nikijava.androidacademynewsapp.presentation.news.NewsAdapterItem;

public class BigNewsViewHolder extends BaseNewsViewHolder<NewsAdapterItem> {

    public BigNewsViewHolder(
            @NonNull View itemView,
            @NonNull OnNewsClickListener onNewsClickListener,
            @NonNull RequestManager requestManager
    ) {
        super(itemView, onNewsClickListener, requestManager);
    }
}
