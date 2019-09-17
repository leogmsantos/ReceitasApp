package activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.receitasapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import helper.ConfiguracaoFirebase;
import model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button entrar;
    private String email, senha;
    private ProgressBar loginProgress;

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
        loginProgress = findViewById(R.id.loginProgress);
    }

    private void recuperarDados(){
        email = editEmail.getText().toString();
        senha = editSenha.getText().toString();
    }

    public void logar(View view){
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginProgress.setVisibility(View.VISIBLE);
                recuperarDados();
                if (!email.isEmpty() && !senha.isEmpty()){
                    Usuario usuario = new Usuario();
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    ConfiguracaoFirebase.getAutenticacao().signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                loginProgress.setVisibility(View.GONE);
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }else{
                                loginProgress.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    //TODO: desenvolver a ação de login
                }
            }
        });
    }
}
