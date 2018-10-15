package ru.nikijava.androidacademynewsapp.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.models.Language;
import ru.nikijava.androidacademynewsapp.data.models.LanguageLocale;
import ru.nikijava.androidacademynewsapp.delegate_adapter.CompositeDelegateAdapter;
import ru.nikijava.androidacademynewsapp.delegate_adapter.Item;
import ru.nikijava.androidacademynewsapp.presentation.adapter.ItemLanguageAdapter;
import ru.nikijava.androidacademynewsapp.presentation.common.BaseActivity;
import ru.nikijava.androidacademynewsapp.utils.SupportedLanguages;

public class LanguagesActivity extends BaseActivity implements OnLanguageClickListener {

    private static final String TAG = LanguagesActivity.class.getSimpleName();
    public static int LANGUAGE_SELECT_RESULT = 1;
    public static String SELECTED_LANGUAGE = "selected_language";
    private RecyclerView rvLanguages;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(R.string.language);
        rvLanguages = findViewById(R.id.rvLanguages);
        List<Language> data = prepareData();
        initLanguageList(data);
    }
    @Override
    public void onLanguageClick(final Language language) {
        onLanguageSelected(language);
    }

    private void initLanguageList(final List<Language> data) {
        final ItemLanguageAdapter adapter = new ItemLanguageAdapter(this);
        adapter.updateData(data);
        rvLanguages.setAdapter(adapter);
    }

    private void onLanguageSelected(final Language language) {
        Intent intent = new Intent();
        intent.putExtra(SELECTED_LANGUAGE, language.getLanguageLocale());
        setResult(LANGUAGE_SELECT_RESULT, intent);
        finish();
    }

    private List<Language> prepareData() {
        List<Language> languages = new ArrayList<>(2);
        languages.add(new Language(getString(SupportedLanguages.RUSSIAN), LanguageLocale.RUSSIAN));
        languages.add(new Language(getString(SupportedLanguages.ENGLISH), LanguageLocale.ENGLISH));
        return languages;
    }
}
