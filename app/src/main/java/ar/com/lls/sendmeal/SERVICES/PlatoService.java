package ar.com.lls.sendmeal.SERVICES;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import ar.com.lls.sendmeal.model.Plato;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlatoService {

    @GET("plato/{id}")
    Call<Plato> getPlato(@Path("id") String id);

    @GET("plato/list")
    Call<List<Plato>> getPlatoList();

    @POST("plato")
    Call<Plato> createPlato(@Body Plato plato);
  /*
    Si deciden usar SendMeal-Fake-API deberán usar un body
    del tipo @Body String body. Lo cual les facilitara cumplir el formato esperado

    JSONObject bodyExample = new JSONObject();
    paramObject.put("email", "sample@gmail.com");
    paramObject.put("pass", "4384984938943");
    createPlato(bodyExample.toString())
  */

    Gson gson = new GsonBuilder().setLenient().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("{urlApiRest}/")
            // En la siguiente linea, le especificamos a Retrofit que tiene que usar Gson para deserializar nuestros objetos
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    PlatoService platoService = retrofit.create(PlatoService.class);
}
