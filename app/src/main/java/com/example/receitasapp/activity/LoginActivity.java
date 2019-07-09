package com.example.receitasapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.receitasapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarComponentes();
    }

    void goToCadastroActivity(View view) {
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    private void inicializarComponentes(){
        editEmail = findViewById(R.id.edtLoginEmail);
        editSenha = findViewById(R.id.edtLoginSenha);
        entrar = findViewById(R.id.btnEntrar);
    }
}
