package com.example.nikhil.studentregistrationsystem.mainActivity_MVP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nikhil.studentregistrationsystem.R;
import com.example.nikhil.studentregistrationsystem.databaseModel.StudentDetailsRef;

import java.util.ArrayList;

/**
 * Created by nikhil on 5/9/17.
 */

public class ListAdapter extends ArrayAdapter<StudentDetailsRef> {
    Context context;
    ArrayList<StudentDetailsRef> arrayList;

    ListAdapter(Context context, ArrayList<StudentDetailsRef> arrayList) {
        super(context, -1, arrayList);
        this.context = context;
        this.arrayList = arrayList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.main_list_item, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView reg_num = (TextView) rowView.findViewById(R.id.reg_num);
        name.setText(arrayList.get(position).getName());
        reg_num.setText(arrayList.get(position).getRegistration_number());
        // change the icon for Windows and iPhone

        return rowView;
    }
}
