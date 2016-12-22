package entidades.pessoa;

import java.io.Serializable;

/**
 *
 * @author matheush
 */
public class Professor extends Pessoa implements Serializable {
    private String siape;

    public Professor(String nome, String email, String siape) {
        super.nome = nome;
        super.email = email;
        this.siape = siape;
    }

    public Professor() {
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
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
        stringBuilder.append("\nPROFESSOR");
        stringBuilder.append("\n" + nome);
        stringBuilder.append("\n" + siape);
        stringBuilder.append("\n" + email);
        stringBuilder.append("\n" + senha);
        
        return stringBuilder.toString();
    }
}
