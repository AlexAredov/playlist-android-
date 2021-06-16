package com.example.playlist;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playlist.Network.Network;

import java.util.ArrayList;
import java.util.Arrays;

public class Change extends AppCompatActivity {

    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    TextView t;
    String name;
    String userName = null;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        et = findViewById(R.id.editTextTextPersonName2);

        Bundle extras = getIntent().getExtras();

        if (extras.getString("pos") != null) {
            userName = extras.getString("pos");
            name = extras.getString("name");
        }
        else{
            name = extras.getString("name");
        }
        t = findViewById(R.id.textView8);
        listView = findViewById(R.id.listview);
        EditText editText = findViewById(R.id.editTextTextPersonName2);

        Network network = new Network();

        network.getAllSongs(
                new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg){
                        super.handleMessage(msg);
                        if (msg.obj != null) {
                            String result = msg.obj.toString().replaceAll("\\[", "");
                            result = result.replaceAll("\\]", "");
                            result = result.replaceAll("\"", "");
                            items = result.split(", ");

                            for (int i = 0; i < items.length; i++){
                                t.setText(t.getText() + "\n" + items[i]);
                            }
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
                    listItems.add("+ add new song");
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
                if (!selectedItem.equals("+ add new song")){
                    et.setText(selectedItem);
                    listItems.clear();
                }
                else {
                    network.adsng(et.getText().toString(), new Handler());
                }

            }
        });
    }

    public void searchItem(String textToSearch){
        for(String item:items){
            if(!item.contains(textToSearch)){
                listItems.remove(item);
            }
            if (textToSearch.isEmpty()){
                listItems.remove("+ add new song");
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void initList(){
        listItems=new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, listItems);
        listView.setAdapter(adapter);
    }

    public void back(View view) {
        Intent intent = new Intent(Change.this, After.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, "robot");
        startActivity(intent, options.toBundle());
    }

    public void delete(View view) throws InterruptedException {
        Intent intent = new Intent(Change.this, After.class);
        Network network = new Network();
        network.songs(
                name,
                new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg){
                        super.handleMessage(msg);
                        int k = 0;
                        String[] subStr = null;
                        String delimeter = ", ";
                        subStr = msg.obj.toString().split(delimeter);
                        for (int i = 0; i < subStr.length; i++){
                            Log.e("myTag", subStr[i]);
                            subStr[i] = subStr[i].replaceAll("\"", "");
                            if (subStr[i].equals(et.getText().toString())){
                                k = 1;
                            }
                        }
                        listItems=new ArrayList<>(Arrays.asList(subStr));
                        listItems.remove(Integer.parseInt(userName));
                        String listString = String.join(", ", listItems);
                        listString = listString.replaceAll("\"", "");
                        network.addsongs(name, listString, new Handler());
                    }
                }
        );
        intent.putExtra("username", name);
        Thread.sleep(100);
        startActivity(intent);

    }

    public void save(View view) throws InterruptedException {
        Intent intent = new Intent(Change.this, After.class);
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
                            subStr = msg.obj.toString().split(delimeter);
                            if (userName != null){
                                int k = 0;
                                for (int i = 0; i < subStr.length; i++){
                                    Log.e("myTag", subStr[i]);
                                    subStr[i] = subStr[i].replaceAll("\"", "");
                                    if (et.getText().toString().equals(subStr[i])){
                                        k = 1;
                                    }
                                }
                                if (k == 0){
                                    listItems=new ArrayList<>(Arrays.asList(subStr));
                                    listItems.set(Integer.parseInt(userName), et.getText().toString());
                                    String listString = String.join(", ", listItems);
                                    listString = listString.replaceAll("\"", "");
                                    network.addsongs(name, listString, new Handler());
                                }
                                else{
                                    Toast toast = Toast.makeText(Change.this, "This song is already in your list!", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.TOP, 0,160);
                                    toast.show();
                                }
                            }
                            else{
                                int k = 0;
                                for (int i = 0; i < subStr.length; i++){
                                    Log.e("myTag", subStr[i]);
                                    subStr[i] = subStr[i].replaceAll("\"", "");
                                    if (subStr[i].equals(et.getText().toString())){
                                        k = 1;
                                    }
                                }
                                if (k == 0){
                                    listItems=new ArrayList<>(Arrays.asList(subStr));
                                    listItems.add(et.getText().toString());
                                    String listString = String.join(", ", listItems);
                                    listString = listString.replaceAll("\"", "");
                                    network.addsongs(name, listString, new Handler());
                                }
                                else{
                                    Toast toast = Toast.makeText(Change.this, "This song is already in your list!", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.TOP, 0,160);
                                    toast.show();
                                }
                            }
                        }
                        else{
                            listItems=new ArrayList<String>();
                            listItems.add(et.getText().toString());
                            String listString = String.join(", ", listItems);
                            listString = listString.replaceAll("\"", "");
                            network.addsongs(name, listString, new Handler());
                        }
                    }
                }
        );
        intent.putExtra("username", name);
        Thread.sleep(100);
        startActivity(intent);
    }


}