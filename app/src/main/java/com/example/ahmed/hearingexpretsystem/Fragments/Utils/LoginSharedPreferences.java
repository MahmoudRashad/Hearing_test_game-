package com.example.ahmed.hearingexpretsystem.Fragments.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by amr heider on 4/6/2017.
 */

public class LoginSharedPreferences {

    private Context context;
    private String Name, password;
    SharedPreferences.Editor editor;
    SharedPreferences settings;

    public LoginSharedPreferences(Context context) {
        this.context = context;
        settings = context.getSharedPreferences("login", 0);
        editor = settings.edit();
    }



    public String getName() {
        settings = context.getSharedPreferences("login", 0);
        return settings.getString("phoneNumber","");
    }

    public void setName(String phoneNumber) {
        this.Name = phoneNumber;
        settings = context.getSharedPreferences("login", 0);
        editor = settings.edit();
        editor.putString("Name", this.Name);
        editor.commit();
    }

    public String getPassword() {
        settings = context.getSharedPreferences("login", 0);
        return settings.getString("password","");
    }

    public void setPassword(String password) {
        this.password = password;
        settings = context.getSharedPreferences("login", 0);
        editor = settings.edit();
        editor.putString("password", this.password);
        editor.commit();
    }




    public void removeLogin(Context context) {
        settings = context.getSharedPreferences("login", 0);
        editor = settings.edit();
        editor.remove("Name");
        editor.remove("password");
        editor.commit();
    }
}
