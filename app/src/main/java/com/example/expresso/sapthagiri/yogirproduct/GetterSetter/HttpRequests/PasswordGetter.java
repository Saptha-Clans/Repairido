package com.example.expresso.sapthagiri.yogirproduct.GetterSetter.HttpRequests;


public class PasswordGetter {

    String err;

    public PasswordGetter(String err) {
        this.err = err;
    }

    public String getError_message() {
        return err;
    }

}
