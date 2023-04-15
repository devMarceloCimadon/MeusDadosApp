package com.example.meusdadosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregar();

        Button okBT = findViewById(R.id.main_ok_button);
        okBT.setOnClickListener(v -> salvar(v));
    }

    public void carregar() {
        Pessoa p = lerSharedPreferences();
        setPessoaToView(p);
    }

    private Pessoa lerSharedPreferences() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String nome = sharedPref.getString("nome", "");
        String sobrenome = sharedPref.getString("sobrenome", "");
        int idade = sharedPref.getInt("idade", 0);
        float salario = sharedPref.getFloat("salario", 10000);
        String sexo = sharedPref.getString("sexo", "");
        return new Pessoa(nome, sobrenome, idade, salario, sexo);
    }

    public void salvar(View v) {
        Pessoa p = getPessoaFromView();
        salvarSharedPreferences(p);

        finish();
    }

    private void salvarSharedPreferences(Pessoa p) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nome", p.nome);
        editor.putString("sobrenome", p.sobrenome);
        editor.putInt("idade", p.idade);
        editor.putFloat("salario", p.salario);
        editor.putString("sexo", p.sexo);
        editor.commit();
    }

    public void cancelar(View v) {
        finish();
    }

    public Pessoa getPessoaFromView() {
        EditText nomeEdt = findViewById(R.id.main_nome_editText);
        EditText sobrenomeEdt = findViewById(R.id.main_sobrenome_editText);
        EditText idadeEdt = findViewById(R.id.main_idade_editText);
        EditText salarioEdt = findViewById(R.id.main_salario_editText);
        EditText sexoEdt = findViewById(R.id.main_sexo_editText);

        String nome = nomeEdt.getText().toString();
        String sobrenome = sobrenomeEdt.getText().toString();
        Integer idade = Integer.parseInt(idadeEdt.getText().toString());
        Float salario = Float.parseFloat(salarioEdt.getText().toString());
        String sexo = sexoEdt.getText().toString();

        return new Pessoa(nome, sobrenome, idade, salario, sexo);
    }

    public void setPessoaToView(Pessoa p) {
        EditText nomeEdt = findViewById(R.id.main_nome_editText);
        EditText sobrenomeEdt = findViewById(R.id.main_sobrenome_editText);
        EditText idadeEdt = findViewById(R.id.main_idade_editText);
        EditText salarioEdt = findViewById(R.id.main_salario_editText);
        EditText sexoEdt = findViewById(R.id.main_sexo_editText);

        nomeEdt.setText(p.nome);
        sobrenomeEdt.setText(p.sobrenome);
        idadeEdt.setText(Integer.toString(p.idade));
        salarioEdt.setText(Float.toString(p.salario));
        sexoEdt.setText(p.sexo);
    }
}