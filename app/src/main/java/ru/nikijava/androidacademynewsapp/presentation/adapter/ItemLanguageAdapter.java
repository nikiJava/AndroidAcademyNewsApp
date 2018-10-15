package ru.nikijava.androidacademynewsapp.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.Language;
import ru.nikijava.androidacademynewsapp.presentation.OnLanguageClickListener;
import ru.nikijava.androidacademynewsapp.presentation.viewholders.ItemLanguageViewHolder;

public class ItemLanguageAdapter extends RecyclerView.Adapter<ItemLanguageViewHolder> {

    @Nullable private final OnLanguageClickListener onClickListener;

    private List<Language> data = Collections.emptyList();

    public ItemLanguageAdapter(@Nullable final OnLanguageClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ItemLanguageViewHolder onCreateViewHolder(
            @NonNull final ViewGroup viewGroup,
            final int i
    ) {
        final View itemView = LayoutInflater.from(viewGroup.getContext())
                                            .inflate(getLayoutId(), viewGroup, false);
        return new ItemLanguageViewHolder(itemView, onClickListener);
    }

    @Override
    public void onBindViewHolder(
            @NonNull final ItemLanguageViewHolder itemLanguageViewHolder,
            final int i
    ) {
        itemLanguageViewHolder.bind(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(@NonNull List<Language> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    private int getLayoutId() {
        return R.layout.item_language;
    }

}
