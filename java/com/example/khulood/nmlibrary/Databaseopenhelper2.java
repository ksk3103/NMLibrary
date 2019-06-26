package com.example.khulood.nmlibrary;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Databaseopenhelper2 extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "Books.db";
    private static final int DATABASE_VERSION = 1;

    public Databaseopenhelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}