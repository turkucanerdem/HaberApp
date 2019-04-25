package com.example.haberapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HaberAdapter extends RecyclerView.Adapter<HaberAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView haberBaslik;
        public TextView goruntulenmeSayisi;
        public TextView haberTuru;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            haberBaslik = (TextView) itemView.findViewById(R.id.haberBaslik);
            goruntulenmeSayisi = (TextView) itemView.findViewById(R.id.goruntulenmeSayisi);
            haberTuru = (TextView) itemView.findViewById(R.id.haberTuru);

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
        ViewHolder viewHolder = new ViewHolder(contactView);
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
