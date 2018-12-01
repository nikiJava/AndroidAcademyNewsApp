package ru.nikijava.adapterdelegate;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.RecyclerView;

public class CompositeDelegateAdapter<T>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = CompositeDelegateAdapter.class.getSimpleName();
    @NonNull protected final List<T> data = new ArrayList<>();
    @NonNull private final Map<Integer,IDelegateAdapter> typeToAdapterMap;

    private CompositeDelegateAdapter(@NonNull final Map<Integer,IDelegateAdapter> typeToAdapterMap) {
        this.typeToAdapterMap = typeToAdapterMap;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(
            @NonNull final ViewGroup parent,
            final int viewType
    ) {
        return typeToAdapterMap.get(viewType).onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(
            @NonNull final RecyclerView.ViewHolder holder,
            final int position
    ) {
        final IDelegateAdapter delegateAdapter = typeToAdapterMap.get(getItemViewType(position));
        if (delegateAdapter != null) {
            //noinspection unchecked
            delegateAdapter.onBindViewHolder(holder, data, position);
        } else {
            throw new NullPointerException("can not find adapter for position " + position);
        }
    }

    @Override
    public final int getItemViewType(final int position) {
        for (int key : typeToAdapterMap.keySet()) {
            final IDelegateAdapter delegate = typeToAdapterMap.get(key);
            //noinspection unchecked
            if (delegate.isForViewType(data, position)) {
                return key;
            }
        }
        throw new NullPointerException("Can not get viewType for position " + position);
    }

    @Override
    public final int getItemCount() {
        return data.size();
    }

    @Override
    public void onViewRecycled(@NonNull final RecyclerView.ViewHolder holder) {
        //noinspection unchecked
        typeToAdapterMap.get(holder.getItemViewType()).onRecycled(holder);
    }

    public void swapData(@NonNull final List<T> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public static class Builder<T> {

        private final Map<Integer,IDelegateAdapter> typeToAdapterMap;
        private int count;

        public Builder() {
            typeToAdapterMap = new ArrayMap<>();
        }

        public Builder<T> add(@NonNull final IDelegateAdapter<?, ? extends T> delegateAdapter) {
            count++;
            typeToAdapterMap.put(delegateAdapter.getLayoutId(), delegateAdapter);
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
