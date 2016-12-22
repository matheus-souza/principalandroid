package br.gamesapp.principal_android.interfaces;

import java.util.ArrayList;

import br.gamesapp.principal_android.entidades.atividade.Atividade;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by matheush on 14/12/16.
 */

public interface AtividadeService {

    @GET("Atividade/get/todas")
    Call<ArrayList<Atividade>> getAtividades();
}
