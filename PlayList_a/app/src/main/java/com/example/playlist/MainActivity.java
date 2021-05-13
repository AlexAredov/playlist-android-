package com.example.playlist;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playlist.Model.Person;
import com.example.playlist.Network.Network;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    Cursor userCursor;
    EditText name;
    EditText password;
    TextView text;
    SQLiteDatabase db;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER)");
        Cursor query = db.rawQuery("SELECT * FROM users WHERE age = 1;", null);
        if(query.moveToFirst()){
            String name = query.getString(0);
            Intent intent1 = new Intent(MainActivity.this, After.class);
            intent1.putExtra("username", name);
            startActivity(intent1);
        }
        else{
            setContentView(R.layout.activity_main);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            name = findViewById(R.id.editTextTextPersonName7);
            password = findViewById(R.id.editTextTextPersonName4);
            text = findViewById(R.id.textView);
            Network network = new Network();
            network.getUsers(new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg){
                    super.handleMessage(msg);
                    if (msg.obj != null) {
                        //ttt.setText(msg.obj.toString());
                    }
                }
            });
        }
    }

    private static MessageDigest md;

    public static String cryptWithMD5(String pass){
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() ==  MotionEvent.ACTION_DOWN) hideKeyboard();
        return super.dispatchTouchEvent(ev);
    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText et = findViewById(R.id.editTextTextPersonName7);
        EditText et1 = findViewById(R.id.editTextTextPersonName4);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        et.clearFocus();
        et1.clearFocus();
    }
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, RegistrActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, v, "robot");
        startActivity(intent, options.toBundle());
    }
    public void clickk(View v){
        pass = password.getText().toString();
        pass = cryptWithMD5(pass);
        Network network = new Network();
        if (!name.getText().toString().isEmpty()  && !password.getText().toString().isEmpty()) {
            network.check(
                    new Person(
                            name.getText().toString(),
                            password.getText().toString()
                    ),
                    new Handler(){
                        @Override
                        public void handleMessage(@NonNull Message msg){
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                if (msg.obj.toString().equals(pass)){
                                    db.execSQL("INSERT INTO users VALUES ('" + name.getText().toString() +"', 1);");
                                    Intent intent1 = new Intent(MainActivity.this, After.class);
                                    intent1.putExtra("username", name.getText().toString());
                                    startActivity(intent1);
                                }
                                else{
                                    text.setText("Error");
                                }
                            }
                            else{
                                text.setText("Error");
                            }
                        }
                    }
            );
        }
    }
}