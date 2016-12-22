package br.gamesapp.principal_android.interfaces;

import br.gamesapp.principal_android.entidades.pessoa.Aluno;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by matheush on 14/12/16.
 */

public interface AlunoService {

    @GET("Aluno/login/{email}/{senha}")
    Call<Aluno> loginAluno();
}
