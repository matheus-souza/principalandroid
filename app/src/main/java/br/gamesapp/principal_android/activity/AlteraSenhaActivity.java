package br.gamesapp.principal_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.gamesapp.principal_android.MyApplication;
import br.gamesapp.principal_android.R;
import br.gamesapp.principal_android.activity.cadastro.DadosGeraisActivity;
import br.gamesapp.principal_android.interfaces.AlunoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlteraSenhaActivity extends AppCompatActivity {

    private final String BASE_URL = "http://192.168.2.122:8080/web-service/webresources/GamesApp/";

    private EditText edtSenhaVelha;
    private EditText edtSenhaNova;
    private EditText edtConfSenhaNova;

    private Button btnSalvar;

    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_senha);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtSenhaNova = (EditText) findViewById(R.id.edt_senha_nova_alt_senha);
        edtConfSenhaNova = (EditText) findViewById(R.id.edt_senha_nova_conf_senha);
        edtSenhaVelha = (EditText) findViewById(R.id.edt_senha_altera_senha);
        btnSalvar = (Button) findViewById(R.id.btn_salvar_alt_senha);

        myApplication = (MyApplication) getApplicationContext();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtSenhaVelha.getText().toString().equals(myApplication.getMyAluno().getSenha()) &&
                        edtSenhaNova.getText().toString().equals(edtConfSenhaNova.getText().toString())) {
                    myApplication.getMyAluno().setSenha(edtSenhaNova.getText().toString());

                    //Cria uma instancia de Retrofit para o aluno
                    Retrofit retrofitAluno = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    //Especifica qual classe que será utilizada na hora de criar um alunoService
                    AlunoService alunoService = retrofitAluno.create(AlunoService.class);
                    Call<Void> alunoCall = alunoService.alterAluno(myApplication.getMyAluno());

                    alunoCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Senha alterada com sucesso", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Erro na conexão", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Erro na conexão", Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return false;
    }

}
