package com.example.firebase1.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user_happy")
public class UserHappy implements Serializable {
    @PrimaryKey
    private long id;
    private String name;
    private String email;
    private String password;
    private int happy = 0;
    private int normal = 0;
    private int unhappy = 0;

    public UserHappy() {
        //tongne
    }

    public UserHappy(long id, String name, String email, String password, int happy, int normal, int unhappy) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.happy = happy;
        this.normal = normal;
        this.unhappy = unhappy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getUnhappy() {
        return unhappy;
    }

    public void setUnhappy(int unhappy) {
        this.unhappy = unhappy;
    }
}