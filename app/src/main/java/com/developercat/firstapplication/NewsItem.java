package com.developercat.firstapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Entity (tableName = "news_table")
public class NewsItem {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "subTitle")
    public String subTitle;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "imageDescription")
    public String imageDescription;

    @ColumnInfo(name = "time")
    public String time;

    @ColumnInfo(name = "publisher")
    public String publisher;

    public NewsItem() {

    }

    public NewsItem(int id, String title, String subTitle, String text, String image, String imageDescription, String time, String publisher) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.text = text;
        this.image = image;
        this.imageDescription = imageDescription;
        this.time = time;
        this.publisher = publisher;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getSubTitle() { return subTitle; }
    public String getText() { return text; }
    public String getImage() { return image; }
    public String getImageDescription() { return imageDescription; }
    public String getTime() { return time; }
    public String getPublisher() { return publisher; }

    public void setTitle(String title) { this.title = title; }
    public void setSubTitle(String subTitle) { this.subTitle = subTitle; }
    public void setText(String text) { this.text = text; }
    public void setImage(String image) { this.image = image; }
    public void setImageDescription(String imageDescription) { this.imageDescription = imageDescription; }
    public void setTime(String time) { this.time = time; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    @Dao
    public interface NewsDao {
        @Query("SELECT * FROM news_table")
        List<NewsItem> getAll();

        @Query("SELECT * FROM news_table WHERE id = :newsId LIMIT 1")
        NewsItem getNewsItem(int newsId);

        @Insert
        void insertNews(NewsItem newsItem);

        @Delete
        void deleteNews(NewsItem newsItem);

        @Update
        void updateNews(NewsItem newsItem);
    }
}
