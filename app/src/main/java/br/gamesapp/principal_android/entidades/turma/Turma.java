package entidades.turma;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author matheush
 */
public class Turma implements Serializable {
    private String id;
    private String nome;
    private int anoInicio;
    private String curso;
    private long pontos;
    private ArrayList<String> alunos;

    public Turma(String nome, int anoInicio, String curso, long pontos) {
        this.nome = nome;
        this.anoInicio = anoInicio;
        this.curso = curso;
        this.pontos = pontos;
    }

    public Turma() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(int anoInicio) {
        this.anoInicio = anoInicio;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public long getPontos() {
        return pontos;
    }

    public void setPontos(long pontos) {
        this.pontos = pontos;
    }

    public ArrayList<String> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<String> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append("\nTURMA");
        stringBuilder.append("\nNome: " + nome);
        stringBuilder.append("\nAno de inicio: " + anoInicio);
        stringBuilder.append("\nCurso: " + curso);
        stringBuilder.append("\nPontos: " + pontos);
        
        return stringBuilder.toString();
    }
}
