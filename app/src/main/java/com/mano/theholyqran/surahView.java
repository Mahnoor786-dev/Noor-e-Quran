package com.mano.theholyqran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class surahView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_view);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);


    }
}