package com.example.khulood.nmlibrary;

public class User {
    public String id;
    public String sid;
    public String email;
    public String password;
    public String passhint;

    public User(String id, String email, String sid, String password, String passhint) {
        this.id = id;
        this.email = email;
        this.sid = sid;
        this.password = password;
        this.passhint = passhint;
    }


}