package com.developercat.firstapplication;

import com.orm.SugarRecord;

public class NewsItem extends SugarRecord<NewsItem> {
    int newsId;
    String title;
    String subTitle;
    String text;
    String image;
    String imageDescription;
    String time;
    String publisher;

    public NewsItem() {

    }

    public NewsItem(int newsId, String title, String subTitle, String text, String image, String imageDescription, String time, String publisher) {
        this.newsId = newsId;
        this.title = title;
        this.subTitle = subTitle;
        this.text = text;
        this.image = image;
        this.imageDescription = imageDescription;
        this.time = time;
        this.publisher = publisher;
    }
}
