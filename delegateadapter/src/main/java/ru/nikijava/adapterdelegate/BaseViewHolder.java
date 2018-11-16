package ru.nikijava.adapterdelegate;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull final View itemView) {
        super(itemView);
    }

    protected abstract void bind(@NonNull final T item);
}
