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
    Call<Person> postPerson(@Body String person);

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

    @POST("serves/{name}/{service}")
    Call<Void> service(@Path("name") String name, @Path("service") String service);

    @GET("/service/{name}")
    Call<Person> serv(@Path("name") String name);

    @POST("/mipost/{name}/{mi}")
    Call<Void> mip(@Path("name") String name, @Path("mi") String mi);

    @GET("/miget/{name}")
    Call<Person> mig(@Path("name") String name);


    @GET("spotify/{name}")
    Call<List<String>> spotify(@Path("name") String name);

    @GET("apple/{name}")
    Call<List<String>> apple(@Path("name") String name);

    @GET("vk/{name}")
    Call<List<String>> vk(@Path("name") String name);

    @GET("yandex/{name}")
    Call<List<String>> yandex(@Path("name") String name);

    @GET("/topsongs")
    Call<List<String>> top();

}
