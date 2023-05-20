package com.tmannapps.truck_app_blank.model;

public class User {
    private int user_id, phone;
    private String username, password, fullname;


    public User(String username, String password, String fullname, int phone) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
    }

    public User() {

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
