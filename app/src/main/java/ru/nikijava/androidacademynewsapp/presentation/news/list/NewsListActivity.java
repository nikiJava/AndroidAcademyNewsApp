package ru.nikijava.androidacademynewsapp.presentation.news.list;

import android.content.res.Configuration;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.nikijava.adapterdelegate.CompositeDelegateAdapter;
import ru.nikijava.adapterdelegate.Item;
import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.NewsRepository;
import ru.nikijava.androidacademynewsapp.data.NewsRepositoryImpl;
import ru.nikijava.androidacademynewsapp.presentation.news.details.NewsDetailsActivity;

public class NewsListActivity extends AppCompatActivity {

    private static final String TAG = NewsListActivity.class.getSimpleName();
    private final NewsRepository newsRepository = new NewsRepositoryImpl();
    private RecyclerView rvNewsList;

    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        getSupportActionBar().setTitle(getString(R.string.top_news));
        rvNewsList = findViewById(R.id.rvNewsList);
        initList();
        populateList();
    }

    private void initList() {
        CompositeDelegateAdapter adapter = new CompositeDelegateAdapter.Builder<Item>()
                .add(new NewsAdapter(item -> NewsDetailsActivity.start(this, item),
                        Glide.with(this)))
                .build();

        rvNewsList.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager;
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_LANDSCAPE: {
                layoutManager = new GridLayoutManager(this, 2);
                break;
            }
            default: {
                layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            }
        }

        rvNewsList.setLayoutManager(layoutManager);

        rvNewsList.addItemDecoration(new NewsItemDecorationImpl(
                getResources().getDimensionPixelSize(R.dimen.spacing_micro)));
    }

    private void populateList() {
        List<Item> data = new ArrayList<>(newsRepository.getNews());
        getAdapter().swapData(data);
    }

    @SuppressWarnings("unchecked")
    private CompositeDelegateAdapter<Item> getAdapter() {
        return (CompositeDelegateAdapter<Item>) rvNewsList.getAdapter();
    }
}
