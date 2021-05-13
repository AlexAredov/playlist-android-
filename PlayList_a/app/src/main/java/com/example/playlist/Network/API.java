package com.example.playlist.Network;

import com.example.playlist.Model.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    @GET("full")
    Call<List<String>> getAll();

    @POST("add")
    Call<Person> postPerson(@Body Person person);

    @GET("/check/{name}")
    Call<Person> checkp(@Path("name") String name);

    @GET("/songs/{name}")
    Call<Person> songs(@Path("name") String name);

    @GET("/friends/{name}")
    Call<Person> friends(@Path("name") String name);

    @GET("songs")
    Call<List<String>> songss();

    @POST("addsongs/{name}")
    Call<Void> addSongs(@Path("name") String name, @Body String s);

    @POST("adsng")
    Call<Void> adsng(@Body String s);

    @POST("fr/{name}")
    Call<Void> addf(@Path("name") String name, @Body String s);

}
