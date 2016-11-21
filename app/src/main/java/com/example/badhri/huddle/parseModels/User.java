package com.example.badhri.huddle.parseModels;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by badhri on 11/12/16.
 */

@ParseClassName("User")
public class User extends ParseObject {
    public static final String USERNAME_KEY = "username";
    public static final String EMAIL_KEY = "email";
    public static final String EMAIL_VERIFIED__KEY = "emailVerified";
    public static final String PHONE_NUMBER_KEY = "phoneNumber";
    public static final String STATUS_KEY = "phoneNumber";


    public String getUsername() {
        return getString(USERNAME_KEY);
    }

    public void setUsername(String username)  {
        put(USERNAME_KEY, username);
    }
    public String getEmail() {
        return getString(EMAIL_KEY);
    }

    public void setEmail(String email) {
        put(EMAIL_KEY, email);
    }

    public boolean getEmailVerified() {
        return getBoolean(EMAIL_VERIFIED__KEY);
    }

    public void setEmailVerified(Boolean emailVerified)  {
        put(EMAIL_VERIFIED__KEY, emailVerified);
    }

    public long getPhoneNumber() {
        return getLong(PHONE_NUMBER_KEY);
    }

    public void setPhoneNumber(Long number)  {
        put(PHONE_NUMBER_KEY, number);
    }

    public String getStatus() {
        return getString(STATUS_KEY);
    }

    public void setStatus(String status)  {
        put(STATUS_KEY, status);
    }

}

