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

    @Nullable private OnAchievementClickListener onClickListener;

    public ItemAchievementAdapter(@Nullable OnAchievementClickListener onAchievementClickListener) {
        this.onClickListener = onAchievementClickListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_achievement;
    }

    @NonNull
    @Override
    protected ItemAchievementViewHolder createViewHolder(View parent) {
        return new ItemAchievementViewHolder(parent, onClickListener);
    }

    @Override
    public boolean isForViewType(@NonNull List<?> items, int position) {
        return items.get(position) instanceof Achievement;
    }

}
