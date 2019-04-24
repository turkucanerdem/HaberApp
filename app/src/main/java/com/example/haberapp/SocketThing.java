package com.example.haberapp;
import android.app.Application;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;


public class SocketThing extends Application {

    private Socket mSocket;
    {
        try {

            mSocket = IO.socket("http://192.168.1.25:3000");
            System.out.println(mSocket.id());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }

}
