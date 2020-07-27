package com.example.expresso.sapthagiri.yogirproduct.GetterSetter.ServiceRequest;

public class ServiceGetterSetter {
    private int image;
    private String service;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public ServiceGetterSetter(int image, String service) {
        this.image = image;
        this.service = service;
    }
}
