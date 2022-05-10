package com.example.a01_19478061_huynhtuananh.model;

import java.io.Serializable;

public class User implements Serializable {
    private String uid;
    private String email;
    private String name;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String uid, String email) {
        this.email = email;
        this.uid = uid;
    }

    public User(String uid, String email, String name) {
        this.uid = uid;
        this.email = email;
        this.name = name;
    }
}
