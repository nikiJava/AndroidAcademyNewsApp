package ru.nikijava.androidacademynewsapp.presentation.viewholders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.Avatar;
import ru.nikijava.androidacademynewsapp.delegate_adapter.BaseViewHolder;

public class ItemAvatarViewHolder extends BaseViewHolder<Avatar> {

    private ImageView ivAvatar;

    public ItemAvatarViewHolder(@NonNull View itemView) {
        super(itemView);
        ivAvatar = itemView.findViewById(R.id.ivAvatar);
    }

    public void bind(@NonNull Avatar avatar) {
        ivAvatar.setBackgroundResource(avatar.getIco());
    }

}
