package com.example.playlist;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playlist.Network.Network;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Top extends AppCompatActivity {

    ArrayList<String> listItems;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        ListView lv = findViewById(R.id.listview100);

        Intent intent = getIntent();

        String user = intent.getExtras().getString("name");

        Network network = new Network();

        network.top(
                new Handler(){
                    @SuppressLint("HandlerLeak")
                    @Override
                    public void handleMessage(@NonNull Message msg){
                        ArrayAdapter<String> adapter;

                        super.handleMessage(msg);
                        if (msg.obj != null) {
                            String[] subStr = null;
                            String delimeter = ", ";
                            String nnn = msg.obj.toString();
                            nnn = nnn.replaceAll("\"", "");
                            nnn = nnn.replaceAll("\\[|\\]", "");
                            subStr = nnn.split(delimeter);
                            listItems=new ArrayList<>(Arrays.asList(subStr));
                            adapter=new ArrayAdapter<String>(Top.this, R.layout.list_item, R.id.txtitem, listItems);
                            lv.setAdapter(adapter);
                        }
                    }
                });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);


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
                                        network.apple(
                                                selectedItem,
                                                new Handler(){
                                                    @SuppressLint("HandlerLeak")
                                                    @Override
                                                    public void handleMessage(@NonNull Message msg){
                                                        try {
                                                            sleep(100);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(msg.obj.toString()));
                                                        startActivity(browserIntent);
                                                    }
                                                });
                                    }
                                    else if (nnn.equals("spotify")){
                                        network.spotify(
                                                selectedItem,
                                                new Handler(){
                                                    @Override
                                                    public void handleMessage(@NonNull Message msg){
                                                        try {
                                                            sleep(100);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(msg.obj.toString()));
                                                        startActivity(browserIntent);
                                                    }
                                                });
                                    }
                                    else if (nnn.equals("vk")){
                                        network.vk(
                                                selectedItem,
                                                new Handler(){
                                                    @Override
                                                    public void handleMessage(@NonNull Message msg){
                                                        try {
                                                            sleep(100);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(msg.obj.toString()));
                                                        startActivity(browserIntent);
                                                    }
                                                });
                                    }
                                    else if (nnn.equals("yandex")){
                                        network.yandex(
                                                selectedItem,
                                                new Handler(){
                                                    @Override
                                                    public void handleMessage(@NonNull Message msg){
                                                        try {
                                                            sleep(100);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(msg.obj.toString()));
                                                        startActivity(browserIntent);
                                                    }
                                                });
                                    }
                                }
                            }
                        });
            }
        });
    }

    public void back(View view) {
        Intent intent = new Intent(Top.this, After.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, "robot");
        startActivity(intent, options.toBundle());
    }
}