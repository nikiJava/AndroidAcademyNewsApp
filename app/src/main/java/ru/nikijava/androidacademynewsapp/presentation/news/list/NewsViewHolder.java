package ru.nikijava.androidacademynewsapp.presentation.news.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.annotation.NonNull;
import ru.nikijava.adapterdelegate.BaseViewHolder;
import ru.nikijava.androidacademynewsapp.DateFormatter;
import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.News;

public class NewsViewHolder extends BaseViewHolder<News> implements View.OnClickListener {

    private static final String TAG = NewsViewHolder.class.getSimpleName();
    @NonNull private final OnNewsClickListener onNewsClickListener;
    @NonNull private final RequestManager requestManager;
    private final DateFormatter dateFormatter = new DateFormatter();

    private final TextView tvCategory;
    private final TextView tvTitle;
    private final TextView tvBody;
    private final ImageView ivImage;
    private final TextView tvDate;

    private News item;

    public NewsViewHolder(
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
    protected void bind(@NonNull News item) {
        this.item = item;
        tvCategory.setText(item.getCategory().getName());
        tvTitle.setText(item.getTitle());
        tvBody.setText(item.getFullText());
        SimpleDateFormat format = new SimpleDateFormat("MMM d, hh:mm", Locale.getDefault());
        tvDate.setText(dateFormatter.formatDateTime(itemView.getContext(), item.getPublishDate()));
        requestManager.load(item.getImageUrl()).into(ivImage);
    }

    @Override
    public void onClick(View v) {
        onNewsClickListener.onNewsClick(item);
    }
}
