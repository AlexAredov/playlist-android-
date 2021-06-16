package com.example.playlist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playlist.Network.Network;

public class MI extends AppCompatActivity {
    String user = "aaa";
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi);
        Intent intent = getIntent();
        text = (TextView) findViewById(R.id.textView199);
        if (intent.hasExtra("name1")) {
            if (intent.getExtras().getString("name1") != null){
                user = intent.getExtras().getString("name1");
                text.setText(user);
            }
            else{
                user = "nuuul";
                text.setText("nuuul");
            }

        }
        else{
            user = "nane";
            text.setText("nane");
        }

        String[] mi = {"Ukelele", "Bangos", "Harmonica", "Bass Guitar", "Drums", "Piano", "Xylophone", "Kalimba", "Clarinet", "Trombone", "Guitar", "Saxophone", "Steel Tongue Drum", "Flute", "Theremin", "Harp", "Cello", "Magix Music Maker", "Image-Line FL Studio 20", "Native Instruments Maschine 2 MK3", "Propellerheads Reason+"};


        Network network = new Network();
        network.miget(
                user,
                new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        String[] str = msg.obj.toString().split(", ");

                        for (int i = 0; i < str.length; i++){
                            String name = mi[Integer.parseInt(str[i]) - 1];
                            //text.setText(name);
                            if (name.equals("Ukelele")){
                                ((CheckBox) findViewById(R.id.Uculele)).setChecked(true);
                            }
                            if (name.equals("Bangos")){
                                ((CheckBox) findViewById(R.id.Bangos)).setChecked(true);
                            }
                            if (name.equals("Harmonica")){
                                ((CheckBox) findViewById(R.id.Harmonica)).setChecked(true);
                            }
                            if (name.equals("Bass Guitar")){
                                ((CheckBox) findViewById(R.id.Bass_Guitar)).setChecked(true);
                            }
                            if (name.equals("Drums")){
                                ((CheckBox) findViewById(R.id.Drums)).setChecked(true);
                            }
                            if (name.equals("Piano")){
                                ((CheckBox) findViewById(R.id.Piano)).setChecked(true);
                            }
                            if (name.equals("Xylophone")){
                                ((CheckBox) findViewById(R.id.Xylophone)).setChecked(true);
                            }
                            if (name.equals("Kalimba")){
                                ((CheckBox) findViewById(R.id.Kalimba)).setChecked(true);
                            }
                            if (name.equals("Clarinet")){
                                ((CheckBox) findViewById(R.id.Clarinet)).setChecked(true);
                            }
                            if (name.equals("Trombone")){
                                ((CheckBox) findViewById(R.id.Trombone)).setChecked(true);
                            }
                            if (name.equals("Guitar")){
                                ((CheckBox) findViewById(R.id.Guitar)).setChecked(true);
                            }
                            if (name.equals("Saxophone")){
                                ((CheckBox) findViewById(R.id.Saxophone)).setChecked(true);
                            }
                            if (name.equals("Steel Tongue Drum")){
                                ((CheckBox) findViewById(R.id.Steel_Tongue_Drum)).setChecked(true);
                            }
                            if (name.equals("Flute")){
                                ((CheckBox) findViewById(R.id.Flute)).setChecked(true);
                            }
                            if (name.equals("Theremin")){
                                ((CheckBox) findViewById(R.id.Theremin)).setChecked(true);
                            }
                            if (name.equals("Harp")){
                                ((CheckBox) findViewById(R.id.Harp)).setChecked(true);
                            }
                            if (name.equals("Cello")){
                                ((CheckBox) findViewById(R.id.Cello)).setChecked(true);
                            }
                            if (name.equals("Magix Music Maker")){
                                ((CheckBox) findViewById(R.id.Magix_Music_Maker)).setChecked(true);
                            }
                            if (name.equals("Image-Line FL Studio 20")){
                                ((CheckBox) findViewById(R.id.Image_Line_FL_Studio_20)).setChecked(true);
                            }
                            if (name.equals("Native Instruments Maschine 2 MK3")){
                                ((CheckBox) findViewById(R.id.Native_Instruments_Maschine_2_MK3)).setChecked(true);
                            }
                            if (name.equals("Propellerheads Reason+")){
                                ((CheckBox) findViewById(R.id.Propellerheads_Reason)).setChecked(true);
                            }
                        }
                    }
                }
        );


    }

    public void Uculele(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "1", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 1", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Bangos(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "2", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 2", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Harmonica(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "3", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 3", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Bass_Guitar(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "4", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 4", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Drums(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "5", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 5", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Piano(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "6", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 6", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Xylophone(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "7", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 7", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Kalimba(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "8", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 8", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Clarinet(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "9", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 9", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Trombone(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "10", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 10", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Guitar(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "11", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 11", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Saxophone(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "12", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 12", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Steel_Tongue_Drum(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "13", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 13", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Flute(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "14", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 14", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Theremin(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "15", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 15", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Harp(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "16", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 16", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Cello(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "17", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 17", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Magix_Music_Maker(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "18", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 18", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Image_Line_FL_Studio_20(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "19", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 19", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Native_Instruments_Maschine_2_MK3(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "20", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 20", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void Propellerheads_Reason(View view){
        boolean checked = ((CheckBox) view).isChecked();
        Network network = new Network();
        if (checked) {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            network.mipost(user, msg.obj.toString() + ", " + "21", new Handler());

                        }
                    }
            );
        } else {
            network.miget(
                    user,
                    new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if (msg.obj != null) {
                                network.mipost(user, msg.obj.toString().replace(", 3", ""), new Handler());
                            }
                        }
                    }
            );
        }
    }

    public void back(View view) {
        Intent intent = new Intent(MI.this, After.class);
        startActivity(intent);
    }

    public  void MI(View view){
        Intent intent = new Intent(MI.this, Serves.class);
        intent.putExtra("name", user);
        startActivity(intent);
    }
}