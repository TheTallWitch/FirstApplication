package com.developercat.firstapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private List<NewsItem> mainItems;
    private OnNewsClickListener listener;

    public NewsAdapter(Activity activity, List<NewsItem> mainItems, OnNewsClickListener listener) {
        this.activity = activity;
        this.mainItems = mainItems;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_row, viewGroup, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int i) {
        NewsViewHolder viewHolder = (NewsViewHolder) holder;
        NewsItem item = mainItems.get(i);
        Picasso.get().load(item.image).into(viewHolder.image);
        viewHolder.title.setText(item.title);
        viewHolder.text.setText(item.text);
        viewHolder.time.setText(item.time);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainItems.size();
    }

    public interface OnNewsClickListener {
        public void onItemClick(int position);
    }
}
