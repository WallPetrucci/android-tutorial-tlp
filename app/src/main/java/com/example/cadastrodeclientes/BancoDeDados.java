package com.example.cadastrodeclientes;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper {
    public BancoDeDados(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql  = "create table if not exists clientes (" +
                      " codigo integer primary key autoincrement not null," +
                      " nome varchar(200) not null," +
                      " login varchar(200) not null," +
                      " senha varchar(100) not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
