package ru.nikijava.androidacademynewsapp.presentation.news.list;

import android.view.View;

import com.bumptech.glide.RequestManager;

import java.util.List;

import androidx.annotation.NonNull;
import ru.nikijava.adapterdelegate.BaseDelegateAdapter;
import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.News;

public class NewsAdapter extends BaseDelegateAdapter<NewsViewHolder, News> {

    private static final String TAG = NewsAdapter.class.getSimpleName();
    @NonNull private final OnNewsClickListener onNewsClickListener;
    @NonNull private final RequestManager requestManager;

    public NewsAdapter(
            @NonNull OnNewsClickListener onNewsClickListener,
            @NonNull RequestManager requestManager
    ) {
        this.onNewsClickListener = onNewsClickListener;
        this.requestManager = requestManager;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_news;
    }

    @NonNull
    @Override
    public NewsViewHolder createViewHolder(@NonNull View itemView) {
        return new NewsViewHolder(itemView, onNewsClickListener, requestManager);
    }

    @Override
    public boolean isForViewType(@NonNull List<?> items, int position) {
        return items.get(position) instanceof News;
    }
}
