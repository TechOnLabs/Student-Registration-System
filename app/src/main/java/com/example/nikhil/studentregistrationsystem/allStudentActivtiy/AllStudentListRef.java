package com.example.nikhil.studentregistrationsystem.allStudentActivtiy;

/**
 * Created by Nikhil on 2/10/17.
 */

public class AllStudentListRef {
    String reg_num, name, mobile_number, mobile_number2, email, address, dept_name;

    public AllStudentListRef(String reg_num, String name, String mobile_number, String mobile_number2,
                             String email, String address, String dept_name) {
        this.reg_num = reg_num;
        this.name = name;
        this.mobile_number = mobile_number;
        this.mobile_number2 = mobile_number2;
        this.email = email;
        this.address = address;
        this.dept_name = dept_name;
    }

    public String getReg_num() {
        return reg_num;
    }

    public void setReg_num(String reg_num) {
        this.reg_num = reg_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getMobile_number2() {
        return mobile_number2;
    }

    public void setMobile_number2(String mobile_number2) {
        this.mobile_number2 = mobile_number2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
}
