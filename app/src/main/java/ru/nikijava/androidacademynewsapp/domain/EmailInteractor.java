package ru.nikijava.androidacademynewsapp.domain;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import ru.nikijava.androidacademynewsapp.R;

public class EmailInteractor {

    private static final String emailTo = "nikrestinpiece@gmail.com";

    public void startEmailClient(@Nullable final String message, @NonNull final Context context) {
        final Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + emailTo));
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello, guys!");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            Toast.makeText(context, R.string.message_no_email_client, Toast.LENGTH_LONG).show();
        }
    }
}
