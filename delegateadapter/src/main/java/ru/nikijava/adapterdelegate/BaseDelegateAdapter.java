package ru.nikijava.adapterdelegate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseDelegateAdapter<VH extends BaseViewHolder, T>
        implements IDelegateAdapter<VH, T> {

    @Override
    @SuppressWarnings("unchecked")
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
    public abstract int getLayoutId();

    @NonNull
    public abstract VH createViewHolder(@NonNull final View itemView);
}
