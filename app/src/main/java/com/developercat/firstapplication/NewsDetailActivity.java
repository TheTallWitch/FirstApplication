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

    public static AppDatabase appDatabase;

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

        appDatabase = AppDatabase.getAppDatabase(this);

        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("id", 0);
            NewsItem item = appDatabase.newsDao().getNewsItem(id);

            Picasso.get().load(item.image).into(newsImage);
            newsTitle.setText(item.title);
            newsText.setText(item.text);
            newsSubTitle.setText(item.subTitle);
            newsTime.setText(item.time);
            newsPublisher.setText(item.publisher);
            newsImageDescription.setText(item.imageDescription);
        }
    }
}
