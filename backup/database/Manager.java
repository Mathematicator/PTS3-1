package com.pts3.sport.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class Manager {

    private MySQLite instance;
    public static SQLiteDatabase db;

    public Manager(Context context) {
        instance = MySQLite.getInstance(context);
        db = instance.getReadableDatabase();
    }
}
