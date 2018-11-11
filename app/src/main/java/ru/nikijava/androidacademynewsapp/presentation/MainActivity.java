package ru.nikijava.androidacademynewsapp.presentation;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.Link;
import ru.nikijava.androidacademynewsapp.domain.BrowserInteractor;
import ru.nikijava.androidacademynewsapp.domain.EmailInteractor;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final EmailInteractor emailInteractor = new EmailInteractor();
    private final BrowserInteractor browserInteractor = new BrowserInteractor();

    private Toolbar toolbar;

    private ImageView ivAvatar;
    private ImageView ivContactTelegram;
    private ImageView ivContactFacebook;
    private TextView tvAbout;
    private EditText etMessage;
    private View ivSendMessage;
    private TextView itemAchievementWellmark;
    private NestedScrollView svContent;

    private LinearLayout layoutContent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(
                getString(R.string.my_first_name) + " " + getString(R.string.my_second_name));

        setAvatar();
        initListeners();
    }

    private void initView() {
        etMessage = findViewById(R.id.etMessage);
        ivAvatar = findViewById(R.id.ivAvatar);
        ivSendMessage = findViewById(R.id.ivSendMessage);
        ivContactTelegram = findViewById(R.id.ivContactTelegram);
        ivContactFacebook = findViewById(R.id.ivContactFacebook);
        tvAbout = findViewById(R.id.tvAbout);
        layoutContent = findViewById(R.id.layoutContent);
        itemAchievementWellmark = findViewById(R.id.tvAchievementWellmarkName);
        svContent = findViewById(R.id.svContent);
        addDisclaimer();
    }

    private void initListeners() {
        ivContactTelegram.setOnClickListener(v -> onContactClick(Link.TELEGRAM.getUrl()));
        ivContactFacebook.setOnClickListener(v -> onContactClick(Link.FACEBOOK.getUrl()));
        itemAchievementWellmark.setOnClickListener(v -> onAchievementClick(Link.WELLMARK.getUrl()));
        ivSendMessage.setOnClickListener(v -> emailInteractor.startEmailClient(getMessage(), this));

        etMessage.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                ivSendMessage.performClick();
                return true;
            }
            return false;
        });
    }

    private void onContactClick(@NonNull final String contactUrl) {
        openUrl(contactUrl);
    }

    private void onAchievementClick(@NonNull final String achievementUrl) {
        openUrl(achievementUrl);
    }

    private void setAvatar() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == ORIENTATION_PORTRAIT) svContent.post(this::recomputeAvatarSize);

        Glide.with(this)
             .load(R.drawable.me)
             .into(ivAvatar);
    }

    private void recomputeAvatarSize() {
        float layoutPosition = svContent.getHeight();
        LinearLayout.LayoutParams params =
                (LinearLayout.LayoutParams) ivAvatar.getLayoutParams();
        params.height = (int) (layoutPosition * 0.4);
    }

    private void openUrl(@NonNull final String url) {
        browserInteractor.openUrlInBrowser(url, this);
    }

    private String getMessage() {
        return etMessage.getText().toString();
    }

    private void addDisclaimer() {
        String disclaimer = getString(R.string.disclaimer, getString(R.string.full_name));
        TextView tvDisclaimer = new TextView(this);
        tvDisclaimer.setText(disclaimer);
        this.layoutContent.addView(tvDisclaimer);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMarginStart((int) getResources().getDimension(R.dimen.margin_start));
        params.gravity = Gravity.END;
        tvDisclaimer.setPadding(
                tvDisclaimer.getPaddingLeft(),
                tvDisclaimer.getPaddingTop(),
                (int) getResources().getDimension(R.dimen.standard_margin),
                (int) getResources().getDimension(R.dimen.standard_margin)
        );
        TextViewCompat.setTextAppearance(tvDisclaimer, R.style.TextAppearance_Disclaimer);
        tvDisclaimer.setLayoutParams(params);
    }
}
