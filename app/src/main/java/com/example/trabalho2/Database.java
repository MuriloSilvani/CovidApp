package com.example.trabalho2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private String tableUsers = "CREATE TABLE users (" +
            "id INTEGER PRIMARY KEY, " +
            "username TEXT NOT NULL UNIQUE, " +
            "name TEXT NOT NULL, " +
            "password TEXT NOT NULL, " +
            "birth DATE NOT NULL, " +
            "email TEXT NOT NULL, " +
            "avatar TEXT," +
            "admin BOOLEAN DEFAULT 0);";


    private String tableDiagnostic = "CREATE TABLE diagnostics (" +
            "id INTEGER PRIMARY KEY, " +
            "user_id Long NOT NULL, " +
            "diagnostic int NOT NULL, " +
            "latitude double NOT NULL, " +
            "longitude double NOT NULL, " +
            "FOREIGN KEY (user_id) REFERENCES users(id)" +
            ");";

    public Database(Context context){
        super(context, "trabalho2-1", null, 8);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableUsers);
        db.execSQL(tableDiagnostic);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users;");
        db.execSQL("DROP TABLE IF EXISTS diagnostics;");
        onCreate(db);
    }
}
