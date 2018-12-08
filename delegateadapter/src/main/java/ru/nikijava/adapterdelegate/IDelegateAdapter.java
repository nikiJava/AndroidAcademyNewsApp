package ru.nikijava.adapterdelegate;

import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public interface IDelegateAdapter<VH extends RecyclerView.ViewHolder, T> {

    boolean isForViewType(@NonNull final List<?> items, final int position);

    void onBindViewHolder(
            @NonNull final VH holder,
            @NonNull final List<T> items,
            final int position
    );

    @NonNull
    RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType);

    void onRecycled(@NonNull final VH holder);

    @LayoutRes
    int getLayoutId();
}
