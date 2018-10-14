package ru.nikijava.androidacademynewsapp.presentation.viewholders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.Achievement;
import ru.nikijava.androidacademynewsapp.data.models.Contact;
import ru.nikijava.androidacademynewsapp.delegate_adapter.BaseViewHolder;
import ru.nikijava.androidacademynewsapp.presentation.OnContactClickListener;

public class ItemContactViewHolder
        extends BaseViewHolder<Contact>
        implements View.OnClickListener {

    @Nullable private final OnContactClickListener onClickListener;
    @NonNull private final ImageView ivContact;

    @Nullable private Contact currentItem;


    public ItemContactViewHolder(
            @NonNull final View itemView,
            @Nullable final OnContactClickListener onClickListener
    ) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.onClickListener = onClickListener;
        ivContact = itemView.findViewById(R.id.ivContact);
    }

    public void bind(@NonNull final Contact contact) {
        currentItem = contact;
        ivContact.setBackgroundResource(contact.getIcon());
    }

    @Override
    public void onClick(final View v) {
        if (onClickListener != null) {
            onClickListener.onContactClick(currentItem);
        }
    }
}
