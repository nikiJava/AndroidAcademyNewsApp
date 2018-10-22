package ru.nikijava.androidacademynewsapp.presentation;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.Link;
import ru.nikijava.androidacademynewsapp.domain.BrowserInteractor;
import ru.nikijava.androidacademynewsapp.domain.EmailInteractor;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final EmailInteractor emailInteractor = new EmailInteractor();
    private final BrowserInteractor browserInteractor = new BrowserInteractor();

    private ImageView ivAvatar;
    private ImageView ivContactTelegram;
    private ImageView ivContactFacebook;
    private TextView tvAbout;
    private EditText etMessage;
    private View ivSendMessage;
    private ImageView ivAchievementWellmarkIcon;
    private TextView tvAchievementWellmarkName;

    private RelativeLayout layoutContent;
    private LinearLayout layoutContacts;

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
        layoutContacts = findViewById(R.id.layoutContacts);
        ivAchievementWellmarkIcon = findViewById(R.id.ivAchievementWellmarkIcon);
        tvAchievementWellmarkName = findViewById(R.id.tvAchievementWellmarkName);
        addDisclaimer();
    }

    private void initListeners() {
        ivContactTelegram.setOnClickListener(v -> onContactClick(Link.TELEGRAM.getUrl()));
        ivContactFacebook.setOnClickListener(v -> onContactClick(Link.FACEBOOK.getUrl()));
        ivAchievementWellmarkIcon.setOnClickListener(v -> onAchievementClick(Link.WELLMARK.getUrl()));
        tvAchievementWellmarkName.setOnClickListener(v -> ivAchievementWellmarkIcon.callOnClick());
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
        if (orientation == ORIENTATION_PORTRAIT) recomputeAvatarSize();

        int avatarHeight = ivAvatar.getHeight();
        int avatarWidth = ivAvatar.getWidth();

        Glide.with(this)
             .load(R.drawable.me)
             .apply(RequestOptions.overrideOf(avatarWidth, avatarHeight))
             .into(ivAvatar);

    }
    private void recomputeAvatarSize() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float layoutPosition = metrics.heightPixels - getSupportActionBar().getHeight()
                - getResources().getDimension(R.dimen.status_bar_height);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivAvatar.getLayoutParams();
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
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.setMarginStart((int) getResources().getDimension(R.dimen.margin_start));
        tvDisclaimer.setPadding(
                tvDisclaimer.getPaddingLeft(),
                tvDisclaimer.getPaddingTop(),
                (int) getResources().getDimension(R.dimen.standard_margin),
                (int) getResources().getDimension(R.dimen.standard_margin)
        );
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.BELOW, R.id.layoutContacts);
        tvDisclaimer.setLayoutParams(params);

        RelativeLayout.LayoutParams contactParams = new RelativeLayout.LayoutParams(
                (RelativeLayout.LayoutParams) layoutContacts.getLayoutParams());
        contactParams.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        contactParams.setMargins(
                contactParams.leftMargin,
                contactParams.topMargin,
                contactParams.rightMargin,
                0
        );
        layoutContacts.setLayoutParams(contactParams);
    }
}
