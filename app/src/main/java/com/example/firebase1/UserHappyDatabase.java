package com.example.firebase1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.firebase1.entity.UserHappy;

@Database(entities = {UserHappy.class}, version = 1, exportSchema = false)
public abstract class UserHappyDatabase extends RoomDatabase {
    public abstract UserHappyDAO getItemDAO();
}