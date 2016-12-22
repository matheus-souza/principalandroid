package br.gamesapp.principal_android.activity.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.ArrayList;
import java.util.List;

import br.gamesapp.principal_android.MyApplication;
import br.gamesapp.principal_android.R;
import br.gamesapp.principal_android.activity.MainActivity;
import br.gamesapp.principal_android.entidades.pessoa.Aluno;
import br.gamesapp.principal_android.interfaces.AlunoService;
import br.gamesapp.principal_android.util.criptografia.CodCifraDeVigenere;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DadosGeraisActivity extends AppCompatActivity implements Validator.ValidationListener, AdapterView.OnItemSelectedListener {
    private final String BASE_URL = "http://192.168.2.122:8080/web-service/webresources/GamesApp/";

    @NotEmpty(message = "Preencha este campo")
    private EditText edtNomeDadosGerais;
    @NotEmpty(message = "Preencha este campo")
    private EditText edtMatriculaDadosGerais;
    @Password(message = "Utilize no minimo 7 caracteres, letras maiusculas, minusculas e números")
    private EditText edtSenhaDadosGerais;
    @ConfirmPassword
    private EditText edtConfirmarSenhaDadosGerais;
    private Spinner spTurma;

    private Button btnConcluir;

    private Intent intent;

    private MyApplication myApplication;

    private Validator validator;

    private String email;

    ArrayList<String> matriculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_gerais);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNomeDadosGerais = (EditText) findViewById(R.id.edt_nome_dados_gerais);
        edtMatriculaDadosGerais = (EditText) findViewById(R.id.edt_matricula_dados_gerais);
        edtSenhaDadosGerais = (EditText) findViewById(R.id.edt_senha_dados_gerais);
        edtConfirmarSenhaDadosGerais = (EditText) findViewById(R.id.edt_confirmar_senha_dados_gerais);
        btnConcluir = (Button) findViewById(R.id.btn_cadastrar_dados_gerais);
        spTurma = (Spinner) findViewById(R.id.sp_turma_dados_gerais);

        myApplication = (MyApplication) getApplicationContext();

        intent = getIntent();

        email = intent.getStringExtra("email");

        // Spinner click listener
        spTurma.setOnItemSelectedListener(this);

        ArrayList<String> turmaString = new ArrayList<>();

        for (int i=0; i<myApplication.getTurmaArrayList().size(); i++) {
            turmaString.add(myApplication.getTurmaArrayList().get(i).getNome());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, turmaString);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spTurma.setAdapter(dataAdapter);

        validator = new Validator(this);
        validator.setValidationListener(this);

        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });
    }


    @Override
    public void onValidationSucceeded() {
        if (matriculas.contains(edtMatriculaDadosGerais.getText().toString())) {
            final Aluno aluno = new Aluno();
            aluno.setEmail(email);
            aluno.setNome(edtNomeDadosGerais.getText().toString());
            aluno.setMatricula(edtMatriculaDadosGerais.getText().toString());
            aluno.setSenha(edtSenhaDadosGerais.getText().toString());

            Log.d("LogXaluno", aluno.toString());

            //Cria uma instancia de Retrofit para o aluno
            Retrofit retrofitAluno = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            //Especifica qual classe que será utilizada na hora de criar um alunoService
            AlunoService alunoService = retrofitAluno.create(AlunoService.class);
            Call<Void> alunoCall = alunoService.insertAluno(aluno);

            alunoCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                        myApplication.setMyAluno(aluno);
                        startActivity(new Intent(DadosGeraisActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Erro na conexão", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro na conexão", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Verifique se a mátricula e/ou turma está correta", Toast.LENGTH_LONG).show();
        }





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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // On selecting a spinner item
        String item = adapterView.getItemAtPosition(i).toString();
        matriculas = myApplication.getTurmaArrayList().get(i).getAlunos();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
