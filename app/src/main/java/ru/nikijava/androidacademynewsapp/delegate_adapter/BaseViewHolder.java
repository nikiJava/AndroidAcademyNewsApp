package ru.nikijava.androidacademynewsapp.delegate_adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull final View itemView) {
        super(itemView);
    }

    protected abstract void bind(@NonNull final T item);
}
