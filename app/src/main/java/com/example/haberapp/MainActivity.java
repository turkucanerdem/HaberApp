package com.example.haberapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

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




    private void attemptSend() {

        JSONObject obj = new JSONObject();
        try {
            obj.put("idhaber", 1);
            System.out.println("LLLLLL");
        }catch (JSONException e) { }

        System.out.println(mSocket.toString());
        mSocket.emit("join", obj);
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






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SocketThing app = new SocketThing();
        mSocket = app.getSocket();
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
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
                attemptSend();
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
