package com.example.playlist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playlist.Network.Network;

public class Serves extends AppCompatActivity {
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serves);

        Network network = new Network();

        Intent intent = getIntent();


        user = intent.getExtras().getString("name");

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton radioButtonA = findViewById(R.id.Apple);
        RadioButton radioButtonS = findViewById(R.id.Spotify);
        RadioButton radioButtonV = findViewById(R.id.Vk);
        RadioButton radioButtonY = findViewById(R.id.Yandex);

        radioGroup.clearCheck();

        network.serv(
                user,
                new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg){
                        if (msg.obj != null) {
                            String[] subStr = null;
                            String delimeter = ", ";
                            String nnn = msg.obj.toString();
                            if (nnn.equals("apple")){
                                radioButtonA.setChecked(true);
                            }
                            else if (nnn.equals("spotify")){
                                radioButtonS.setChecked(true);
                            }
                            else if (nnn.equals("vk")){
                                radioButtonV.setChecked(true);
                            }
                            else if (nnn.equals("yandex")){
                                radioButtonY.setChecked(true);
                            }
                        }
                    }
                });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        break;
                    case R.id.Apple:
                        network.service(user, "apple", new Handler());
                        break;
                    case R.id.Spotify:
                        network.service(user, "spotify", new Handler());
                        break;
                    case R.id.Vk:
                        network.service(user, "vk", new Handler());
                        break;
                    case R.id.Yandex:
                        network.service(user, "yandex", new Handler());
                        break;
                }
            }
        });
    }

    public void back(View view) {
        Intent intent = new Intent(Serves.this, After.class);
        startActivity(intent);
    }

    public  void MI(View view){
        Intent intent = new Intent(Serves.this, MI.class);
        intent.putExtra("name1", "alex");
        startActivity(intent);
    }
}