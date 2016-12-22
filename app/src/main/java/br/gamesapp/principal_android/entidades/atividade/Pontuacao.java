package entidades.atividade;

/**
 *
 * @author matheush
 */
public class Pontuacao {
    private long primeiro;
    private long segundo;
    private long terceiro;

    public Pontuacao(long primeiro, long segundo, long terceiro) {
        this.primeiro = primeiro;
        this.segundo = segundo;
        this.terceiro = terceiro;
    }

    public Pontuacao() {
    }

    public long getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(long primeiro) {
        this.primeiro = primeiro;
    }

    public long getSegundo() {
        return segundo;
    }

    public void setSegundo(long segundo) {
        this.segundo = segundo;
    }

    public long getTerceiro() {
        return terceiro;
    }

    public void setTerceiro(long terceiro) {
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
