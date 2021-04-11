package com.example.androidtermproject;

import androidx.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String userName;
    public String userID;
    public String userPassword;
    public String userEmail;

    public User() {

    }

    public User(String userName, String userID, String userPassword, String userEmail) {
        this.userName = userName;
        this.userID = userID;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public String getuserName()
    {
        return userName;
    }

    public void setuserName()
    {
        this.userName = userName;
    }

    public String getuserID()
    {
        return userID;
    }

    public void setuserID()
    {
        this.userID = userID;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword()
    {
        this.userPassword = userPassword;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail()
    {
        this.userEmail = userEmail;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" + "userName='" + userName + '\'' + ", userID='" + userID + '\'' +
                ", userPassword='" + userPassword + '\'' + ", userEmail='" + userEmail + '\'' + '}';
    }
}
