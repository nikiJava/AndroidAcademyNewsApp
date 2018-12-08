package ru.nikijava.androidacademynewsapp.presentation.news.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import androidx.annotation.NonNull;
import ru.nikijava.adapterdelegate.BaseViewHolder;
import ru.nikijava.androidacademynewsapp.DateFormatter;
import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.News;
import ru.nikijava.androidacademynewsapp.presentation.news.NewsAdapterItem;

public class BaseNewsViewHolder<T extends NewsAdapterItem> extends BaseViewHolder<T> implements View.OnClickListener {

    private static final String TAG = NewsViewHolder.class.getSimpleName();
    @NonNull private final OnNewsClickListener onNewsClickListener;
    @NonNull private final RequestManager requestManager;

    private final TextView tvCategory;
    private final TextView tvTitle;
    private final TextView tvBody;
    private final ImageView ivImage;
    private final TextView tvDate;

    private NewsAdapterItem item;

    public BaseNewsViewHolder(
            @NonNull View itemView,
            @NonNull OnNewsClickListener onNewsClickListener,
            @NonNull RequestManager requestManager
    ) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.onNewsClickListener = onNewsClickListener;
        this.requestManager = requestManager;
        tvCategory = itemView.findViewById(R.id.tvCategory);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvBody = itemView.findViewById(R.id.tvBody);
        ivImage = itemView.findViewById(R.id.ivImage);
        tvDate = itemView.findViewById(R.id.tvDate);
    }
    @Override
    protected void bind(@NonNull NewsAdapterItem item) {
        this.item = item;
        News news = item.getNews();
        tvCategory.setText(news.getCategory().getName());
        tvTitle.setText(news.getTitle());
        tvBody.setText(news.getFullText());
        tvDate.setText(DateFormatter.formatDateTime(itemView.getContext(), news.getPublishDate()));
        requestManager.load(news.getImageUrl()).into(ivImage);
    }

    @Override
    public void onClick(View v) {
        onNewsClickListener.onNewsClick(item.getNews());
    }
}
