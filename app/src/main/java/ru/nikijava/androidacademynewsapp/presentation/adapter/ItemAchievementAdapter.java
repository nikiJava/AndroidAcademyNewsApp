package ru.nikijava.androidacademynewsapp.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.Achievement;
import ru.nikijava.androidacademynewsapp.delegate_adapter.BaseDelegateAdapter;
import ru.nikijava.androidacademynewsapp.presentation.OnAchievementClickListener;
import ru.nikijava.androidacademynewsapp.presentation.viewholders.ItemAchievementViewHolder;

public class ItemAchievementAdapter extends
        BaseDelegateAdapter<ItemAchievementViewHolder, Achievement> {

    @Nullable private final OnAchievementClickListener onClickListener;

    public ItemAchievementAdapter(
            @Nullable final OnAchievementClickListener onAchievementClickListener
    ) {
        this.onClickListener = onAchievementClickListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_achievement;
    }

    @NonNull
    @Override
    protected ItemAchievementViewHolder createViewHolder(@NonNull final View itemView) {
        return new ItemAchievementViewHolder(itemView, onClickListener);
    }

    @Override
    public boolean isForViewType(@NonNull final List<?> items, final int position) {
        return items.get(position) instanceof Achievement;
    }

}
