package com.example.trabalho2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    private SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

    private String TABLE = "users";

    private SQLiteDatabase sqlite;
    private Database database;

    public UserController(Context context){
        database = new Database(context);
    }

//    Create
    public Long create(User user){

        ContentValues valores;
        long resultado;

        sqlite = database.getWritableDatabase();
        valores = new ContentValues();

        valores.put("username", user.getUsername());
        valores.put("name", user.getName());
        valores.put("birth", formater.format(user.getBirth()));
        valores.put("email", user.getEmail());
        valores.put("password", user.getPassword());
        valores.put("admin", user.isAdmin());

        resultado = sqlite.insert(TABLE, null, valores);
        sqlite.close();


        return resultado;

    }

//    Read
    public List<User> readAll(){

        sqlite = database.getWritableDatabase();

        Cursor cursor = sqlite.rawQuery("SELECT * FROM " + TABLE, null);

        cursor.moveToFirst();

        List<User> users = new ArrayList<>();

        while (!cursor.isAfterLast()){

            User user = new User();

            user.setId(cursor.getLong(cursor.getColumnIndex("id")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));

            SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

            try{

                user.setBirth(format.parse(cursor.getString(cursor.getColumnIndex("birth"))));
            }catch (Exception e){

            }

            user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            user.setAvatar(cursor.getString(cursor.getColumnIndex("avatar")));

            users.add(user);

            cursor.moveToNext();
        }

        sqlite.close();

        return users;
    }

//    Update
//    Delete

    public void clear(){

        sqlite = database.getWritableDatabase();
        sqlite.delete(TABLE, "1 = 1", null);
    }

    // Login
    public Long login(String username, String password) {

        sqlite = database.getWritableDatabase();

        String[] args = {username, password};


        Cursor cursor = sqlite.rawQuery("SELECT * FROM " + TABLE + " WHERE username = ? AND password = ?", args);

        cursor.moveToFirst();

        if(cursor.getCount() == 0){

            return new Long(-1);
        }


        return cursor.getLong(cursor.getColumnIndex("id"));
    }
}
