package ru.nikijava.androidacademynewsapp.presentation.viewholders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.About;
import ru.nikijava.androidacademynewsapp.delegate_adapter.BaseViewHolder;

public class ItemAboutViewHolder extends BaseViewHolder<About> {

    private TextView tvAbout;

    public ItemAboutViewHolder(@NonNull View itemView) {
        super(itemView);
        tvAbout = itemView.findViewById(R.id.tvAbout);
    }

    public void bind(@NonNull About about) {
        tvAbout.setText(about.getText());
    }

}
