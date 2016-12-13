package br.gamesapp.principal_android.activity.cadastro;

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

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;

import java.util.List;

import br.gamesapp.principal_android.R;
import br.gamesapp.principal_android.util.GeraCodConfirmacao;
import br.gamesapp.principal_android.util.email.SendMail;

public class EmailActivity extends AppCompatActivity implements Validator.ValidationListener {

    @Email(message = "E-mail inválido")
    private EditText edtEmail;
    private Button btnAvancar;

    private String codConfirmacao;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtEmail = (EditText) findViewById(R.id.edt_email_cadastro);
        btnAvancar = (Button) findViewById(R.id.btn_avancar_email_cadastro);

        validator = new Validator(this);
        validator.setValidationListener(this);

        codConfirmacao = new GeraCodConfirmacao().geraCodConfirmacao();

        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

    }

    private void sendEmail() {
        //Getting content for email
        String email = edtEmail.getText().toString().trim();
        String subject = "Código de confirmação!";
        String message = "Utilize este código para confirmação do cadastro no aplicativo GamesApp: "
                + codConfirmacao;


        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onValidationSucceeded() {
        sendEmail();

        //Intent intent = new Intent(EmailActivity.this)
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return false;
    }
}
