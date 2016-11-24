package com.artkodec.uv_control.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.artkodec.uv_control.model.UserEntity;
import com.google.gson.Gson;

import org.json.JSONException;


public class SessionManager {

    public static final String PREFERENCE_NAME = "check_in";
    public static int PRIVATE_MODE = 0;

    /**
        USUARIO DATA SESSION - JSON
     */
    public static final String USER_TOKEN = "user_token";
    public static final String USER_JSON = "user_json";
    public static final String IS_LOGIN = "user_login";

    /**
        Last location
     */
    public static final String LOCATION = "location";


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public boolean isLogin()  {
        return preferences.getBoolean(IS_LOGIN, false);
    }


    public void openSession(String token, UserEntity userEntity) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(USER_TOKEN, token);
        if(userEntity!=null){
            Gson gson = new Gson();
            String user= gson.toJson(userEntity);
            editor.putString(USER_JSON, user);
        }

        editor.commit();

    }




    public void setUser(UserEntity user_dto) {

        if(user_dto!=null){
            Gson gson = new Gson();
            String user= gson.toJson(user_dto);
            editor.putString(USER_JSON, user);
        }

        editor.commit();

    }

    public void closeSession() {
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(USER_TOKEN, null);
        editor.putString(USER_JSON, null);
        editor.commit();

    }



    public UserEntity getUserEntity() throws JSONException {

        if (isLogin()) {
            String userData = preferences.getString(USER_JSON, null);
            UserEntity user_dto = new Gson().fromJson(userData, UserEntity.class);
            return user_dto;
        } else {
            return null;
        }


    }


    public String getUserToken() {
        if (isLogin()) {
            return preferences.getString(USER_TOKEN, "");
        } else {
            return "";
        }
    }




}