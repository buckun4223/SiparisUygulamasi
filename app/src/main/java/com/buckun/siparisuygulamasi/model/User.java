package com.buckun.siparisuygulamasi.model;

import android.text.TextUtils;
import android.util.Patterns;
public class User implements IUser {

    private String userName;
    private String password;
    private String age;
    private String key;

    public User(String userName, String password, String age, String key) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.key = key;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String getEmail() {
        return userName;
    }

    @Override
    public String getPassWord() {
        return password;
    }

    @Override
    public String getAge() {
        return age;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public boolean isValidDate() {
        return !TextUtils.isEmpty(getEmail()) &&
                Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches() &&
                getPassWord().length() >= 6 ;
    }
}
