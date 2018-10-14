package ru.nikijava.androidacademynewsapp.presentation.viewholders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.Language;
import ru.nikijava.androidacademynewsapp.delegate_adapter.BaseViewHolder;
import ru.nikijava.androidacademynewsapp.presentation.OnLanguageClickListener;

public class ItemLanguageViewHolder extends BaseViewHolder<Language> implements
        View.OnClickListener {

    @NonNull private final TextView tvLanguageName;
    @Nullable private final OnLanguageClickListener onClickListener;
    private Language currentLanguage;


    public ItemLanguageViewHolder(@NonNull final View itemView,
            @Nullable final OnLanguageClickListener onLanguageClickListener) {
        super(itemView);
        this.onClickListener = onLanguageClickListener;
        itemView.setOnClickListener(this);
        tvLanguageName = itemView.findViewById(R.id.tvLanguageName);
    }

    @Override
    protected void bind(@NonNull final Language item) {
        currentLanguage = item;
        tvLanguageName.setText(item.getText());
    }

    @Override
    public void onClick(final View v) {
        if (onClickListener != null) {
            onClickListener.onLanguageClick(currentLanguage);
        }
    }
}
