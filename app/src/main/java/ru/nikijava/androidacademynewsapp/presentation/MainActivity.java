package ru.nikijava.androidacademynewsapp.presentation;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.About;
import ru.nikijava.androidacademynewsapp.data.models.Achievement;
import ru.nikijava.androidacademynewsapp.data.models.Contact;
import ru.nikijava.androidacademynewsapp.data.models.LanguageLocale;
import ru.nikijava.androidacademynewsapp.data.models.Link;
import ru.nikijava.androidacademynewsapp.delegate_adapter.CompositeDelegateAdapter;
import ru.nikijava.androidacademynewsapp.delegate_adapter.Item;
import ru.nikijava.androidacademynewsapp.domain.BrowserInteractor;
import ru.nikijava.androidacademynewsapp.domain.EmailInteractor;
import ru.nikijava.androidacademynewsapp.presentation.adapter.ItemAboutAdapter;
import ru.nikijava.androidacademynewsapp.presentation.adapter.ItemAchievementAdapter;
import ru.nikijava.androidacademynewsapp.presentation.adapter.ItemContactAdapter;
import ru.nikijava.androidacademynewsapp.presentation.common.BaseActivity;
import ru.nikijava.androidacademynewsapp.presentation.decorations.Divider;
import ru.nikijava.androidacademynewsapp.presentation.decorations.DividerAdapter;

public class MainActivity
        extends BaseActivity
        implements OnContactClickListener, OnAchievementClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int LANGUAGE_REQUEST_CODE = 2;

    private static final int layout = R.layout.activity_main;

    private final EmailInteractor emailInteractor = new EmailInteractor();
    private final BrowserInteractor browserInteractor = new BrowserInteractor();

    private ImageView ivAvatar;
    private RecyclerView rvContent;
    private RecyclerView rvContacts;
    private EditText etMessage;
    private View ivSendMessage;

    private RelativeLayout layoutContent;
    private RelativeLayout layoutContacts;

    private CompositeDelegateAdapter<Item> contentAdapter;
    private CompositeDelegateAdapter<Item> contactAdapter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
        initView();
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(
                getString(R.string.my_first_name) + " " + getString(R.string.my_second_name));
        initContent();
        fillAboutInfo(getData());
        fillContactInfo(getContacts());
        Glide.with(this)
                .load(R.drawable.me)
                .apply(RequestOptions.overrideOf(500, 500))
                .into(ivAvatar);

        ivSendMessage.setOnClickListener(v -> emailInteractor.startEmailClient(getMessage(), this));

        etMessage.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                ivSendMessage.performClick();
                return true;
            }
            return false;
        });

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == ORIENTATION_PORTRAIT) recomputeAvatarSize();
    }

    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            @Nullable final Intent data
    ) {
        if (requestCode == LANGUAGE_REQUEST_CODE) {
            if (data != null) {
                LanguageLocale language = (LanguageLocale) data.getSerializableExtra(
                        LanguagesActivity.SELECTED_LANGUAGE);
                languageRepository.changeLocale(language);
                recreate();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        etMessage.clearFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemLanguage:
                Intent intent = new Intent(this, LanguagesActivity.class);
                startActivityForResult(intent, LANGUAGE_REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onContactClick(@NonNull final Contact contact) {
        if (contact.getUrl() != null) {
            openUrl(contact.getUrl());
        }
    }

    @Override
    public void onAchievementClick(@NonNull final Achievement achievement) {
        if (achievement.getUrl() != null) {
            openUrl(achievement.getUrl());
        }
    }

    private void initView() {
        etMessage = findViewById(R.id.etMessage);
        ivAvatar = findViewById(R.id.ivAvatar);
        rvContent = findViewById(R.id.rvContent);
        rvContacts = findViewById(R.id.rvContacts);
        ivSendMessage = findViewById(R.id.ivSendMessage);
        layoutContent = findViewById(R.id.layoutContent);
        layoutContacts = findViewById(R.id.layoutContacts);
        addDisclaimer();
    }

    private void recomputeAvatarSize() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float layoutPosition = metrics.heightPixels - getSupportActionBar().getHeight()
                - getResources().getDimension(R.dimen.status_bar_height);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ivAvatar.getLayoutParams();
        params.height = (int) (layoutPosition * 0.4);
    }

    private void openUrl(@NonNull final String url) {
        browserInteractor.openUrlInBrowser(url, this);
    }

    private void initContent() {
        contentAdapter = new CompositeDelegateAdapter.Builder<Item>()
                .add(new ItemAboutAdapter())
                .add(new ItemAchievementAdapter(this))
                .add(new DividerAdapter())
                .build();
        rvContent.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvContent.setAdapter(contentAdapter);
        rvContent.setNestedScrollingEnabled(true);

        contactAdapter = new CompositeDelegateAdapter.Builder<Item>()
                .add(new ItemContactAdapter(this))
                .build();
        rvContacts.setAdapter(contactAdapter);
    }

    private List<Item> getData() {
        List<Achievement> achievements = new ArrayList<>();
        achievements.add(Achievement.fromLink(Link.WELLMARK));

        List<Item> data = new ArrayList<>(achievements);
        data.add(new Divider());
        data.add(new About(getString(R.string.about_me)));
        data.add(new Divider());
        return data;
    }

    private List<Item> getContacts() {
        List<Item> contacts = new ArrayList<>();
        contacts.add(Contact.fromLink(Link.FACEBOOK));
        contacts.add(Contact.fromLink(Link.TELEGRAM));
        return contacts;
    }

    private String getMessage() {
        return etMessage.getText().toString();
    }

    private void fillAboutInfo(@NonNull List<Item> data) {
        contentAdapter.swapData(data);
    }

    private void fillContactInfo(@NonNull List<Item> data) {
        contactAdapter.swapData(data);
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
