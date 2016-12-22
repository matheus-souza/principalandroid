package br.gamesapp.principal_android.interfaces;

import br.gamesapp.principal_android.entidades.pessoa.Aluno;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by matheush on 14/12/16.
 */

public interface AlunoService {

    @GET("Aluno/login/{email}/{senha}")
    Call<Aluno> loginAluno(@Path("email") String email, @Path("senha") String senha);

    @GET("Aluno/recuperacao/{email}")
    Call<Aluno> recuperaAluno(@Path("email") String email);

    @POST("Aluno/insert")
    Call<Void> insertAluno(@Body Aluno aluno);

    @PUT("Aluno/alterar")
    Call<Void> alterAluno(@Body Aluno aluno);
}
