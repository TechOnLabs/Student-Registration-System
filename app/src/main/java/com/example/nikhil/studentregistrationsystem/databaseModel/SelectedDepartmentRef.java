package com.example.nikhil.studentregistrationsystem.databaseModel;

/**
 * Created by nikhil on 5/9/17.
 */

public class SelectedDepartmentRef {
    private String registration_number,dep_id;

    public SelectedDepartmentRef(String registration_number, String dep_id) {
        this.registration_number = registration_number;
        this.dep_id = dep_id;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getDep_id() {
        return dep_id;
    }

    public void setDep_id(String dep_id) {
        this.dep_id = dep_id;
    }
}
