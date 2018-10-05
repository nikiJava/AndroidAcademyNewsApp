package ru.nikijava.androidacademynewsapp.presentation.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.Avatar;
import ru.nikijava.androidacademynewsapp.delegate_adapter.BaseDelegateAdapter;
import ru.nikijava.androidacademynewsapp.presentation.viewholders.ItemAvatarViewHolder;

public class ItemAvatarAdapter extends BaseDelegateAdapter<ItemAvatarViewHolder, Avatar> {

    @Override
    protected int getLayoutId() {
        return R.layout.item_avatar;
    }

    @NonNull
    @Override
    protected ItemAvatarViewHolder createViewHolder(View parent) {
        return new ItemAvatarViewHolder(parent);
    }

    @Override
    public boolean isForViewType(@NonNull List<?> items, int position) {
        return items.get(position) instanceof Avatar;
    }
}
