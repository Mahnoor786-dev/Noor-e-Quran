package com.mano.theholyqran;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ParaView extends AppCompatActivity {
        ArrayList<Ayat> surah;
        ListView surahListView;
        DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_view);

        Intent intent = getIntent();
        int id = intent.getIntExtra("surahId", 1);

        db = new DatabaseHelper(ParaView.this);
        surah = db.getSurahAyats(id, "surah"); //get ayats by surah id

        AyatAdapter adapter = new AyatAdapter(this, surah);
        surahListView = findViewById(R.id.surah);
        surahListView.setAdapter(adapter);


    }
}