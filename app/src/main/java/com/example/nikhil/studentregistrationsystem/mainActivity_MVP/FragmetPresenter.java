package com.example.nikhil.studentregistrationsystem.mainActivity_MVP;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;

import com.example.nikhil.studentregistrationsystem.databaseModel.DBHelper;
import com.example.nikhil.studentregistrationsystem.databaseModel.DbQueries;
import com.example.nikhil.studentregistrationsystem.databaseModel.StudentDetailsRef;

import java.util.ArrayList;

/**
 * Created by nikhil on 5/9/17.
 */

public class FragmetPresenter implements MainMVP.fragmentPresenter {
    private MainMVP.fragmentView view;
    private DBHelper dbHelper;
    private DbQueries dbQueries;

    public FragmetPresenter(MainMVP.fragmentView view, Context context) {
        this.view = view;
        dbHelper = new DBHelper(context);
        dbQueries = new DbQueries(context);
    }

    @Override
    public void onStudentAdd() {

    }

    @Override
    public void deleteOneEntry(String reg_num) {
        dbQueries.open();
        dbQueries.deleteOneEntry(reg_num);
        dbQueries.close();
    }

    @Override
    public void onClickTruncateDB(String depId) {
        dbQueries.open();
        dbQueries.truncateDb(depId);
        dbQueries.close();
    }

    @Override
    public void onClickStudentAdd() {

    }

    @Override
    public Boolean updateStudentDetails(String name, String registration_number, String mobile_number, String secondary_number, String email, String address, String depId) {
        String error = null;
        if (name.equals(""))
            error = "Enter name";
        else if (registration_number.equals(""))
            error = "Enter Registration Number";
        else if (mobile_number.equals("") && mobile_number.length() != 10)
            error = "Enter Mobile Number";
        else if (!secondary_number.equals("") && secondary_number.length() != 10)
            error = "Enter Secondary Mobile Number";
        else if (email.equals("") && !email.contains("@"))
            error = "Enter Email";
        else if (address.equals(""))
            error = "Enter Address";
        if (error != null) {
            view.showErrorToast(error);
            return true;
        } else {

            StudentDetailsRef studentDetailsRef = new StudentDetailsRef(registration_number, name, mobile_number, address, email, secondary_number);
            dbQueries.open();
            dbQueries.updateStudentDetails(studentDetailsRef, depId);
            dbQueries.close();
            return false;
        }
    }

    @Override
    public Boolean hasErrors(String name, String registration_number, String mobile_number, String secondary_number, String email, String address, String depId) {
        String error = null;
        if (name.equals(""))
            error = "Enter name";
        else if (registration_number.equals(""))
            error = "Enter Registration Number";
        else if (mobile_number.equals("") && mobile_number.length() != 10)
            error = "Enter Mobile Number";
        else if (!secondary_number.equals("") && secondary_number.length() != 10)
            error = "Enter Secondary Mobile Number";
        else if (email.equals("") && !email.contains("@"))
            error = "Enter Email";
        else if (address.equals(""))
            error = "Enter Address";
        if (error != null) {
            view.showErrorToast(error);
            return true;
        } else {

            StudentDetailsRef studentDetailsRef = new StudentDetailsRef(registration_number, name, mobile_number, address, email, secondary_number);
            dbQueries.open();
            dbQueries.addStudentDetails(studentDetailsRef, depId);
            dbQueries.close();
            return false;
        }


    }

    @Override
    public int getScreenWidth(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }

    @Override
    public void getStudentList(String subjectsList) {
        ArrayList<StudentDetailsRef> list;
        dbQueries.open();
        list = dbQueries.getStudentList(subjectsList);
        view.showListView(list);
        dbQueries.close();
    }

    @Override
    public String[] getDepartmentsSubjects(String depName) {
        return new String[0];
    }

    @Override
    public void getUpdateStudentView(StudentDetailsRef studentDetailsRef, ArrayList<String> subjectsList) {
        view.showUpdateStudentDialog(studentDetailsRef, subjectsList);
    }

    @Override
    public void getDepartmentNames() {
        dbQueries.open();
        ArrayList<String> list = dbQueries.getDepartmentsNamesList();
        view.showAddStudentDialog(list);
        dbQueries.close();
    }
}
