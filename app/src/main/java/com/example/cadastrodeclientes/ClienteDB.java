package com.example.cadastrodeclientes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ClienteDB {
    private SQLiteDatabase conexao;
    public ClienteDB (SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserir (Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("login", cliente.getLogin());
        values.put("senha", cliente.getSenha());
        conexao.insertOrThrow("clientes", null, values);
    }

    public void remover (Cliente cliente) {
        String args[] = new String[1];
        args[0] = String.valueOf(cliente.getCodigo());
        conexao.delete("clientes", "codigo = ?", args);
    }

    public void alterar (Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("login", cliente.getLogin());
        values.put("senha", cliente.getSenha());
        String vet[] = new String[1];
        vet[0] = String.valueOf(cliente.getCodigo());
        conexao.update("clientes", values, "codigo = ?", vet);
    }

    public Cliente getCliente (int codigo) {
        Cliente cliente = new Cliente();
        String sql = "select * from clientes where codigo=" + codigo;
        Cursor resultado = conexao.rawQuery(sql, null);
        resultado.moveToFirst();
        if (resultado.getCount() == 0) return null;
        cliente.setNome(resultado.getString(
                resultado.getColumnIndexOrThrow("nome")));
        cliente.setLogin(resultado.getString(
                resultado.getColumnIndexOrThrow("login")));
        cliente.setSenha(resultado.getString(
                resultado.getColumnIndexOrThrow("senha")));
        return cliente;
    }

    public String getListaClientes () {
        String sql = "select nome from clientes";
        StringBuilder lista = new StringBuilder();
        int index;
        Cursor resultado = conexao.rawQuery(sql, null);
        resultado.moveToFirst();
        if (resultado.getCount() > 0) {
            do {
                if (! resultado.isFirst()) lista.append("\n");
                index = resultado.getColumnIndexOrThrow("nome");
                lista.append(resultado.getString(index));
            } while (resultado.moveToNext());
        }
        return lista.toString();
    }
}
