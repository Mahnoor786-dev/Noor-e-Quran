package com.mano.theholyqran;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{

    public DatabaseHelper(@Nullable Context context) {
        super(context, "QuranDb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public ArrayList<Index> getSurahIndex()
    {
        ArrayList<Index> surahList = new ArrayList<>();
        String queryString = "SELECT SurahNameE, SurahNameU FROM tsurah";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst())
        {
             do {
                String nameEnglish  = cursor.getString(0);
                if(nameEnglish!=null) {
                    String[] arr = nameEnglish.split(" ");
                    nameEnglish = arr[0];   //only name of surah in english
                }
                String nameUrdu  = cursor.getString(1);
                Index currentSurah = new Index(nameUrdu, nameEnglish);
                surahList.add(currentSurah);
            }while (cursor.moveToNext());
        }
        else {
        }
        cursor.close();
        db.close();
        return surahList;
    }


}