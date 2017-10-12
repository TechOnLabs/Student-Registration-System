package com.example.nikhil.studentregistrationsystem.allStudentActivtiy;

import java.util.ArrayList;

/**
 * Created by Nikhil on 2/10/17.
 */

public interface AllMVP {
    interface view {
        void setStudentListAdapter(ArrayList<AllStudentListRef> list);
    }

    interface presenter {
        void getStudentList();

        void deleteAll();
    }
}
