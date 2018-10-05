package ru.nikijava.androidacademynewsapp.presentation.viewholders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.Contact;
import ru.nikijava.androidacademynewsapp.delegate_adapter.BaseViewHolder;
import ru.nikijava.androidacademynewsapp.presentation.OnContactClickListener;

public class ItemContactViewHolder extends BaseViewHolder<Contact> {

    @Nullable private OnContactClickListener onClickListener;

    private ImageView ivContact;

    public ItemContactViewHolder(@NonNull View parent,
            @Nullable OnContactClickListener onClickListener) {
        super(parent);
        this.onClickListener = onClickListener;
        ivContact = parent.findViewById(R.id.ivContact);
    }

    public void bind(Contact contact) {
        ivContact.setBackgroundResource(contact.getIco());
        ivContact.setOnClickListener(v -> {
            if (onClickListener != null) onClickListener.onContactClick(contact);
        });
    }
}
