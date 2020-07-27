package com.example.expresso.sapthagiri.yogirproduct.GetterSetter.HttpRequests;

public class LoginGetter {

    String token;
    String user_id;

    public LoginGetter(String token, String user_id, String email) {
        this.token = token;
        this.user_id = user_id;
        this.email = email;
    }

    String email;

    public String getToken() {
        return token;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }
}
