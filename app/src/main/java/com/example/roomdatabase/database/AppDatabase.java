package com.example.roomdatabase.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabase.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;
    public abstract UserDao mUserDao();

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null)
            sInstance = Room.databaseBuilder(context, AppDatabase.class, "room_database").allowMainThreadQueries().build();
        return sInstance;
    }
}
