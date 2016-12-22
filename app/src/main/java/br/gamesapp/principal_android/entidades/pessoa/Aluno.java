package entidades.pessoa;

import java.io.Serializable;

/**
 *
 * @author matheush
 */
public class Aluno extends Pessoa implements Serializable {
    private String matricula;
    
    public Aluno(String nome, String email, String matricula) {
        super.nome = nome;
        super.email = email;
        this.matricula = matricula;
    }

    public Aluno() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append("\nALUNO");
        stringBuilder.append("\n" + nome);
        stringBuilder.append("\n" + matricula);
        stringBuilder.append("\n" + email);
        stringBuilder.append("\n" + senha);
        
        return stringBuilder.toString();
    }
}
