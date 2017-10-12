package com.example.nikhil.studentregistrationsystem.databaseModel;

/**
 * Created by nikhil on 5/9/17.
 */

public class StudentDetailsRef {
    private String registration_number,name,mobile_number,address,email,secondary_number="";

    public StudentDetailsRef(String registration_number, String name, String mobile_number, String address, String email, String secondary_number) {
        this.registration_number = registration_number;
        this.name = name;
        this.mobile_number = mobile_number;
        this.address = address;
        this.email = email;
        this.secondary_number = secondary_number;
    }

    public StudentDetailsRef(String registration_number, String name, String mobile_number, String address, String email) {
        this.registration_number = registration_number;
        this.name = name;
        this.mobile_number = mobile_number;
        this.address = address;
        this.email = email;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public String getName() {
        return name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getSecondary_number() {
        return secondary_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSecondary_number(String secondary_number) {
        this.secondary_number = secondary_number;
    }
}
