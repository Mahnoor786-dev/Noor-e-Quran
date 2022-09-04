package com.mano.theholyqran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParaIndex extends AppCompatActivity {
    ListView parahListView;
    ParahNames para;
    ArrayList<Index> paraNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_para_index);

        String[]  paraEnglishNames = para.getEnglishParahName(); //get para English names
        String[] paraUrduNames = para.getUrduParahName();
        for (int i = 1; i < 31; i++)
        {
            paraNames.add(new Index(paraUrduNames[i], paraEnglishNames[i]));
        }
        ViewAdapter adapter = new ViewAdapter(this, paraNames);
        parahListView = findViewById(R.id.paraList);
        parahListView.setAdapter(adapter);
    }
}