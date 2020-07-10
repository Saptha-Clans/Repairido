package com.example.expresso.sapthagiri.yogirproduct;

public class DashboardGetterSetter {
    String contractor_name;
    String contractor_category;
    String contractor_specialize;
    Float rating_bar;
    int contractor_image;

    public DashboardGetterSetter(String contractor_name, String contractor_category, String contractor_specialize, int contractor_image, Float rating_bar) {
        this.contractor_name = contractor_name;
        this.contractor_category = contractor_category;
        this.contractor_specialize = contractor_specialize;
        this.contractor_image = contractor_image;
        this.rating_bar = rating_bar;
    }

    public int getContractor_image() {
        return contractor_image;
    }

    public void setContractor_image(int contractor_image) {
        this.contractor_image = contractor_image;
    }

    public String getContractor_name() {
        return contractor_name;
    }

    public void setContractor_name(String contractor_name) {
        this.contractor_name = contractor_name;
    }

    public String getContractor_category() {
        return contractor_category;
    }

    public void setContractor_category(String contractor_category) {
        this.contractor_category = contractor_category;
    }

    public String getContractor_specialize() {
        return contractor_specialize;
    }

    public void setContractor_specialize(String contractor_specialize) {
        this.contractor_specialize = contractor_specialize;
    }

    public Float getRatingBar() {
        return rating_bar;
    }

    public void setRatingBar(Float rating_bar) {
        this.rating_bar = rating_bar;
    }
}
