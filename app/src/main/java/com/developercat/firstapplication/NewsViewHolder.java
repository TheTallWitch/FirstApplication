package com.developercat.firstapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView title;
    public TextView text;
    public TextView time;

    public NewsViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.newsImage);
        title = itemView.findViewById(R.id.newsTitle);
        text = itemView.findViewById(R.id.newsText);
        time = itemView.findViewById(R.id.newsTime);
    }
}
