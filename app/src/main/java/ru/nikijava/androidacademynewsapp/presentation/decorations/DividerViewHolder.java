package ru.nikijava.androidacademynewsapp.presentation.decorations;

import android.support.annotation.NonNull;
import android.view.View;

import ru.nikijava.androidacademynewsapp.delegate_adapter.BaseViewHolder;

public class DividerViewHolder extends BaseViewHolder<Divider> {

    DividerViewHolder(@NonNull final View itemView) {
        super(itemView);
    }

    @Override
    protected void bind(@NonNull final Divider item) {
        // there is no need to bind item to view, because Divider has no any properties yet.
    }
}
