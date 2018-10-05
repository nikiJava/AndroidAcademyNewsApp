package ru.nikijava.androidacademynewsapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.nikijava.androidacademynewsapp.R;
import ru.nikijava.androidacademynewsapp.data.LanguageRepository;
import ru.nikijava.androidacademynewsapp.data.models.Language;
import ru.nikijava.androidacademynewsapp.data.models.LanguageLocale;
import ru.nikijava.androidacademynewsapp.delegate_adapter.CompositeDelegateAdapter;
import ru.nikijava.androidacademynewsapp.delegate_adapter.Item;
import ru.nikijava.androidacademynewsapp.presentation.adapter.ItemLanguageAdapter;
import ru.nikijava.androidacademynewsapp.utils.LocaleDelegate;

public class LanguagesActivity extends AppCompatActivity implements OnLanguageClickListener {

    private static final String TAG = LanguagesActivity.class.getSimpleName();
    public static int LANGUAGE_SELECT_RESULT = 1;
    public static String SELECTED_LANGUAGE = "selected_language";
    private RecyclerView rvLanguages;
    private LanguageRepository languageRepository;
    private LocaleDelegate localeDelegate = new LocaleDelegate();


    @Override
    protected void attachBaseContext(Context newBase) {
        languageRepository = new LanguageRepository(
                PreferenceManager.getDefaultSharedPreferences(newBase));
        super.attachBaseContext(localeDelegate.setLocale(languageRepository.getLocale().getName(), newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(R.string.language);
        rvLanguages = findViewById(R.id.rvLanguages);
        List<Item> data = prepareData();
        initLanguageList(data);
    }

    @Override
    public void onLanguageClick(Language language) {
        onLanguageSelected(language);
    }

    private void initLanguageList(List<Item> data) {
        CompositeDelegateAdapter<Item> adapter = new CompositeDelegateAdapter.Builder<Item>()
                .add(new ItemLanguageAdapter(this))
                .build();
        adapter.swapData(data);
        rvLanguages.setAdapter(adapter);
    }

    private void onLanguageSelected(Language language) {
        Intent intent = new Intent();
        intent.putExtra(SELECTED_LANGUAGE, language.getLanguageLocale());
        setResult(LANGUAGE_SELECT_RESULT, intent);
        finish();
    }

    private List<Item> prepareData() {
        List<Item> languages = new ArrayList<>(2);
        String[] supportedLanguages = getResources().getStringArray(R.array.supported_languages);
        languages.add(new Language(supportedLanguages[0], LanguageLocale.RUSSIAN));
        languages.add(new Language(supportedLanguages[1], LanguageLocale.ENGLISH));
        return languages;
    }

}
