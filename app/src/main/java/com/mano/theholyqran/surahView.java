package com.mano.theholyqran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class surahView extends AppCompatActivity {
        ArrayList<Ayat> surah;
        ListView surahListView;
        DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_view);

        Intent intent = getIntent();
        int id = intent.getIntExtra("surahId", 1);

        db = new DatabaseHelper(surahView.this);
        surah = db.getSurahAyats(id);

        AyatAdapter adapter = new AyatAdapter(this, surah);
        surahListView = findViewById(R.id.surah);
        surahListView.setAdapter(adapter);


    }
}