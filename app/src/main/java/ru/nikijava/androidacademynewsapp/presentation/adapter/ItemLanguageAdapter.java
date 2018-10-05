package ru.nikijava.androidacademynewsapp.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.Language;
import ru.nikijava.androidacademynewsapp.delegate_adapter.BaseDelegateAdapter;
import ru.nikijava.androidacademynewsapp.presentation.OnLanguageClickListener;
import ru.nikijava.androidacademynewsapp.presentation.viewholders.ItemLanguageViewHolder;

public class ItemLanguageAdapter extends BaseDelegateAdapter<ItemLanguageViewHolder, Language> {

    @Nullable private OnLanguageClickListener onClickListener;

    public ItemLanguageAdapter(@Nullable final OnLanguageClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_language;
    }

    @NonNull
    @Override
    protected ItemLanguageViewHolder createViewHolder(final View parent) {
        return new ItemLanguageViewHolder(parent, onClickListener);
    }

    @Override
    public boolean isForViewType(@NonNull List items, int position) {
        return items.get(position) instanceof Language;
    }
}
