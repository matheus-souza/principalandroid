package br.gamesapp.principal_android.util;

/**
 * Created by matheush on 13/12/16.
 */

public class GeraCodConfirmacao {
    public GeraCodConfirmacao() {
    }

    public String geraCodConfirmacao() {
        int random;
        String cod = "";
        boolean v = false;
        for (int i = 0; i < 8; i++) {
            v = false;
            do {
                random = (int) (Math.random() * 122);
                if (random >= 48 && random <= 57)
                    v = true;
                else if (random >= 97 && random <= 122)
                    v = true;
                else if (random >= 65 && random <= 90)
                    v = true;
            } while (!v);
            //System.out.print((char) random + "");
            cod += (char) random;
        }
        //System.out.println(cod);

        return cod;
    }
}
