package com.example.yellowochre;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;

public class SessionManager {
    SharedPreferences shp;
    SharedPreferences.Editor edi;
    public static final String prefName = "userpref";

    public SessionManager(Context context){
        shp = context.getSharedPreferences(prefName,0);
        edi = shp.edit();
        edi.commit();
    }
    public void setLogin(boolean login){
        edi.putBoolean("key_login", login);
        edi.commit();
    }
    public boolean getLogin(){
        return shp.getBoolean("key_Login", false);
    }

    public void setUsername(String username){
        edi.putString("key_username", username);
        edi.commit();
    }
    public String getUsername(){
        return shp.getString("key_username", "");
    }

    public void setScore(int score){
        edi.putInt("key_score", score);
        edi.commit();
    }
    public int getScore(){
        return shp.getInt("key_score", 0);
    }

    public void setTime(String userdate){
        edi.putString("key_timestamp", userdate);
        edi.commit();
    }
    public String getTime(){
        return shp.getString("key_timestamp", "");
    }
}
