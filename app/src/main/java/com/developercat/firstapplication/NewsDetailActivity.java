package com.developercat.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    TextView newsTitle, newsText, newsSubTitle, newsTime, newsPublisher, newsImageDescription;
    ImageView newsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        newsImage = findViewById(R.id.newsImage);
        newsTitle = findViewById(R.id.newsTitle);
        newsText = findViewById(R.id.newsText);
        newsSubTitle = findViewById(R.id.newsSubTitle);
        newsTime = findViewById(R.id.newsTime);
        newsPublisher = findViewById(R.id.newsPublisher);
        newsImageDescription = findViewById(R.id.newsImageDescription);

        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("id", 0);
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            NewsItem item = databaseHelper.getNewsItem(id);

            Picasso.get().load(item.getImage()).into(newsImage);
            newsTitle.setText(item.getTitle());
            newsText.setText(item.getText());
            newsSubTitle.setText(item.getSubTitle());
            newsTime.setText(item.getTime());
            newsPublisher.setText(item.getPublisher());
            newsImageDescription.setText(item.getImageDescription());
        }
    }
}
