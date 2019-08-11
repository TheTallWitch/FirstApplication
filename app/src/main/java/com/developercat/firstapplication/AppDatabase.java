package com.developercat.firstapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = { NewsItem.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    public abstract NewsItem.NewsDao newsDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "news").allowMainThreadQueries().build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}
