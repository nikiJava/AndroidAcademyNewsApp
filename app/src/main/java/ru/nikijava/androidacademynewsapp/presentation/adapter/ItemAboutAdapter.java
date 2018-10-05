package ru.nikijava.androidacademynewsapp.presentation.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.About;
import ru.nikijava.androidacademynewsapp.delegate_adapter.BaseDelegateAdapter;
import ru.nikijava.androidacademynewsapp.presentation.viewholders.ItemAboutViewHolder;

public class ItemAboutAdapter extends BaseDelegateAdapter<ItemAboutViewHolder, About> {

    @Override
    protected int getLayoutId() {
        return R.layout.item_description;
    }

    @NonNull
    @Override
    protected ItemAboutViewHolder createViewHolder(View parent) {
        return new ItemAboutViewHolder(parent);
    }

    @Override
    public boolean isForViewType(@NonNull List<?> items, int position) {
        return items.get(position) instanceof About;
    }
}
