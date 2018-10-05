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
    protected DividerViewHolder createViewHolder(View parent) {
        return new DividerViewHolder(parent);
    }

    @Override
    public boolean isForViewType(@NonNull List<?> items, int position) {
        return items.get(position) instanceof Divider;
    }
}
