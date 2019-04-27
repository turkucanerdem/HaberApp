package com.example.haberapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.socket.client.Socket;

public class HaberAdapter extends RecyclerView.Adapter<HaberAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private void attemptView(int idhaber){
            JSONObject obj = new JSONObject();
            try {
                obj.put("idhaber",idhaber);
            }catch (JSONException e) { }
            System.out.println(mSocket.toString());
            mSocket.emit("goruntule", obj);
        }


        public TextView haberBaslik;
        public TextView goruntulenmeSayisi;
        public TextView haberTuru;
        public Context context;
        public Socket mSocket;

        public ViewHolder(Context context, View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            haberBaslik = (TextView) itemView.findViewById(R.id.haberBaslik);
            goruntulenmeSayisi = (TextView) itemView.findViewById(R.id.goruntulenmeSayisi);
            haberTuru = (TextView) itemView.findViewById(R.id.haberTuru);
            this.context = context;

            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Haber haber = mHabers.get(position);
                // We can access the data within the views
                Toast.makeText(context, haberBaslik.getText(), Toast.LENGTH_SHORT).show();

                SocketThing app = new SocketThing();
                mSocket = app.getSocket();
                mSocket.connect();
                attemptView(haber.idhaber);



                Intent intent = new Intent(context, HaberOkuActivity.class);
                intent.putExtra("haber", haber);
                context.startActivity(intent);

            }
        }

    }

    private List<Haber> mHabers;

    // Pass in the contact array into the constructor
    public HaberAdapter(List<Haber> contacts) {
        mHabers = contacts;
    }

    @Override
    public HaberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.haber, parent, false);

        // Return a new holder instance
        //ViewHolder viewHolder = new ViewHolder(contactView); 27sinde commente alındı
        ViewHolder viewHolder = new ViewHolder(context,contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(HaberAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Haber contact = mHabers.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.haberBaslik;
        textView.setText(contact.getHaberbaslik());
        TextView textView2 = viewHolder.goruntulenmeSayisi;
        textView2.setText(new Integer(contact.getGoruntulenmesayisi()).toString());
        TextView textView3 = viewHolder.haberTuru;
        textView3.setText(contact.getHaberturu());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mHabers.size();
    }

}
