package com.example.nikhil.studentregistrationsystem.allStudentActivtiy;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikhil.studentregistrationsystem.R;

import java.util.ArrayList;

/**
 * Created by nikhil on 5/9/17.
 */

public class AllStudentListAdapter extends RecyclerView.Adapter<AllStudentListAdapter.MyViewHolder> {
    Context context;
    ArrayList<AllStudentListRef> mStudentDetailsList;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_student_list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AllStudentListRef currentRef = mStudentDetailsList.get(position);
        holder.mRegNum.setText("Reg. Number: " + currentRef.getReg_num());
        holder.mName.setText("Name: " + currentRef.getName());
        holder.mMobileNumber.setText("Mobile Number: " + currentRef.getMobile_number());
        holder.mMobileNumber2.setText("Secondary Number: " + currentRef.getMobile_number2());
        holder.mEmail.setText("Email: " + currentRef.getEmail());
        holder.mAddress.setText("Address: " + currentRef.getAddress());
        holder.mDeptName.setText("Dept. Name: " + currentRef.getDept_name());
    }

    @Override
    public int getItemCount() {
        return mStudentDetailsList.size();
    }

    public AllStudentListAdapter(Activity context, ArrayList<AllStudentListRef> mStudentDetailsList) {
        this.context = context;
        this.mStudentDetailsList = mStudentDetailsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mRegNum;
        public TextView mName;
        public TextView mMobileNumber;
        public TextView mMobileNumber2;
        public TextView mEmail;
        public TextView mAddress;
        public TextView mDeptName;

        public MyViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.name);
            mRegNum = (TextView) itemView.findViewById(R.id.reg_num);
            mMobileNumber = (TextView) itemView.findViewById(R.id.mobile_number);
            mMobileNumber2 = (TextView) itemView.findViewById(R.id.mobile_number2);
            mEmail = (TextView) itemView.findViewById(R.id.email);
            mAddress = (TextView) itemView.findViewById(R.id.address);
            mDeptName = (TextView) itemView.findViewById(R.id.dept_name);
        }
    }

}
