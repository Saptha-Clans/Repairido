package com.example.expresso.sapthagiri.yogirproduct.GetterSetter.ServiceRequest;

public class ServiceAddressGetterSetter {
    private String contractor_name;
    private String address_line_1;
    private String getAddress_line_2;
    private String city, state, pincode;

    public ServiceAddressGetterSetter(
            String contractor_name,
            String address_line_1,
            String getAddress_line_2,
            String city,
            String state,
            String pincode
    ) {
        this.contractor_name = contractor_name;
        this.address_line_1 = address_line_1;
        this.getAddress_line_2 = getAddress_line_2;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getContractor_name() {
        return contractor_name;
    }

    public void setContractor_name(String contractor_name) {
        this.contractor_name = contractor_name;
    }

    public String getAddress_line_1() {
        return address_line_1;
    }

    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public String getGetAddress_line_2() {
        return getAddress_line_2;
    }

    public void setGetAddress_line_2(String getAddress_line_2) {
        this.getAddress_line_2 = getAddress_line_2;
    }
}
