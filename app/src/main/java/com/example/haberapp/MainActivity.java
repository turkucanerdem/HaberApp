package com.example.haberapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class MainActivity extends AppCompatActivity {

    Button button;
    CheckBox ekonomi;
    CheckBox gundem;
    CheckBox spor;
    CheckBox egitim;
    Socket mSocket;




    private void attemptSendHaberAl(boolean spor, boolean gundem, boolean egitim, boolean ekonomi) {

        JSONObject obj = new JSONObject();
        ArrayList<String> list = new ArrayList<String>();

        try {
            if(spor)
                list.add("spor");

            if(gundem)
                list.add("gundem");

            if(egitim)
                list.add("egitim");

            if(ekonomi)
                list.add("ekonomi");

            if(!((spor||gundem)||(egitim||ekonomi))){
                list.add("spor");
                list.add("gundem");
                list.add("egitim");
                list.add("ekonomi");
            }

            obj.put("haberturu",new JSONArray(list));

        }catch (JSONException e) { }

        System.out.println(mSocket.toString());
        mSocket.emit("haberAl", obj);
    }





    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Toast.makeText(getApplicationContext(), "Unable to connect to NodeJS server", Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    private Emitter.Listener getMesaj = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONArray dataArray = (JSONArray) args[0];
                    try {
                        Haber.createListForViewing(dataArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    System.out.println(dataArray.toString());
                    startActivity(new Intent(MainActivity.this, UserListActivity.class));
                    //Toast.makeText(getApplicationContext(), "Unable to connect to NodeJS server", Toast.LENGTH_LONG).show();
                }
            });
        }
    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SocketThing app = new SocketThing();
        mSocket = app.getSocket();
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.on("haberGonder",getMesaj);
        mSocket.connect();


        gundem = (CheckBox) findViewById(R.id.checkBox);
        spor = (CheckBox) findViewById(R.id.checkBox2);
        egitim = (CheckBox) findViewById(R.id.checkBox3);
        ekonomi = (CheckBox) findViewById(R.id.checkBox4);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                boolean sporisChecked = spor.isChecked();
                boolean gundemisChecked = gundem.isChecked();
                boolean egitimisChecked = egitim.isChecked();
                boolean ekonomiisChecked = ekonomi.isChecked();




                // Sending an object
                //attemptSendLike(1);
                attemptSendHaberAl(sporisChecked,gundemisChecked,egitimisChecked,ekonomiisChecked);




            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
    }




}
