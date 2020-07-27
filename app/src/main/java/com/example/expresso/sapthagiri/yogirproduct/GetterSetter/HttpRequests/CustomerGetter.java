package com.example.expresso.sapthagiri.yogirproduct.GetterSetter.HttpRequests;

public class CustomerGetter {

    private String first_name, last_name, user_name, email, password, phone_number;

    public CustomerGetter(String first_name, String last_name, String email, String password, String phone_number, String user_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
