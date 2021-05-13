package com.example.playlist;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playlist.Network.Network;

import java.util.ArrayList;
import java.util.Arrays;

public class Friends extends AppCompatActivity {
    String[] items;
    ArrayList<String> listItems;
    ArrayList<String> listItems1;
    ArrayAdapter<String> adapter;
    ListView listView;
    ListView listView1;
    EditText editText;
    TextView t;
    protected static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        if (intent.hasExtra("name")) {
            name = intent.getExtras().getString("name");
        }
        listView=(ListView)findViewById(R.id.listview9);
        listView1=(ListView)findViewById(R.id.listview2);
        editText=(EditText)findViewById(R.id.txtsearch);
        t = (TextView) findViewById(R.id.textView5d);
        Network network = new Network();
        network.getUsers(new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg){
                super.handleMessage(msg);
                if (msg.obj != null) {
                    String result = msg.obj.toString().replaceAll("\\[", "");
                    result = result.replaceAll("\\]", "");
                    items = result.split(", ");

                    for (int i = 0; i < items.length; i++){
                        t.setText(t.getText() + "\n" + items[i]);
                    }
                }
            }
        });

        network.friends(
                name,
                new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg){
                        super.handleMessage(msg);
                        if (msg.obj != null) {
                            String[] subStr = null;
                            String delimeter = ", ";
                            String listString = msg.obj.toString();
                            listString = listString.replaceAll("\"", "");
                            subStr = listString.split(delimeter);
                            listItems1=new ArrayList<>(Arrays.asList(subStr));
                            adapter=new ArrayAdapter<String>(Friends.this, R.layout.list_item, R.id.txtitem, listItems1);
                            listView1.setAdapter(adapter);
                        }
                        else{
                            listView1.setAdapter(null);
                        }
                    }
                }
        );
        items = t.getText().toString().split("\n");

        //initList();
        //adapter.notifyDataSetChanged();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                initList();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    listView.setAdapter(null);
                } else {
                    // perform search
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(Friends.this, Profile.class);
                intent.putExtra("name", selectedItem);
                intent.putExtra("usname", name);
                startActivity(intent);
            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(Friends.this, Profile.class);
                intent.putExtra("name", selectedItem);
                intent.putExtra("usname", name);
                startActivity(intent);
            }
        });
    }

    public void searchItem(String textToSearch){
        for(String item:items){
            if(!item.contains(textToSearch)){
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void initList(){
        listItems=new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, listItems);
        listView.setAdapter(adapter);
    }

    public void but(View view) {
        Intent intent = new Intent(Friends.this, After.class);
        startActivity(intent);
    }
}