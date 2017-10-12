package com.example.nikhil.studentregistrationsystem.databaseModel;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nikhil.studentregistrationsystem.allStudentActivtiy.AllStudentListRef;

import java.util.ArrayList;

/**
 * Created by nikhil on 5/9/17.
 */

public class DbQueries implements DbQueriesInterface {
    Context context;
    public SQLiteDatabase database;
    DBHelper dbHelper;

    public DbQueries(Context context) {
        this.context = context;
    }


    public DbQueries open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    @Override
    public void truncateAllTables() {
        database = dbHelper.getWritableDatabase();

        database.execSQL("delete from student_details");
        database.execSQL("delete from selected_department");

    }

    public void close() {
        dbHelper.close();
    }

    @Override
    public void truncateDb(String depId) {
        database = dbHelper.getWritableDatabase();

        database.execSQL("delete from student_details WHERE registration_number IN (" +
                " SELECT a.registration_number from student_details a INNER JOIN selected_department b" +
                " ON a.registration_number = b.registration_number WHERE b.dep_id = '" + depId + "')");
        database.execSQL("delete from selected_department WHERE dep_id = '" + depId + "'");
    }

    @Override
    public void addStudentDetails(StudentDetailsRef studentDetailsRef, String depId) {
        database = dbHelper.getWritableDatabase();
        if (studentDetailsRef.getSecondary_number().equals(""))
            database.execSQL("INSERT INTO student_details (registration_number, name, mobile_number, " +
                    "address, email) values ('" + studentDetailsRef.getRegistration_number() +
                    "', '" + studentDetailsRef.getName() + "', '" + studentDetailsRef.getMobile_number()
                    + "', '" + studentDetailsRef.getAddress() + "' ,'" + studentDetailsRef.getEmail() + "')");
        else
            database.execSQL("INSERT INTO student_details (registration_number, name, mobile_number, " +
                    "address, email, secondary_number) values ('" + studentDetailsRef.getRegistration_number() +
                    "', '" + studentDetailsRef.getName() + "', '" + studentDetailsRef.getMobile_number()
                    + "', '" + studentDetailsRef.getAddress() + "' ,'" + studentDetailsRef.getEmail()
                    + "', '" + studentDetailsRef.getSecondary_number() + "')");
        database.execSQL("INSERT INTO selected_department (registration_number, dep_id) values ('" +
                studentDetailsRef.getRegistration_number() + "', '" + depId + "')");

    }

    @Override
    public ArrayList<AllStudentListRef> getAllStudentList() {
        ArrayList<AllStudentListRef> list = new ArrayList<AllStudentListRef>();
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery("SELECT a.*, c.* FROM student_details a " +
                            "INNER JOIN selected_department b ON a.registration_number = b.registration_number " +
                            "INNER JOIN department_details c ON c.dep_id = b.dep_id"
                    , null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        list.add(new AllStudentListRef(
                                cursor.getString(cursor.getColumnIndex("registration_number")),
                                cursor.getString(cursor.getColumnIndex("name")),
                                cursor.getString(cursor.getColumnIndex("mobile_number")),
                                cursor.getString(cursor.getColumnIndex("secondary_number")),
                                cursor.getString(cursor.getColumnIndex("email")),
                                cursor.getString(cursor.getColumnIndex("address")),
                                cursor.getString(cursor.getColumnIndex("dep_name"))));
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.toString());
        }
        return list;
    }

    @Override
    public void updateStudentDetails(StudentDetailsRef studentDetailsRef, String depId) {
        database = dbHelper.getWritableDatabase();
        if (studentDetailsRef.getSecondary_number().equals(""))
            database.execSQL("UPDATE student_details SET " +
                    "name = '" + studentDetailsRef.getName() + "'" +
                    ", mobile_number = '" + studentDetailsRef.getMobile_number() + "'" +
                    ", address = '" + studentDetailsRef.getAddress() + "'" +
                    ", email = '" + studentDetailsRef.getEmail() + "'" +
                    "WHERE registration_number = '" + studentDetailsRef.getRegistration_number() + "'");
        else
            database.execSQL("UPDATE student_details SET " +
                    "name = '" + studentDetailsRef.getName() + "'" +
                    ", mobile_number = '" + studentDetailsRef.getMobile_number() + "'" +
                    ", address = '" + studentDetailsRef.getAddress() + "'" +
                    ", email = '" + studentDetailsRef.getEmail() + "'" +
                    ", secondary_number = '" + studentDetailsRef.getSecondary_number() + "'" +
                    "WHERE registration_number = '" + studentDetailsRef.getRegistration_number() + "'");


    }

    @Override
    public void deleteOneEntry(String reg_num) {
        database = dbHelper.getWritableDatabase();
        database.execSQL("delete from student_details WHERE registration_number = '" + reg_num + "'");
        database.execSQL("delete from selected_department WHERE registration_number = '" + reg_num + "'");
    }

    @Override
    public ArrayList<StudentDetailsRef> getStudentList(String depName) {
        ArrayList<StudentDetailsRef> list = new ArrayList<StudentDetailsRef>();
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery("SELECT a.* FROM student_details a " +
                    "INNER JOIN selected_department b ON a.registration_number = b.registration_number " +
                    "INNER JOIN department_details c ON c.dep_id = b.dep_id " +
                    "WHERE c.dep_name = '" + depName + "'", null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        list.add(new StudentDetailsRef(
                                cursor.getString(cursor.getColumnIndex("registration_number")),
                                cursor.getString(cursor.getColumnIndex("name")),
                                cursor.getString(cursor.getColumnIndex("mobile_number")),
                                cursor.getString(cursor.getColumnIndex("address")),
                                cursor.getString(cursor.getColumnIndex("email")),
                                cursor.getString(cursor.getColumnIndex("secondary_number"))));
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.toString());
        }
        return list;
    }

    @Override
    public ArrayList<DepartmentDetailsRef> getDepartmentDetailsList() {
        ArrayList<DepartmentDetailsRef> list = new ArrayList<DepartmentDetailsRef>();
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery("SELECT * FROM department_details", null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        list.add(new DepartmentDetailsRef(
                                cursor.getString(cursor.getColumnIndex("dep_id")),
                                cursor.getString(cursor.getColumnIndex("courses")),
                                cursor.getString(cursor.getColumnIndex("dep_name"))));
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.toString());
        }
        return list;
    }

    @Override
    public ArrayList<String> getDepartmentsNamesList() {
        ArrayList<String> list = new ArrayList<String>();
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery("SELECT * FROM department_details", null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        list.add(cursor.getString(cursor.getColumnIndex("dep_name")));
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.toString());
        }
        return list;
    }

    @Override
    public DepartmentDetailsRef getDepartmentsDetails(String departmentName) {
        DepartmentDetailsRef detailsRef = null;
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery("SELECT * FROM department_details WHERE dep_name = " + departmentName, null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        detailsRef = new DepartmentDetailsRef(
                                cursor.getString(cursor.getColumnIndex("dep_id")),
                                cursor.getString(cursor.getColumnIndex("courses")),
                                cursor.getString(cursor.getColumnIndex("dep_name")
                                ));
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.toString());
        }
        return detailsRef;
    }

    public String getData() {
        String s = "";
        try {
            Cursor cursor;
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery("SELECT * FROM department_details", null);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        s += cursor.getString(cursor.getColumnIndex("courses"));
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        } catch (Exception e) {
            Log.v("Exception", e.toString());
        }
        return s;
    }

}
