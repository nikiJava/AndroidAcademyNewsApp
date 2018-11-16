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
import ru.nikijava.androidacademynewsapp.data.Category;
import ru.nikijava.androidacademynewsapp.data.News;
import ru.nikijava.androidacademynewsapp.data.NewsRepository;
import ru.nikijava.androidacademynewsapp.data.NewsRepositoryImpl;
import ru.nikijava.androidacademynewsapp.presentation.news.NewsAdapterItem;
import ru.nikijava.androidacademynewsapp.presentation.news.details.NewsDetailsActivity;

public class NewsListActivity extends AppCompatActivity implements OnNewsClickListener {

    private static final String TAG = NewsListActivity.class.getSimpleName();
    private final NewsRepository newsRepository = new NewsRepositoryImpl();
    private RecyclerView rvNewsList;

    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        getSupportActionBar().setTitle(getString(R.string.top_news));
        rvNewsList = findViewById(R.id.rvNewsList);
        initList();

        List<News> data = newsRepository.getNews();
        List<Item> mappedNews = mapNewsForPresentation(data);
        getAdapter().swapData(mappedNews);
    }

    @Override
    public void onNewsClick(News news) {
        NewsDetailsActivity.start(this, news);
    }

    @SuppressWarnings("unchecked call")
    private void initList() {
        CompositeDelegateAdapter adapter = new CompositeDelegateAdapter.Builder<Item>()
                .add(new NewsAdapter(this, Glide.with(this), R.layout.item_news))
                .add(new NewsAdapter(this, Glide.with(this), R.layout.item_news_big))
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

    private List<Item> mapNewsForPresentation(List<News> news) {
        List<Item> mappedNews = new ArrayList<>(news.size());
        for (News newsItem : news) {
            if (newsItem.getCategory() == Category.CRIMINAL) {
                mappedNews.add(
                        new NewsAdapterItem(newsItem, R.layout.item_news_big));
            } else {
                mappedNews.add(new NewsAdapterItem(newsItem, R.layout.item_news));
            }
        }
        return mappedNews;
    }

    @SuppressWarnings("unchecked")
    private CompositeDelegateAdapter<Item> getAdapter() {
        return (CompositeDelegateAdapter<Item>) rvNewsList.getAdapter();
    }
}
