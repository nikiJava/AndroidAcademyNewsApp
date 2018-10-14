package ru.nikijava.androidacademynewsapp.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.Contact;
import ru.nikijava.androidacademynewsapp.delegate_adapter.BaseDelegateAdapter;
import ru.nikijava.androidacademynewsapp.presentation.OnContactClickListener;
import ru.nikijava.androidacademynewsapp.presentation.viewholders.ItemContactViewHolder;

public class ItemContactAdapter extends BaseDelegateAdapter<ItemContactViewHolder, Contact> {

    @Nullable private final OnContactClickListener onClickListener;

    public ItemContactAdapter(@Nullable final OnContactClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_contact;
    }

    @NonNull
    @Override
    protected ItemContactViewHolder createViewHolder(@NonNull final View itemView) {
        return new ItemContactViewHolder(itemView, onClickListener);
    }

    @Override
    public boolean isForViewType(@NonNull final List<?> items, final int position) {
        return items.get(position) instanceof Contact;
    }
}
