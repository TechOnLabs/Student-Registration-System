package com.example.nikhil.studentregistrationsystem.mainActivity_MVP;

import android.app.Activity;

import com.example.nikhil.studentregistrationsystem.databaseModel.DepartmentDetailsRef;
import com.example.nikhil.studentregistrationsystem.databaseModel.StudentDetailsRef;

import java.util.ArrayList;

/**
 * Created by nikhil on 5/9/17.
 */

public interface MainMVP {
    interface view {
    }

    interface presenter {

        ArrayList<DepartmentDetailsRef> getDepartmentDetailsList();
    }

    interface fragmentView {

        void showAddStudentDialog(ArrayList<String> subjectsList);

        void showUpdateStudentDialog(StudentDetailsRef studentDetailsRef, ArrayList<String> subjectsList);

        void showListView(ArrayList<StudentDetailsRef> studentList);

        void showErrorToast(String message);
    }

    interface fragmentPresenter {

        void onStudentAdd();

        void onClickTruncateDB(String depId);

        void deleteOneEntry(String reg_num);

        void onClickStudentAdd();

        void getStudentList(String depName);

        String[] getDepartmentsSubjects(String depName);

        void getDepartmentNames();

        void getUpdateStudentView(StudentDetailsRef studentDetailsRef, ArrayList<String> subjectsList);

        int getScreenWidth(Activity activity);

        Boolean hasErrors(String name, String registration_number, String mobile_number, String secondary_number
                , String email, String address, String depId);

        Boolean updateStudentDetails(String name, String registration_number, String mobile_number, String secondary_number
                , String email, String address, String depId);
    }
}
