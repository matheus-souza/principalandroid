package entidades.atividade;

import java.io.Serializable;

/**
 *
 * @author matheush
 */
public class Atividade implements Serializable {
    private String id;
    private String situacao;
    private String nome;
    private String descricao;
    private String data;
    private String hora;
    private Endereco endereco;
    private Pontuacao pontuacao;
    private Classificacao classificacao;

    public Atividade(String nome, String descricao, String data, String hora, Endereco endereco, Pontuacao pontuacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
        this.endereco = endereco;
        this.pontuacao = pontuacao;
    }

    public Atividade() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pontuacao getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Pontuacao pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nATIVIDADE");
        stringBuilder.append("\nNome: " + nome);
        stringBuilder.append("\nData: " + data);
        stringBuilder.append("\nHora: " + hora);
        stringBuilder.append("\nEndereço: " + endereco.toString());
        stringBuilder.append("\nDescrição: " + descricao);
        if (classificacao != null) {
            stringBuilder.append("\nClassificação: ");
            stringBuilder.append(classificacao.toString());  
        }
        stringBuilder.append("\nPontuação:");
        stringBuilder.append(pontuacao.toString());
                  
        return stringBuilder.toString();
    }
}
