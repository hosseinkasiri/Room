package com.example.roomdatabase.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomdatabase.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();
    @Query("SELECT * FROM user WHERE mId IN (:userIds)")
    List<User> loadAllById(Long[] userIds);
    @Query("SELECT * FROM user WHERE mFirstName LIKE :first AND " +
            "mLastName LIKE :last LIMIT 1")
    User findByName(String first, String last);
    @Insert
    void insertAll(User... users);
    @Delete
    void delete(User user);
}
