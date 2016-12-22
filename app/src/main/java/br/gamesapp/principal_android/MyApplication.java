package br.gamesapp.principal_android;

import android.app.Application;

import java.util.ArrayList;

import br.gamesapp.principal_android.entidades.atividade.Atividade;
import br.gamesapp.principal_android.entidades.pessoa.Aluno;
import br.gamesapp.principal_android.entidades.turma.Turma;

/**
 * Created by matheush on 13/12/16.
 */

public class MyApplication extends Application {

    private ArrayList<Turma> turmaArrayList;
    private ArrayList<Atividade> atividadesAndamentoArrayList;
    private ArrayList<Atividade> atividadesConcluidasArrayList;
    private Aluno MyAluno;

    @Override
    public void onCreate() {
        turmaArrayList = new ArrayList<>();
        atividadesAndamentoArrayList = new ArrayList<>();
        atividadesConcluidasArrayList = new ArrayList<>();
        MyAluno = new Aluno();

        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public ArrayList<Turma> getTurmaArrayList() {
        return turmaArrayList;
    }

    public void setTurmaArrayList(ArrayList<Turma> turmaArrayList) {
        this.turmaArrayList = turmaArrayList;
    }

    public ArrayList<Atividade> getAtividadesAndamentoArrayList() {
        return atividadesAndamentoArrayList;
    }

    public void setAtividadesAndamentoArrayList(ArrayList<Atividade> atividadesAndamentoArrayList) {
        this.atividadesAndamentoArrayList = atividadesAndamentoArrayList;
    }

    public ArrayList<Atividade> getAtividadesConcluidasArrayList() {
        return atividadesConcluidasArrayList;
    }

    public void setAtividadesConcluidasArrayList(ArrayList<Atividade> atividadesConcluidasArrayList) {
        this.atividadesConcluidasArrayList = atividadesConcluidasArrayList;
    }

    public Aluno getMyAluno() {
        return MyAluno;
    }

    public void setMyAluno(Aluno myAluno) {
        MyAluno = myAluno;
    }
}
