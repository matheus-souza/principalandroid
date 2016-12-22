package br.gamesapp.principal_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.gamesapp.principal_android.MyApplication;
import br.gamesapp.principal_android.R;
import br.gamesapp.principal_android.entidades.atividade.Atividade;

public class DadosAtividadeActivity extends AppCompatActivity {

    private Intent intent;
    private int atividadeId;
    private int moderador;
    private Atividade atividade;

    private TextView tvNomeAtividade;
    private TextView tvDescricaoAtividade;
    private TextView tvDataAtividade;
    private TextView tvHoraAtividade;
    private TextView tvLocalAtividade;
    private TextView tvPontuacaoPrimeiroAtividade;
    private TextView tvPontuacaoSegundoAtividade;
    private TextView tvPontuacaoTerceiroAtividade;
    private TextView tvClassificacaoPrimeiroAtividade;
    private TextView tvClassificacaoSegundoAtividade;
    private TextView tvClassificacaoTerceiroAtividade;

    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_atividade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvNomeAtividade = (TextView) findViewById(R.id.tv_nome_atividade_dados);
        tvDescricaoAtividade = (TextView) findViewById(R.id.tv_descricao_dados);
        tvDataAtividade = (TextView) findViewById(R.id.tv_data_dados);
        tvHoraAtividade = (TextView) findViewById(R.id.tv_hora_dados);
        tvLocalAtividade = (TextView) findViewById(R.id.tv_local_dados);
        tvPontuacaoPrimeiroAtividade = (TextView) findViewById(R.id.tv_pontuacao_primeiro_dados);
        tvPontuacaoSegundoAtividade = (TextView) findViewById(R.id.tv_pontuacao_segundo_dados);
        tvPontuacaoTerceiroAtividade = (TextView) findViewById(R.id.tv_pontuacao_terceiro_dados);
        tvClassificacaoPrimeiroAtividade = (TextView) findViewById(R.id.tv_classificacao_primeiro_dados);
        tvClassificacaoSegundoAtividade = (TextView) findViewById(R.id.tv_classificacao_segundo_dados);
        tvClassificacaoTerceiroAtividade = (TextView) findViewById(R.id.tv_classificacao_terceiro_dados);


        myApplication = (MyApplication) getApplicationContext();

        intent = getIntent();

        moderador = intent.getIntExtra("moderador", 0);
        atividadeId = intent.getIntExtra("atividadeid", 0);

        if (moderador == 1) {
            atividade = myApplication.getAtividadesAndamentoArrayList().get(atividadeId);
        } else {
            atividade = myApplication.getAtividadesConcluidasArrayList().get(atividadeId);
        }

        tvNomeAtividade.setText(atividade.getNome());
        tvDescricaoAtividade.setText(atividade.getDescricao());
        tvDataAtividade.setText(atividade.getData());
        tvHoraAtividade.setText(atividade.getHora());
        tvLocalAtividade.setText(atividade.getEndereco().toString());
        tvPontuacaoPrimeiroAtividade.setText(String.valueOf(atividade.getPontuacao().getPrimeiro()));
        tvPontuacaoSegundoAtividade.setText(String.valueOf(atividade.getPontuacao().getSegundo()));
        tvPontuacaoTerceiroAtividade.setText(String.valueOf(atividade.getPontuacao().getTerceiro()));
        if (!atividade.getClassificacao().getPrimeiro().equals("")) {
            tvClassificacaoPrimeiroAtividade.setText(atividade.getClassificacao().getPrimeiro());
            tvClassificacaoSegundoAtividade.setText(atividade.getClassificacao().getSegundo());
            tvClassificacaoTerceiroAtividade.setText(atividade.getClassificacao().getTerceiro());
        } else {
            tvClassificacaoPrimeiroAtividade.setText("Em apuração");
            tvClassificacaoSegundoAtividade.setText("Em apuração");
            tvClassificacaoTerceiroAtividade.setText("Em apuração");
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
