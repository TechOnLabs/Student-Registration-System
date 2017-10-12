package com.example.nikhil.studentregistrationsystem.databaseModel;

/**
 * Created by Destroyer on 6/10/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    Context context;
    private static final String DB_NAME = "studentDatabase";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    String student_detailTable = "CREATE  TABLE \"student_details\" (\"pk_id\" " +
            "INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , \"registration_number\" " +
            "VARCHAR NOT NULL , \"name\" VARCHAR NOT NULL , \"mobile_number\" VARCHAR NOT NULL , " +
            "\"address\" VARCHAR NOT NULL , \"email\" VARCHAR NOT NULL , \"secondary_number\" " +
            "VARCHAR NOT NULL DEFAULT \"Not Provided\")";

    String department_details = "CREATE  TABLE \"department_details\" " +
            "(\"pk_id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE ," +
            " \"dep_id\" VARCHAR NOT NULL , \"courses\" VARCHAR NOT NULL , " +
            "\"dep_name\" VARCHAR NOT NULL )";

    String selected_department = "CREATE  TABLE \"selected_department\" " +
            "(\"registration_number\" VARCHAR NOT NULL , \"dep_id\" VARCHAR NOT NULL," +
            "FOREIGN KEY(registration_number) REFERENCES student_details(registration_number))";
    String insertDD1 = "INSERT INTO department_details (\"dep_id\", \"courses\", \"dep_name\") " +
            "values (\"1\", \"Java,DBMS,Android\",\"CSE\")";
    String insertDD2 = "INSERT INTO department_details (dep_id, courses, dep_name) " +
            "values (\"2\", \"Java,DBMS,Network Security\",\"IT\")";
    String insertDD3 = "INSERT INTO department_details (dep_id, courses, dep_name) " +
            "values (\"3\", \"Computer Networks,DBMS,Android\",\"SOFTWARE\")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(student_detailTable);
        db.execSQL(department_details);
        db.execSQL(selected_department);
        db.execSQL(insertDD1);
        db.execSQL(insertDD2);
        db.execSQL(insertDD3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}
