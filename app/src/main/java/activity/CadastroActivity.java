package activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.receitasapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import helper.ConfiguracaoFirebase;
import model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText editCadastroNome, editCadastroEmail, editCadastroSenha;
    private Button btnCadastrar;
    private FirebaseAuth autenticacao;
    private String nome, email, senha;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializarComponentes();
        criarUsuario();
        cadastrarUsuario();
    }

    private void inicializarComponentes(){
        editCadastroNome = findViewById(R.id.edtCadastroNome);
        editCadastroEmail = findViewById(R.id.edtCadastroEmail);
        editCadastroSenha = findViewById(R.id.edtCadastroSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
    }

    private void recuperarDadosCadastrais(){
        nome = editCadastroNome.getText().toString();
        email = editCadastroEmail.getText().toString();
        senha = editCadastroSenha.getText().toString();
    }

    private void criarUsuario(){
        usuario = new Usuario();

        recuperarDadosCadastrais();

        if (!email.isEmpty() && !senha.isEmpty()){
            usuario.setEmail(email);
            usuario.setSenha(senha);
        }
    }

    private void configuracaoFirebase(){
        autenticacao = ConfiguracaoFirebase.getAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(CadastroActivity.this, getString(R.string.cadastro_sucesso), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }else {
                            Toast.makeText(CadastroActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void cadastrarUsuario(){
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criarUsuario();
                configuracaoFirebase();
            }
        });
    }


}
