package com.example.trabalho2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticController {

    private String TABLE = "diagnostics";

    private SQLiteDatabase sqlite;
    private Database database;

    public DiagnosticController(Context context){
        database = new Database(context);
    }

//    Create
    public Long create(Diagnostic diagnostic){

        ContentValues valores;
        long resultado;

        sqlite = database.getWritableDatabase();
        valores = new ContentValues();

        valores.put("user_id", diagnostic.getUser_id());
        valores.put("diagnostic", diagnostic.getDiagnostic());
        valores.put("latitude", diagnostic.getLatitude());
        valores.put("longitude", diagnostic.getLongitude());

        resultado = sqlite.insert(TABLE, null, valores);
        sqlite.close();

        return resultado;

    }

//    Read
    public List<Diagnostic> readAll(){

        sqlite = database.getWritableDatabase();

        Cursor cursor = sqlite.rawQuery("SELECT * FROM " + TABLE, null);

        cursor.moveToFirst();

        List<Diagnostic> diagnostics = new ArrayList<>();

        while (!cursor.isAfterLast()){

            Diagnostic diagnostic = new Diagnostic();

            diagnostic.setId(cursor.getLong(cursor.getColumnIndex("id")));
            diagnostic.setUser_id(cursor.getLong(cursor.getColumnIndex("user_id")));
            diagnostic.setDiagnostic(cursor.getInt(cursor.getColumnIndex("diagnostic")));
            diagnostic.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
            diagnostic.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));


            diagnostics.add(diagnostic);

            cursor.moveToNext();
        }

        sqlite.close();

        return diagnostics;
    }

//    Update
//    Delete
}
