package com.example.playlist.Network;

import android.os.Handler;
import android.os.Message;

import com.example.playlist.Model.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    Retrofit retrofit;
    API api;

    public Network() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://playlist-ad.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.api = retrofit.create(API.class);
    }

    public void getUsers(Handler handler){
        Call<List<String>> call = api.getAll();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Message msg = new Message();
                msg.obj = response.body();
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void postPerson(Person person, Handler handler) {
        String s = person.getName() + " " + person.getEmail() + " " + person.getPassword();
        api.postPerson(s).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                getUsers(handler);
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void check(Person person, Handler handler){
        Call<Person> call = api.checkp(person.getName());
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Message msg = new Message();

                if (response.body() != null) {
                    msg.obj = response.body().getPassword();
                }
                else{
                    msg.obj = "ERRROOOR";
                }
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void songs(String name, Handler handler){
        Call<Person> call = api.songs(name);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Message msg = new Message();

                if (response.body() != null) {
                    msg.obj = response.body().getPassword();
                }
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void friends(String name, Handler handler){
        Call<Person> call = api.friends(name);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Message msg = new Message();

                if (response.body() != null) {
                    msg.obj = response.body().getPassword();
                }
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getAllSongs(Handler handler){
        Call<List<String>> call = api.songss();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Message msg = new Message();
                msg.obj = response.body();
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void addsongs(String name, String s, Handler handler) {
        api.addSongs(name, s).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void adsng(String s, Handler handler) {
        api.adsng(s).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void addfr(String name, String s, Handler handler) {
        api.addf(name, s).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) { }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void service(String name, String service, Handler handler) {
        api.service(name, service).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) { }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void serv(String name, Handler handler){
        Call<Person> call = api.serv(name);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Message msg = new Message();

                if (response.body() != null) {
                    msg.obj = response.body().getPassword();
                }
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void mipost(String name, String mi, Handler handler) {
        api.mip(name, mi).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) { }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void miget(String name, Handler handler){
        Call<Person> call = api.mig(name);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Message msg = new Message();

                if (response.body() != null) {
                    msg.obj = response.body().getPassword();
                }
                else{
                    msg.obj = "err";
                }
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void top(Handler handler){
        Call<List<String>> call = api.top();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Message msg = new Message();
                msg.obj = response.body();
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void spotify(String name, Handler handler){
        Call<List<String>> call = api.spotify(name);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Message msg = new Message();
                String s = "";
                if (response != null) {
                    s = response.body().toString().replace("[","");
                    s = s.replace("]", "");
                    //s = "spotify:" + s;
                    msg.obj = s;
                }
                else{
                    msg.obj = "ERROR!!!!!!!!!!!";
                }
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void apple(String name, Handler handler){
        Call<List<String>> call = api.apple(name);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Message msg = new Message();
                String s = "";
                if (response != null) {
                    s = response.body().toString().replace("[","");
                    s = s.replace("]", "");
                    msg.obj = s;
                }
                else{
                    msg.obj = "ERROR!!!!!!!!!!!";
                }
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void vk(String name, Handler handler){
        Call<List<String>> call = api.vk(name);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Message msg = new Message();
                String s = "";
                if (response != null) {
                    s = response.body().toString().replace("[","");
                    s = s.replace("]", "");
                    msg.obj = s;
                }
                else{
                    msg.obj = "ERROR!!!!!!!!!!!";
                }
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void yandex(String name, Handler handler){
        Call<List<String>> call = api.yandex(name);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Message msg = new Message();
                String s = "";
                if (response != null) {
                    s = response.body().toString().replace("[","");
                    s = s.replace("]", "");
                    msg.obj = s;
                }
                else{
                    msg.obj = "ERROR!!!!!!!!!!!";
                }
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
