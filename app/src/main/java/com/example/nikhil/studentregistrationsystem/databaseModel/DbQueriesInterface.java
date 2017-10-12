package com.example.nikhil.studentregistrationsystem.databaseModel;

import com.example.nikhil.studentregistrationsystem.allStudentActivtiy.AllStudentListRef;

import java.util.ArrayList;

/**
 * Created by nikhil on 5/9/17.
 */

public interface DbQueriesInterface {
    void truncateDb(String depId);

    void addStudentDetails(StudentDetailsRef studentDetailsRef, String depId);

    void updateStudentDetails(StudentDetailsRef studentDetailsRef, String depId);

    ArrayList<StudentDetailsRef> getStudentList(String depName);

    DepartmentDetailsRef getDepartmentsDetails(String departmentName);

    ArrayList<String> getDepartmentsNamesList();

    ArrayList<DepartmentDetailsRef> getDepartmentDetailsList();

    void deleteOneEntry(String reg_num);

    ArrayList<AllStudentListRef> getAllStudentList();

    void truncateAllTables();

}
