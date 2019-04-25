package com.example.haberapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    ArrayList<Haber> habers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
        // Lookup the recyclerview in activity layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        RecyclerView rvContacts = findViewById(R.id.rvContacts);



        // Initialize contacts
        //habers = Haber.createContactsList(20);
        habers= Haber.gosterilecekler;
        System.out.println(habers);
        // Create adapter passing in the sample user data
        HaberAdapter adapter = new HaberAdapter(habers);
        // Attach the adapter to the recyclerview to populate items

        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }
}