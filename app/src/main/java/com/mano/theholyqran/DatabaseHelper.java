package com.mano.theholyqran;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.database.sqlite.SQLiteDatabaseKt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DBNAME = "QuranDb.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        if (!ifDBExists(context)) {
            if (!copyDBFromAssets(context)) {
                throw new RuntimeException("Failed to Copy Database From Assets Folder");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    private boolean copyDBFromAssets(Context context) {
        Log.d("CPYDBINFO","Starting attemtpt to cop database from the assets file.");
        String DBPATH = "/data/data/com.mano.theholyqran/databases/QuranDb.db";
        InputStream  is;
        OutputStream os;
        int buffer_size = 8192;
        int length = buffer_size;
        long bytes_read = 0;
        long bytes_written = 0;
        byte[] buffer = new byte[length];

        try {

            is = context.getAssets().open(DBNAME);
        } catch (IOException e) {
            Log.e("CPYDB FAIL - NO ASSET","Failed to open the Asset file " + DBNAME);
            e.printStackTrace();
            return false;
        }

        try {
            os = new FileOutputStream(DBPATH);
        } catch (IOException e) {
            Log.e("CPYDB FAIL - OPENDB","Failed to open the Database File at " + DBPATH);
            e.printStackTrace();
            return false;
        }
        Log.d("CPYDBINFO","Initiating copy from asset file" + DBNAME + " to " + DBPATH);
        while (length >= buffer_size) {
            try {
                length = is.read(buffer,0,buffer_size);
            } catch (IOException e) {
                Log.e("CPYDB FAIL - RD ASSET",
                        "Failed while reading in data from the Asset. " +
                                String.valueOf(bytes_read) +
                                " bytes read successfully."
                );
                e.printStackTrace();
                return false;
            }
            bytes_read = bytes_read + length;
            try {
                os.write(buffer,0,buffer_size);
            } catch (IOException e) {
                Log.e("CPYDB FAIL - WR ASSET","failed while writing Database File " +
                        DBPATH +
                        ". " +
                        String.valueOf(bytes_written) +
                        " bytes written successfully.");
                e.printStackTrace();
                return false;

            }
            bytes_written = bytes_written + length;
        }
        Log.d("CPYDBINFO",
                "Read " + String.valueOf(bytes_read) + " bytes. " +
                        "Wrote " + String.valueOf(bytes_written) + " bytes."
        );
        try {
            os.flush();
            is.close();
            os.close();
        } catch (IOException e ) {
            Log.e("CPYDB FAIL - FINALISING","Failed Finalising Database Copy. " +
                    String.valueOf(bytes_read) +
                    " bytes read." +
                    String.valueOf(bytes_written) +
                    " bytes written."
            );
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private boolean ifDBExists(Context context) {
        String dbparent = context.getDatabasePath(DBNAME).getParent();
        File f = context.getDatabasePath(DBNAME);
        if (!f.exists()) {
            Log.d("NODB MKDIRS","Database file not found, making directories."); //<<<< remove before the App goes live.
            File d = new File(dbparent);
            d.mkdirs();
            //return false;
        }
        return f.exists();
    }


    public ArrayList<Index> getSurahIndex()
    {
        ArrayList<Index> surahList = new ArrayList<>();
        String queryString = "SELECT SurahID, SurahNameE, SurahNameU FROM tsurah";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst())
        {
             do {
                int surahId  = cursor.getInt(0);
                String nameEnglish  = cursor.getString(1);
                if(nameEnglish!=null) {
                    String[] arr = nameEnglish.split(" ");
                    nameEnglish = arr[0];   //only name of surah in english
                }
                String nameUrdu  = cursor.getString(2);
                Index currentSurah = new Index( surahId , nameUrdu, nameEnglish);
                surahList.add(currentSurah);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return surahList;
    }

    public ArrayList<Ayat> getSurahAyats(int collectionNumber, String collectiontype) //collection represents whether its a parah or a surah
    {
        ArrayList<Ayat> surahAyats = new ArrayList<Ayat>();
        String idType="";
        if(collectiontype.equals("surah"))
        {
            idType="SuraID";
        }
        else
        {
            idType= "ParaID";
        }
        String queryString = "SELECT ArabicText, MehmoodulHassan, DrMohsinKhan FROM tayah WHERE " + idType +  " = " + collectionNumber ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst())
        {
            do{
                String ayatArabic = cursor.getString(0);
                String translationU = cursor.getString(1);
                String translationE = cursor.getString(2);
                Ayat ayatSet = new Ayat(ayatArabic, translationE, translationU);
                surahAyats.add(ayatSet);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return surahAyats;
    }

    public int getSurahId(String surahName) //get surah Id by its name
    {
        Log.d("surahName Db: " , surahName);
        String queryString = "SELECT SurahID FROM tsurah WHERE SurahID =" + surahName;
        SQLiteDatabase db = this.getReadableDatabase();
        //try catch here
        int surahId = 1;
        try (Cursor cursor = db.rawQuery(queryString, null)) {
            if (cursor.moveToFirst()) {
                surahId = cursor.getInt(0);
            }
            cursor.close();
        }
        db.close();
        return surahId;
    }

}