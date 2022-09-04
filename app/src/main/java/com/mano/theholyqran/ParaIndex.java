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
        paraNames = new ArrayList<Index>();
        para = new ParahNames();
        for (int i = 0; i < 30; i++)
        {
            Index paraa = new Index(i+1, para.UrduParahName[i], para.englishParahName[i]);
            paraNames.add(paraa);
        }
        ViewAdapter adapter = new ViewAdapter(this, paraNames);
        parahListView = findViewById(R.id.paraList);
        parahListView.setAdapter(adapter);
    }
}