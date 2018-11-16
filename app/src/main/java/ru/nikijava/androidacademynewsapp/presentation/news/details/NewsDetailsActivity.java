package ru.nikijava.androidacademynewsapp.presentation.news.details;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import ru.nikijava.androidacademynewsapp.DateFormatter;
import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.News;

public class NewsDetailsActivity extends AppCompatActivity {

    private static String NEWS_KEY = "news_key";
    private final DateFormatter dateFormatter = new DateFormatter();

    private News news;

    private ImageView ivImage;
    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvDetails;
    private NestedScrollView svContent;

    public static void start(Context context, News news) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra(NEWS_KEY, news);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        news = (News) getIntent().getSerializableExtra(NEWS_KEY);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(news.getCategory().getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        showNews();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initViews() {
        ivImage = findViewById(R.id.ivImage);
        tvTitle = findViewById(R.id.tvTitle);
        tvDate = findViewById(R.id.tvDate);
        tvDetails = findViewById(R.id.tvDetails);
        svContent = findViewById(R.id.svContent);
    }

    private void showNews() {
        tvDate.setText(dateFormatter.formatDateTime(this, news.getPublishDate()));
        tvDetails.setText(news.getFullText());
        tvTitle.setText(news.getTitle());
        setImage();
    }

    private void setImage() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == ORIENTATION_PORTRAIT) ivImage.post(this::recomputeAvatarSize);
        Glide.with(this).load(news.getImageUrl()).into(ivImage);
    }

    private void recomputeAvatarSize() {
        float layoutPosition = svContent.getHeight();
        CollapsingToolbarLayout.LayoutParams params =
                (CollapsingToolbarLayout.LayoutParams) ivImage.getLayoutParams();
        params.height = (int) (layoutPosition * 0.4);
    }
}
