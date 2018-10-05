package ru.nikijava.androidacademynewsapp.delegate_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View parent) {
        super(parent);
    }

    protected abstract void bind(T item);

}
