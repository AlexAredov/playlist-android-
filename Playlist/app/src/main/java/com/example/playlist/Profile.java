package com.example.playlist;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playlist.Network.Network;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Thread.sleep;

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
        ListView lv = findViewById(R.id.listview4);

        String[] mi = {"Ukelele", "Bangos", "Harmonica", "Bass Guitar", "Drums", "Piano", "Xylophone", "Kalimba", "Clarinet", "Trombone", "Guitar", "Saxophone", "Steel Tongue Drum", "Flute", "Theremin", "Harp", "Cello", "Magix Music Maker", "Image-Line FL Studio 20", "Native Instruments Maschine 2 MK3", "Propellerheads Reason+"};

        network.miget(
                usr,
                new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        String[] str = msg.obj.toString().split(", ");
                        for (int i = 0; i < str.length; i++){
                            String name = mi[Integer.parseInt(str[i]) - 1];
                            tv.setText(tv.getText() + name + ", ");
                            //text.setText(name);
                        }
                    }
                }
        );

        network.songs(
                name,
                new Handler(){
                    @SuppressLint("HandlerLeak")
                    @Override
                    public void handleMessage(@NonNull Message msg){
                        ArrayAdapter<String> adapter;

                        super.handleMessage(msg);
                        if (msg.obj != null) {
                            ListView lv = findViewById(R.id.listview4);
                            String[] subStr = null;
                            String delimeter = ", ";
                            String nnn = msg.obj.toString();
                            nnn = nnn.replaceAll("\"", "");
                            subStr = nnn.split(delimeter);
//                            for (int i = 0; i < subStr.length; i++){
//                                tv.setText(tv.getText() + Integer.toString(i + 1) + ") " + subStr[i] + "\n");
//                            }
                            listItems=new ArrayList<>(Arrays.asList(subStr));
                            adapter=new ArrayAdapter<String>(Profile.this, R.layout.list_item, R.id.txtitem, listItems);
                            lv.setAdapter(adapter);
                        }
                    }
                });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);


                network.serv(
                        usr,
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