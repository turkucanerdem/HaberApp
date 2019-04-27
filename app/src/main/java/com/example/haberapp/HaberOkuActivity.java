package com.example.haberapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;

public class HaberOkuActivity extends AppCompatActivity {

    FloatingActionButton like;
    FloatingActionButton dislike;
    Haber haber;
    Socket mSocket;
    TextView baslik;
    TextView tarih;
    TextView begenme;
    TextView begenmeme;
    TextView tur;
    TextView goruntulenme;
    TextView icerik;

    private void attemptSendLike(int idhaber){
        JSONObject obj = new JSONObject();
        try {
            obj.put("idhaber",idhaber);
        }catch (JSONException e) { }
        System.out.println(mSocket.toString());
        mSocket.emit("begen", obj);
    }

    private void attemptSendDislike(int idhaber){
        JSONObject obj = new JSONObject();
        try {
            obj.put("idhaber",idhaber);
        }catch (JSONException e) { }
        System.out.println(mSocket.toString());
        mSocket.emit("begenme", obj);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haber_oku);

        like = (FloatingActionButton) findViewById(R.id.likeButton);
        dislike = (FloatingActionButton) findViewById(R.id.dislikeButton);

        haber = (Haber) getIntent().getSerializableExtra("haber");

        SocketThing app = new SocketThing();
        mSocket = app.getSocket();
        mSocket.connect();

        baslik = (TextView) findViewById(R.id.baslik);
        tarih = (TextView) findViewById(R.id.tarih);
        begenme = (TextView) findViewById(R.id.begenme);
        begenmeme = (TextView) findViewById(R.id.begenmeme);
        tur = (TextView) findViewById(R.id.tur);
        goruntulenme = (TextView) findViewById(R.id.goruntulenme);
        icerik = (TextView) findViewById(R.id.icerik);

        baslik.setText(haber.getHaberbaslik());
        tur.setText("Tür:\n "+haber.getHaberturu());
        begenme.setText("Like:\n " + String.valueOf(haber.getBegenmesayisi()));
        begenmeme.setText("Dlike:\n " + String.valueOf(haber.getBegenmemesayisi()));
        goruntulenme.setText("View:\n " + String.valueOf(haber.getGoruntulenmesayisi()));
        tarih.setText("Tarihi:\n " + haber.getYayınlanmatarihi());
        icerik.setText(haber.getHabericerik());


        like.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Sending an object
                //attemptSendLike(1);
                attemptSendLike(haber.idhaber);

            }
        });

        dislike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Sending an object
                //attemptSendLike(1);
                attemptSendDislike(haber.idhaber);

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
    }
}
