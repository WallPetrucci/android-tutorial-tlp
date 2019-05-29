package com.example.cadastrodeclientes;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout layoutContentMain;
    private TextView listaClientes;
    private ClienteDB clienteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent it = getIntent();
        if (it.hasExtra("nome"))
            cadastrar(it);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibeCadastro(view);
            }
        });

        if (Conexao.conexao == null)
            estabeleceConexao();

        layoutContentMain = findViewById(R.id.layoutContentMain);
    }

    public void exibeCadastro(View view){
        Intent intent =  new Intent(this, CadastroActivity.class);
        startActivity(intent);

    }

    private void estabeleceConexao () {
        try {
            BancoDeDados bd = new BancoDeDados(this, "clientes.db",
                    null, 1);
            Conexao.conexao = bd.getWritableDatabase();
            Snackbar sb = Snackbar.make(layoutContentMain,
                    R.string.sucesso_conexao, Snackbar.LENGTH_LONG);
            sb.setAction("Ok", null);
            sb.show();
        }
        catch (Exception e) {
            Snackbar sb = Snackbar.make(layoutContentMain,
                    R.string.erro_conexao, Snackbar.LENGTH_LONG);
            sb.setAction("Ok", null);
            sb.show();
        }
    }

    public void adicionar (View view) {
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("nome", editTextNome.getText().toString());
        it.putExtra("login", editTextLogin.getText().toString());
        it.putExtra("senha", editTextSenha.getText().toString());
        startActivity(it);
    }

    public void cadastrar (Intent it) {
        Cliente cliente = new Cliente();
        cliente.setNome(it.getStringExtra("nome"));
        cliente.setLogin(it.getStringExtra("login"));
        cliente.setSenha(it.getStringExtra("senha"));
        clienteDB.inserir(cliente);
        String lista = clienteDB.getListaClientes();
        listaClientes.setText(lista);
        Snackbar sb = Snackbar.make(layoutContentMain,
                R.string.sucesso_cadastro, Snackbar.LENGTH_LONG);
        sb.setAction("Ok", null);
        sb.show();
    }

}
