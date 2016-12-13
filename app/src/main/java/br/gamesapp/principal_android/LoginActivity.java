package br.gamesapp.principal_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import br.gamesapp.principal_android.activity.cadastro.EmailActivity;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {

    @Email(message = "E-mail inválido")
    private EditText edtEmail;
    @NotEmpty(message = "Peencha este campo")
    private EditText edtSenha;
    private ActionProcessButton btnEntrar;
    private TextView tvCadastrar;

    //private MyApplication myApplication;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        validator = new Validator(this);
        validator.setValidationListener(this);

        edtEmail = (EditText) findViewById(R.id.edt_email_login);
        edtSenha = (EditText) findViewById(R.id.edt_senha_login);
        tvCadastrar = (TextView) findViewById(R.id.tv_cadastrar_login);
        btnEntrar = (ActionProcessButton) findViewById(R.id.btn_entrar_login);

        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, EmailActivity.class));
            }
        });

        btnEntrar.setMode(ActionProcessButton.Mode.ENDLESS);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        btnEntrar.setProgress(1);
        //Enviar email e senha para servidor e verificar se existe
        //Se existir colocar os dados na MyApplication e chamar MainActivity
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
