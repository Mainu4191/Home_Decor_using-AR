package com.example.arapp3.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    public Session(Context ctx) {

        sharedPreferences = ctx.getSharedPreferences("AppKey", 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setLoggedIn(boolean loggedIn) {
        editor.putBoolean("loggedInmode", loggedIn);
        editor.commit();
    }

    public boolean getLoggedIn() {
        return sharedPreferences.getBoolean("loggedInmode", false);
    }

    public void setUserId(String userId) {
        editor.putString("userId", userId);
        editor.commit();
    }

    public String getUserId() {
        return sharedPreferences.getString("userId", "");
    }



    public void setUserName(String userName) {
        editor.putString("userName", userName);
        editor.commit();
    }

    public String getUserName() {
        return sharedPreferences.getString("userName", "");
    }

    public void setUserEmail(String userEmail) {
        editor.putString("userEmail", userEmail);
        editor.commit();
    }

    public String getUserEmail() {
        return sharedPreferences.getString("userEmail", "");
    }

    public void setUserPhone(String userPhone) {
        editor.putString("userPhone", userPhone);
        editor.commit();
    }

    public String getUserPhone() {
        return sharedPreferences.getString("userPhone", "");
    }

    public void setUserAddress(String userAddress) {
        editor.putString("userAddress", userAddress);
        editor.commit();
    }

    public String getUserAddress() {
        return sharedPreferences.getString("userAddress", "");
    }



}
