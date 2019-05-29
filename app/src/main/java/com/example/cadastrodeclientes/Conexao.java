package com.example.cadastrodeclientes;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Conexao {
    public  static SQLiteDatabase conexao = null;

    public void inserir (Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("login", cliente.getLogin());
        values.put("senha", cliente.getSenha());
        conexao.insertOrThrow("clientes", null, values);
    }
}
