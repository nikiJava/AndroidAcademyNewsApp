package ru.nikijava.androidacademynewsapp.delegate_adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseDelegateAdapter
        <VH extends BaseViewHolder, T> implements IDelegateAdapter<VH, T> {


    @Override
    public final void onBindViewHolder(
            @NonNull final VH holder,
            @NonNull final List<T> items,
            final int position
    ) {
        holder.bind(items.get(position));
    }

    @NonNull
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(
            @NonNull final ViewGroup parent,
            final int viewType
    ) {
        final View inflatedView = LayoutInflater
                .from(parent.getContext())
                .inflate(getLayoutId(), parent, false);
        return createViewHolder(inflatedView);
    }

    @Override
    public void onRecycled(@NonNull final VH holder) {
    }

    @LayoutRes
    abstract protected int getLayoutId();

    @NonNull
    abstract protected VH createViewHolder(@NonNull final View itemView);
}
