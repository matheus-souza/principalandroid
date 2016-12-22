package br.gamesapp.principal_android.util.criptografia;


import org.apache.commons.codec.binary.Base64;

/**
 * Created by matheush on 16/12/16.
 */

public class Alfanumerico {

    public Alfanumerico(){
    }

    public String codifica(String str) {
        return new Base64().encodeToString(str.getBytes());
    }

    public String decodifica(String str) {
        return new String(new Base64().decode(str));
    }
}
