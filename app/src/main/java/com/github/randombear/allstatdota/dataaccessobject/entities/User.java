package com.github.randombear.allstatdota.dataaccessobject.entities;

/**
 * =================================
 * Created by randomBEAR on 31/10/2017.
 * =================================
 */

public class User {

    private String name;
    private boolean online;
    private String date;

    public User() {}

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
