package com.example.expresso.sapthagiri.yogirproduct.GetterSetter.CustomerHomePage;

public class HistoryGetterSetter {
    String request_title, request_description;

    public String getRequest_title() {
        return request_title;
    }

    public void setRequest_title(String request_title) {
        this.request_title = request_title;
    }

    public String getRequest_description() {
        return request_description;
    }

    public void setRequest_description(String request_description) {
        this.request_description = request_description;
    }

    public HistoryGetterSetter(String request_title, String request_description) {
        this.request_title = request_title;
        this.request_description = request_description;
    }
}
