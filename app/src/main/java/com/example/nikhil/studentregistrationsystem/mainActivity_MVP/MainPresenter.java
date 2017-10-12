package com.example.nikhil.studentregistrationsystem.mainActivity_MVP;

import android.content.Context;

import com.example.nikhil.studentregistrationsystem.databaseModel.DBHelper;
import com.example.nikhil.studentregistrationsystem.databaseModel.DbQueries;
import com.example.nikhil.studentregistrationsystem.databaseModel.DepartmentDetailsRef;

import java.util.ArrayList;

/**
 * Created by nikhil on 5/9/17.
 */

public class MainPresenter implements MainMVP.presenter {
    private MainMVP.view view;
    private DBHelper dbHelper;
    private DbQueries dbQueries;

    public MainPresenter(MainMVP.view view, Context context) {
        this.view = view;
        dbHelper = new DBHelper(context);
        dbQueries = new DbQueries(context);
    }


    @Override
    public ArrayList<DepartmentDetailsRef> getDepartmentDetailsList() {
        dbQueries.open();
        ArrayList<DepartmentDetailsRef> list = dbQueries.getDepartmentDetailsList();
        dbQueries.close();
        return list;
    }


    public String getData() {
        dbQueries.open();
        return dbQueries.getData();
    }
}
