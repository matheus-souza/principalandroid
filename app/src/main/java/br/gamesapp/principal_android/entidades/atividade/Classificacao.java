package entidades.atividade;

/**
 *
 * @author matheush
 */
public class Classificacao {
    private String primeiro;
    private String segundo;
    private String terceiro;

    public Classificacao(String primeiro, String segundo, String terceiro) {
        this.primeiro = primeiro;
        this.segundo = segundo;
        this.terceiro = terceiro;
    }

    public Classificacao() {
    }

    public String getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(String primeiro) {
        this.primeiro = primeiro;
    }

    public String getSegundo() {
        return segundo;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }

    public String getTerceiro() {
        return terceiro;
    }

    public void setTerceiro(String terceiro) {
        this.terceiro = terceiro;
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append("\n1- " + primeiro);
        stringBuilder.append("\n2- " + segundo);
        stringBuilder.append("\n3- " + terceiro);
        
        return stringBuilder.toString();
    }
}
