package br.gamesapp.principal_android.interfaces;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import br.gamesapp.principal_android.entidades.turma.Turma;

/**
 * Created by matheush on 14/12/16.
 */

public interface TurmaService {

    @GET("Turma/get/todas)")
    Call<ArrayList<Turma>> getTurmas();
}
