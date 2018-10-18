package ru.nikijava.androidacademynewsapp.presentation.viewholders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.Achievement;
import ru.nikijava.androidacademynewsapp.delegate_adapter.BaseViewHolder;
import ru.nikijava.androidacademynewsapp.presentation.OnAchievementClickListener;

public class ItemAchievementViewHolder
        extends BaseViewHolder<Achievement>
        implements View.OnClickListener {

    @NonNull private final TextView tvAchievementText;
    @NonNull private final ImageView ivAchievementIco;
    @Nullable private final OnAchievementClickListener onAchievementClickListener;

    @Nullable private Achievement currentItem;

    public ItemAchievementViewHolder(
            @NonNull final View itemView,
            @Nullable final OnAchievementClickListener onAchievementClickListener
    ) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.onAchievementClickListener = onAchievementClickListener;
        tvAchievementText = itemView.findViewById(R.id.tvAchievementText);
        ivAchievementIco = itemView.findViewById(R.id.ivAchievementIco);
    }

    public void bind(@NonNull final Achievement achievement) {
        currentItem = achievement;
        tvAchievementText.setText(achievement.getText());
        ivAchievementIco.setBackgroundResource(achievement.getIcon());
    }

    @Override
    public void onClick(final View v) {
        if (onAchievementClickListener != null) {
            onAchievementClickListener.onAchievementClick(currentItem);
        }
    }
}
