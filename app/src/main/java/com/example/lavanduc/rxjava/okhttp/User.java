package com.example.lavanduc.rxjava.okhttp;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    private String user;
    @SerializedName("password")
    private String pass;
    @SerializedName("email")
    private String emai;

    public User() {
    }


    public User(String user, String pass, String emai) {
        this.user = user;
        this.pass = pass;
        this.emai = emai;
    }

    public String getUser() {
        return user;
    }

    public User setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public User setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public String getEmai() {
        return emai;
    }

    public User setEmai(String emai) {
        this.emai = emai;
        return this;
    }
}
