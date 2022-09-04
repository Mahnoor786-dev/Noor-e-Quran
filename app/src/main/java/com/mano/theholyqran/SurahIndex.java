package com.mano.theholyqran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SurahIndex extends AppCompatActivity {
    DatabaseHelper db;
    ListView surahListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_index);
        db = new DatabaseHelper(SurahIndex.this);
        ArrayList<Index> SurahIndexes = db.getSurahIndex();
        ViewAdapter adapter = new ViewAdapter(this, SurahIndexes);
        surahListView = findViewById(R.id.surahList);
        surahListView.setAdapter(adapter);
    }
}