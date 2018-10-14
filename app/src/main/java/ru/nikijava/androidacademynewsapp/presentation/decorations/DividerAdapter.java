package ru.nikijava.androidacademynewsapp.presentation.decorations;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.delegate_adapter.BaseDelegateAdapter;

public class DividerAdapter extends BaseDelegateAdapter<DividerViewHolder, Divider> {

    @Override
    protected int getLayoutId() {
        return R.layout.divider;
    }

    @NonNull
    @Override
    protected DividerViewHolder createViewHolder(@NonNull final View itemView) {
        return new DividerViewHolder(itemView);
    }

    @Override
    public boolean isForViewType(@NonNull final List<?> items, final int position) {
        return items.get(position) instanceof Divider;
    }
}
