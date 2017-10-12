package com.example.nikhil.studentregistrationsystem.allStudentActivtiy;

import android.content.Context;

import com.example.nikhil.studentregistrationsystem.databaseModel.DBHelper;
import com.example.nikhil.studentregistrationsystem.databaseModel.DbQueries;

import java.util.ArrayList;

/**
 * Created by Nikhil on 2/10/17.
 */

public class AllStudentListPresenter implements AllMVP.presenter {
    private AllMVP.view view;
    private DBHelper dbHelper;
    private DbQueries dbQueries;

    public AllStudentListPresenter(AllMVP.view view, Context context) {
        this.view = view;
        dbHelper = new DBHelper(context);
        dbQueries = new DbQueries(context);
    }

    @Override
    public void deleteAll() {
        dbQueries.open();
        dbQueries.truncateAllTables();
        dbQueries.close();
    }

    @Override
    public void getStudentList() {
        dbQueries.open();
        ArrayList<AllStudentListRef> list = dbQueries.getAllStudentList();
        dbQueries.close();
        view.setStudentListAdapter(list);
    }
}
