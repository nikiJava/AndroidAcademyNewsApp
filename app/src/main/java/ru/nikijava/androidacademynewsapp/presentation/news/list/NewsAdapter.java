package ru.nikijava.androidacademynewsapp.presentation.news.list;

import android.view.View;

import com.bumptech.glide.RequestManager;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import ru.nikijava.adapterdelegate.BaseDelegateAdapter;
import ru.nikijava.adapterdelegate.Item;
import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.presentation.news.NewsAdapterItem;

public class NewsAdapter extends
        BaseDelegateAdapter<BaseNewsViewHolder, NewsAdapterItem> {

    private static final String TAG = NewsAdapter.class.getSimpleName();
    @NonNull private final RequestManager requestManager;
    @NonNull private final OnNewsClickListener onNewsClickListener;
    private final @LayoutRes int layoutId;

    public NewsAdapter(
            @NonNull OnNewsClickListener onNewsClickListener,
            @NonNull RequestManager requestManager,
            @LayoutRes int layoutId
    ) {
        this.onNewsClickListener = onNewsClickListener;
        this.requestManager = requestManager;
        this.layoutId = layoutId;
    }

    @Override
    public int getLayoutId() {
        return layoutId;
    }

    @Override
    public boolean isForViewType(@NonNull List<?> items, int position) {
        Item item = (Item) items.get(position);
        return item instanceof NewsAdapterItem && item.getLayoutId() == layoutId;
    }

    @NonNull
    @Override
    public BaseNewsViewHolder createViewHolder(@NonNull View itemView) {
        switch (layoutId) {
            case R.layout.item_news_big:
                return new BigNewsViewHolder(itemView, onNewsClickListener, requestManager);
            default:
                return new NewsViewHolder(itemView, onNewsClickListener, requestManager);
        }
    }
}
