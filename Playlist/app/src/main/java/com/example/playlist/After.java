package com.example.playlist;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playlist.Network.Network;

import java.util.ArrayList;
import java.util.Arrays;

public class After extends AppCompatActivity {
    protected static String user = "";
    protected static ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    SQLiteDatabase db;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after);

        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();

        if (intent.hasExtra("username")) {
            user = intent.getExtras().getString("username");
        }

        TextView text = findViewById(R.id.txt_example3);
        //TextView text2 = findViewById(R.id.textView2);
        lv = findViewById(R.id.listview5);
        text.setText("Hi " + user);

        View view = findViewById(R.id.after);

        Network network = new Network();

        network.songs(
                user,
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
                            listItems=new ArrayList<>(Arrays.asList(subStr));
                            adapter=new ArrayAdapter<String>(After.this, R.layout.list_item, R.id.txtitem, listItems);
                            lv.setAdapter(adapter);
                        }
                    }
                }
        );

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String selectedItem = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(After.this, Change.class);
                intent.putExtra("pos", Long.toString(parent.getItemIdAtPosition(position)));
                intent.putExtra("name", user);

                startActivity(intent);
            }
        });
    }

    public void lick(View v) {
        db.execSQL("UPDATE users SET age = 0 WHERE name = '" + user +"'");
        Intent intent = new Intent(After.this, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, v, "robot");
        startActivity(intent, options.toBundle());
    }

    public void but(View v){
        Intent intent = new Intent(After.this, Friends.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, v, "robot");
        intent.putExtra("name", user);

        startActivity(intent, options.toBundle());
    }

    public void plus(View v){
        Intent intent = new Intent(After.this, Plus.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, v, "robot");
        intent.putExtra("name", user);
        startActivity(intent, options.toBundle());
    }

    public void serves(View view) {
        Intent intent = new Intent(After.this, Serves.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, "robot");
        intent.putExtra("name", user);
        startActivity(intent, options.toBundle());
    }

    public void top(View view){
        Intent intent = new Intent(After.this, Top.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, "robot");
        intent.putExtra("name", user);
        startActivity(intent, options.toBundle());
    }
}
