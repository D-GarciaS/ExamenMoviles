package com.example.damiangarcia.tareasettings.Beans;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    private String username;
    private String password;
    private boolean isLogued;

    public User getUser(Context context) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("com.iteso.tarea2.CACAHUATE",
                        Context.MODE_PRIVATE);
        User user = new User();
        user.setUsername(sharedPreferences.getString("USER",null));
        user.setPassword(sharedPreferences.getString("PDD", null));
        user.setLogued(sharedPreferences.getBoolean("LOGGED",false));
        return user;
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

    public boolean isLogued() {
        return isLogued;
    }

    public void setLogued(boolean logued) {
        isLogued = logued;
    }

    public void savePreferences(Context context){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("com.iteso.tarea2.CACAHUATE",
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USER",getUsername());
        editor.putString("PWD",getPassword());
        editor.putBoolean("LOGGED",true);
        editor.apply();
    }
}
