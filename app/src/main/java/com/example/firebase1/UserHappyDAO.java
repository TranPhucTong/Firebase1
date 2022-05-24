package com.example.firebase1;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.firebase1.entity.UserHappy;

import java.util.List;

@Dao
public interface UserHappyDAO {
    @Query("select * from user_happy")
    List<UserHappy> getUsers();

    @Insert
    void insert(UserHappy... users);

    @Update
    void update(UserHappy... users);

    @Query("delete from user_happy")
    void deleteAll();

    @Query("select * from user_happy where email = :email")
    UserHappy getUserById(String email);
    
}
