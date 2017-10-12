package com.example.nikhil.studentregistrationsystem.databaseModel;

/**
 * Created by nikhil on 5/9/17.
 */

public class DepartmentDetailsRef {
    private String dep_id,courses,dep_name;

    public DepartmentDetailsRef(String dep_id, String courses, String dep_name) {
        this.dep_id = dep_id;
        this.courses = courses;
        this.dep_name = dep_name;
    }

    public String getDep_id() {
        return dep_id;
    }

    public String getCourses() {
        return courses;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_id(String dep_id) {
        this.dep_id = dep_id;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
}