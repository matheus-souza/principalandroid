package br.gamesapp.principal_android.activity.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import br.gamesapp.principal_android.MyApplication;
import br.gamesapp.principal_android.R;
import br.gamesapp.principal_android.entidades.deserializer.TurmaDeserializer;
import br.gamesapp.principal_android.entidades.turma.Turma;
import br.gamesapp.principal_android.interfaces.TurmaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CodConfirmacaoActivity extends AppCompatActivity implements Validator.ValidationListener {
    private final String BASE_URL = "http://192.168.2.122:8080/web-service/webresources/GamesApp/";

    private Intent intent;
    private String codConfirmacao;
    private String email;

    @NotEmpty(message = "Preencha o campo")
    private EditText edtCodConfirmacao;
    private Button btnAvancar;

    private MyApplication myApplication;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cod_confirmacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtCodConfirmacao = (EditText) findViewById(R.id.edt_cod_confirmacao_cadastro);
        btnAvancar = (Button) findViewById(R.id.btn_avancar_codconfirmacao_cadastro);

        myApplication = (MyApplication) getApplicationContext();

        validator = new Validator(this);
        validator.setValidationListener(this);

        intent = getIntent();

        email = intent.getStringExtra("email").toString();
        codConfirmacao = intent.getStringExtra("codConfirmacao").toString();

        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        //TURMA
        //Cria um conversor para a classe Turma
        Gson gsonTurma = new GsonBuilder().registerTypeAdapter(Turma.class, new TurmaDeserializer()).create();

        //Cria uma instancia de Retrofit para a atividade
        Retrofit retrofitTurma = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonTurma))
                .build();

        //Especifica qual classe que será utilizada na hora de criar uma atividadeService
        TurmaService turmaService = retrofitTurma.create(TurmaService.class);
        //Cria a Call que será utilizada
        Call<ArrayList<Turma>> turmaCall = turmaService.getTurmas();
        //Implementa um Call para aluno, responsavel por realizar a chamada assincrona
        turmaCall.enqueue(new Callback<ArrayList<Turma>>() {
            @Override
            public void onResponse(Call<ArrayList<Turma>> call, Response<ArrayList<Turma>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Turma> turmas = response.body();

                    myApplication.setTurmaArrayList(turmas);
                } else {
                    Toast.makeText(getApplicationContext(), "Erro na conexão", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Turma>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro na conexão", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        Intent intent = new Intent(CodConfirmacaoActivity.this, DadosGeraisActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
