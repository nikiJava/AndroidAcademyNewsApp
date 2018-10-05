package ru.nikijava.androidacademynewsapp.delegate_adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public interface IDelegateAdapter<VH extends RecyclerView.ViewHolder, T> {

    boolean isForViewType(@NonNull List<?> items, int position);

    void onBindViewHolder(@NonNull VH holder,
            @NonNull List<T> items,
            int position);

    @NonNull
    RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    void onRecycled(VH holder);
}
