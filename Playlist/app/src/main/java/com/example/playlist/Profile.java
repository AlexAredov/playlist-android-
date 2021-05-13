package com.example.playlist;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playlist.Network.Network;

import java.util.ArrayList;
import java.util.Arrays;

public class Profile extends AppCompatActivity {
    String name;
    String usr;
    TextView textView;
    TextView tv;
    Button button;
    ArrayList<String> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        usr = intent.getExtras().getString("usname");
        textView = findViewById(R.id.txt_example);
        textView.setText(name);
        tv = findViewById(R.id.textView4);
        button = findViewById(R.id.buttonb);
        Network network = new Network();
        network.songs(
                name,
                new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg){
                        super.handleMessage(msg);
                        if (msg.obj != null) {
                            String[] subStr = null;
                            String delimeter = ", ";
                            String nnn = msg.obj.toString();
                            nnn = nnn.replaceAll("\"", "");
                            subStr = nnn.split(delimeter);
                            for (int i = 0; i < subStr.length; i++){
                                tv.setText(tv.getText() + Integer.toString(i + 1) + ") " + subStr[i] + "\n");
                            }
                        }
                    }
                });

        network.friends(
                usr,
                new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg){
                        super.handleMessage(msg);
                        if (msg.obj != null) {
                            String[] subStr = null;
                            String delimeter = ", ";
                            subStr = msg.obj.toString().split(delimeter);
                            int k = 0;
                            for (int i = 0; i < subStr.length; i++) {
                                Log.e("myTag", subStr[i]);
                                subStr[i] = subStr[i].replaceAll("\"", "");
                                if (name.equals(subStr[i])) {
                                    k = 1;
                                }
                            }
                            if (k == 0){
                                button.setText("Add friend");
                            }
                            else{
                                button.setText("Remove friend");
                            }
                        }
                        else{
                            button.setText("Add friend");
                        }
                    }
                }
        );

    }

    public void back(View view) {
        Intent intent = new Intent(Profile.this, Friends.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, "robot");
        startActivity(intent, options.toBundle());
    }

    public void save(View view) {
        Network network = new Network();
        network.friends(
                usr,
                new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg){
                        super.handleMessage(msg);
                        if (msg.obj != null) {
                            String[] subStr = null;
                            String delimeter = ", ";
                            subStr = msg.obj.toString().split(delimeter);
                            int k = 0;
                            for (int i = 0; i < subStr.length; i++) {
                                Log.e("myTag", subStr[i]);
                                subStr[i] = subStr[i].replaceAll("\"", "");
                                if (name.equals(subStr[i])) {
                                    k = 1;
                                }
                            }
                            if (k == 0){
                                listItems=new ArrayList<>(Arrays.asList(subStr));
                                listItems.add(name);
                                String listString = String.join(", ", listItems);
                                listString = listString.replaceAll("\"", "");
                                network.addfr(usr, listString, new Handler());
                                button.setText("Remove friend");
                            }
                            else{
                                listItems=new ArrayList<>(Arrays.asList(subStr));
                                listItems.remove(name);
                                String listString = String.join(", ", listItems);
                                listString = listString.replaceAll("\"", "");
                                network.addfr(usr, listString, new Handler());
                                button.setText("Add friend");
                            }
                        }
                        else{
                            listItems=new ArrayList<String>();
                            listItems.add(name);
                            String listString = String.join(", ", listItems);
                            listString = listString.replaceAll("\"", "");
                            network.addfr(usr, listString, new Handler());
                            button.setText("Remove friend");
                        }
                    }
                }
        );
    }
}