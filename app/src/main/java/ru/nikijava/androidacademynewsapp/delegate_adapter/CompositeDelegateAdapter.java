package ru.nikijava.androidacademynewsapp.delegate_adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CompositeDelegateAdapter<T>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int FIRST_VIEW_TYPE = 0;
    protected final @NonNull List<T> data = new ArrayList<>();
    private final SparseArray<IDelegateAdapter> typeToAdapterMap;

    CompositeDelegateAdapter(
            @NonNull SparseArray<IDelegateAdapter> typeToAdapterMap) {
        this.typeToAdapterMap = typeToAdapterMap;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
            int viewType) {
        return typeToAdapterMap.get(viewType)
                .onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final IDelegateAdapter delegateAdapter =
                typeToAdapterMap.get(getItemViewType(position));
        if (delegateAdapter != null) {
            //noinspection unchecked
            delegateAdapter.onBindViewHolder(holder, data, position);
        } else {
            throw new NullPointerException(
                    "can not find adapter for position " + position);
        }
    }

    @Override
    public final int getItemViewType(int position) {
        for (int i = FIRST_VIEW_TYPE; i < typeToAdapterMap.size(); i++) {
            final IDelegateAdapter delegate = typeToAdapterMap.valueAt(i);
            //noinspection unchecked
            if (delegate.isForViewType(data, position)) {
                return typeToAdapterMap.keyAt(i);
            }
        }
        throw new NullPointerException(
                "Can not get viewType for position " + position);
    }

    @Override
    public final int getItemCount() {
        return data.size();
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        //noinspection unchecked
        typeToAdapterMap.get(holder.getItemViewType())
                .onRecycled(holder);
    }

    public void swapData(@NonNull List<T> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public static class Builder<T> {

        private final SparseArray<IDelegateAdapter> typeToAdapterMap;
        private int count;

        public Builder() {
            typeToAdapterMap = new SparseArray<>();
        }

        public Builder<T> add(
                @NonNull IDelegateAdapter<?, ? extends T> delegateAdapter) {
            typeToAdapterMap.put(count++, delegateAdapter);
            return this;
        }

        public CompositeDelegateAdapter<T> build() {
            if (count == 0) {
                throw new IllegalArgumentException("Register at least one adapter");
            }
            return new CompositeDelegateAdapter<>(typeToAdapterMap);
        }
    }
}
