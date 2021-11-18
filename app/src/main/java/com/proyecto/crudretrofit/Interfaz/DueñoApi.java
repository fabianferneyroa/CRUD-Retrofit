package com.proyecto.crudretrofit.Interfaz;

import com.proyecto.crudretrofit.modelo.Dueño;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DueñoApi {

    @GET("api/dueños/{id}")
    public Call<Dueño> find(@Path("id") Long id);

    @POST("api/dueños/")
    public Call<Dueño> save(@Body Dueño dueño);

    @DELETE("api/dueños/{id}")
    public Call<Void> delete(@Path("id") Long id);

}
