package br.gamesapp.principal_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import br.gamesapp.principal_android.MyApplication;
import br.gamesapp.principal_android.R;
import br.gamesapp.principal_android.activity.cadastro.EmailActivity;
import br.gamesapp.principal_android.entidades.atividade.Atividade;
import br.gamesapp.principal_android.entidades.deserializer.AlunoDeserializer;
import br.gamesapp.principal_android.entidades.deserializer.AtividadeDeserializer;
import br.gamesapp.principal_android.entidades.deserializer.TurmaDeserializer;
import br.gamesapp.principal_android.entidades.pessoa.Aluno;
import br.gamesapp.principal_android.entidades.turma.Turma;
import br.gamesapp.principal_android.interfaces.AlunoService;
import br.gamesapp.principal_android.interfaces.AtividadeService;
import br.gamesapp.principal_android.interfaces.TurmaService;
import br.gamesapp.principal_android.util.SendMail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {

    private final String BASE_URL = "http://192.168.2.122:8080/web-service/webresources/GamesApp/";

    @Email(message = "E-mail inválido")
    private EditText edtEmail;
    @NotEmpty(message = "Peencha este campo")
    private EditText edtSenha;
    private ActionProcessButton btnEntrar;
    private ActionProcessButton btnCadastrar;
    private TextView tvRecuperaSenha;

    private MyApplication myApplication;

    private Validator validator;

    private String senhaProEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        validator = new Validator(this);
        validator.setValidationListener(this);

        edtEmail = (EditText) findViewById(R.id.edt_email_login);
        edtSenha = (EditText) findViewById(R.id.edt_senha_login);
        btnEntrar = (ActionProcessButton) findViewById(R.id.btn_entrar_login);
        btnCadastrar = (ActionProcessButton) findViewById(R.id.btn_cadastrar_login);
        tvRecuperaSenha = (TextView) findViewById(R.id.tv_recuperar_login);
        myApplication = (MyApplication) getApplicationContext();

        btnEntrar.setMode(ActionProcessButton.Mode.ENDLESS);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, EmailActivity.class));
            }
        });

        tvRecuperaSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //ALUNO
                //Cria um conversor para a classe Aluno
                Gson gsonAluno2 = new GsonBuilder().registerTypeAdapter(Aluno.class, new AlunoDeserializer()).create();

                //Cria uma instancia de Retrofit para o aluno
                Retrofit retrofitAluno2 = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gsonAluno2))
                        .build();

                //Especifica qual classe que será utilizada na hora de criar um alunoService
                AlunoService alunoService2 = retrofitAluno2.create(AlunoService.class);
                //Cria a Call que será utilizada
                Call<Aluno> alunoCall2 = alunoService2.recuperaAluno(edtEmail.getText().toString());
                //Implementa um Call para aluno, responsavel por realizar a chamada assincrona
                alunoCall2.enqueue(new Callback<Aluno>() {
                                       @Override
                                       public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                                           if (response.isSuccessful()) {
                                               myApplication.setMyAluno(response.body());

                                               if (myApplication.getMyAluno().getNome() != null) {
                                                   senhaProEmail = myApplication.getMyAluno().getSenha();
                                               } else {
                                                   edtEmail.setText("");
                                                   edtSenha.setText("");
                                                   edtEmail.requestFocus();
                                                   Toast.makeText(getApplicationContext(), "Aluno inválido", Toast.LENGTH_LONG).show();
                                               }
                                           } else {
                                               btnEntrar.setProgress(-1);
                                           }
                                       }

                                       @Override
                                       public void onFailure(Call<Aluno> call, Throwable t) {
                                           Toast.makeText(getApplicationContext(), "Erro na conexão", Toast.LENGTH_LONG).show();
                                       }
                                   });
                sendEmail();
            }
        });
    }


    private void sendEmail() {
        //Getting content for email
        String email = edtEmail.getText().toString().trim();
        String subject = "Recuperação de senha!";
        String message = "Sua senha do aplicativo GamesApp é: " + senhaProEmail + ". Caso não tenha solocitado a recuperação de senha" +
                "entrar o mais rápido possivel com a equipe de desenvolvimento";


        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onValidationSucceeded() {
        //Inicia o efeito de loader do botão
        btnEntrar.setProgress(1);

        //ALUNO
        //Cria um conversor para a classe Aluno
        Gson gsonAluno = new GsonBuilder().registerTypeAdapter(Aluno.class, new AlunoDeserializer()).create();

        //Cria uma instancia de Retrofit para o aluno
        Retrofit retrofitAluno = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonAluno))
                .build();

        //Especifica qual classe que será utilizada na hora de criar um alunoService
        AlunoService alunoService = retrofitAluno.create(AlunoService.class);
        //Cria a Call que será utilizada
        Call<Aluno> alunoCall = alunoService.loginAluno(
                edtEmail.getText().toString(),
                edtSenha.getText().toString());
        //Implementa um Call para aluno, responsavel por realizar a chamada assincrona
        alunoCall.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                if (response.isSuccessful()) {
                    myApplication.setMyAluno(response.body());

                    if (myApplication.getMyAluno().getNome() != null) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        edtEmail.setText("");
                        edtSenha.setText("");
                        edtEmail.requestFocus();
                        Toast.makeText(getApplicationContext(), "Aluno e/ou senha inválido", Toast.LENGTH_LONG).show();
                    }
                } else {
                    btnEntrar.setProgress(-1);
                }
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                btnEntrar.setProgress(-1);
            }
        });

        //ATIVIDADE
        //Cria um conversor para a classe Atividade
        Gson gsonAtividade = new GsonBuilder().registerTypeAdapter(Atividade.class, new AtividadeDeserializer()).create();

        //Cria uma instancia de Retrofit para a atividade
        Retrofit retrofitAtividade = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonAtividade))
                .build();

        //Especifica qual classe que será utilizada na hora de criar uma atividadeService
        AtividadeService atividadeService = retrofitAtividade.create(AtividadeService.class);
        //Cria a Call que será utilizada
        Call<ArrayList<Atividade>> atividadeCall = atividadeService.getAtividades();
        //Implementa um Call para aluno, responsavel por realizar a chamada assincrona
        atividadeCall.enqueue(new Callback<ArrayList<Atividade>>() {
            @Override
            public void onResponse(Call<ArrayList<Atividade>> call, Response<ArrayList<Atividade>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Atividade> atividades = response.body();

                    //myApplication.setAtividadeArrayList(response.body());

                    //Log.d("LogX", myApplication.getAtividadeArrayList().toString());

                    for (int i=0; i<atividades.size();i++) {
                        if (atividades.get(i).getSituacao().equals("Em andamento")) {
                            myApplication.getAtividadesAndamentoArrayList().add(atividades.get(i));
                        } else {
                            myApplication.getAtividadesConcluidasArrayList().add(atividades.get(i));
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erro na conexão", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Atividade>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro na conexão", Toast.LENGTH_LONG).show();
            }
        });

        //TURMA
        //Cria um conversor para a classe Atividade
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
        turmaCall.enqueue(new Callback<ArrayList<Turma>>() {
            @Override
            public void onResponse(Call<ArrayList<Turma>> call, Response<ArrayList<Turma>> response) {
                if (response.isSuccessful()) {
                    myApplication.setTurmaArrayList(response.body());
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
