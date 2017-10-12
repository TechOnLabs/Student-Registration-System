package com.example.nikhil.studentregistrationsystem.mainActivity_MVP;

/**
 * Created by Destroyer on 6/6/2017.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nikhil.studentregistrationsystem.R;
import com.example.nikhil.studentregistrationsystem.allStudentActivtiy.AllStudentsActivity;
import com.example.nikhil.studentregistrationsystem.databaseModel.StudentDetailsRef;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends Fragment implements MainMVP.fragmentView {
    Activity context;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.fab_menu)
    FloatingActionMenu floatingActionMenu;
    private FragmetPresenter mPresenter;
    String dep_name, dep_id;
    String[] courses;

    public MainFragment() {
        // Required empty public constructor
        context = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new FragmetPresenter(this, getActivity());

        Bundle bundle = getArguments();
        dep_name = bundle.getString("dep_name");
        dep_id = bundle.getString("dep_id");
        courses = bundle.getString("courses").split(",");
        mPresenter.getStudentList(dep_name);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<String> list = new ArrayList<String>();
                for (String s : courses)
                    list.add(s);
                mPresenter.getUpdateStudentView((StudentDetailsRef) listView.getItemAtPosition(i), list);
            }
        });
        FloatingActionButton bt1 = (FloatingActionButton) view.findViewById(R.id.menu_item);
        FloatingActionButton bt2 = (FloatingActionButton) view.findViewById(R.id.menu_item2);
        FloatingActionButton bt3 = (FloatingActionButton) view.findViewById(R.id.menu_item3);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionMenu.close(true);
                mPresenter.getDepartmentNames();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AllStudentsActivity.class));
                getActivity().finish();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionMenu.close(true);
                mPresenter.onClickTruncateDB(dep_id);
                mPresenter.getStudentList(dep_name);
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    //do something
                    if (floatingActionMenu.isOpened())
                        floatingActionMenu.close(true);
                }
                return true;

            }
        });
        return view;
    }


    @Override
    public void showUpdateStudentDialog(final StudentDetailsRef studentDetailsRef, ArrayList<String> subjectsList) {
        final Dialog addStudentDialog = new Dialog(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_student_dialog, null);
        addStudentDialog.setContentView(dialogView);

        if (addStudentDialog != null) {
            addStudentDialog.getWindow()
                    .setLayout((int) (mPresenter.getScreenWidth(getActivity()) * .9), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        addStudentDialog.setCancelable(true);
        final EditText mName = (EditText) dialogView.findViewById(R.id.d_name);
        final EditText mRegNum = (EditText) dialogView.findViewById(R.id.d_reg_num);
        final EditText mMobile = (EditText) dialogView.findViewById(R.id.d_mob_num);
        final EditText mSecMob = (EditText) dialogView.findViewById(R.id.d_sec_num);
        final EditText mEmail = (EditText) dialogView.findViewById(R.id.d_email);
        final EditText mAddress = (EditText) dialogView.findViewById(R.id.d_address);
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.d_spinner);
        Button addBtn = (Button) dialogView.findViewById(R.id.d_add_btn);
        Button delBtn = (Button) dialogView.findViewById(R.id.d_del_btn);
        delBtn.setVisibility(View.VISIBLE);
        addBtn.setText("UPDATE");
        mName.setText(studentDetailsRef.getName());
        mRegNum.setText(studentDetailsRef.getRegistration_number());
        mMobile.setText(studentDetailsRef.getMobile_number());
        mSecMob.setText(studentDetailsRef.getSecondary_number());
        mEmail.setText(studentDetailsRef.getEmail());
        mAddress.setText(studentDetailsRef.getAddress());
        ArrayAdapter<String> departmentListAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, courses);
        departmentListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(departmentListAdapter);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean error = mPresenter.updateStudentDetails(mName.getText().toString().trim(),
                        mRegNum.getText().toString().trim(),
                        mMobile.getText().toString().trim(),
                        mSecMob.getText().toString().trim(),
                        mEmail.getText().toString().trim(),
                        mAddress.getText().toString().trim(),
                        dep_id);
                if (!error) {
                    mPresenter.getStudentList(dep_name);
                    addStudentDialog.dismiss();
                }
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.deleteOneEntry(studentDetailsRef.getRegistration_number());
                mPresenter.getStudentList(dep_name);
                addStudentDialog.dismiss();
            }
        });


        addStudentDialog.show();
    }

    @Override
    public void showAddStudentDialog(ArrayList<String> depNameList) {
        final Dialog addStudentDialog = new Dialog(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_student_dialog, null);
        addStudentDialog.setContentView(dialogView);

        if (addStudentDialog != null) {
            addStudentDialog.getWindow()
                    .setLayout((int) (mPresenter.getScreenWidth(getActivity()) * .9), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        addStudentDialog.setCancelable(true);
        final EditText mName = (EditText) dialogView.findViewById(R.id.d_name);
        final EditText mRegNum = (EditText) dialogView.findViewById(R.id.d_reg_num);
        final EditText mMobile = (EditText) dialogView.findViewById(R.id.d_mob_num);
        final EditText mSecMob = (EditText) dialogView.findViewById(R.id.d_sec_num);
        final EditText mEmail = (EditText) dialogView.findViewById(R.id.d_email);
        final EditText mAddress = (EditText) dialogView.findViewById(R.id.d_address);
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.d_spinner);
        Button addBtn = (Button) dialogView.findViewById(R.id.d_add_btn);
        ArrayAdapter<String> departmentListAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, courses);
        departmentListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(departmentListAdapter);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean error = mPresenter.hasErrors(mName.getText().toString().trim(),
                        mRegNum.getText().toString().trim(),
                        mMobile.getText().toString().trim(),
                        mSecMob.getText().toString().trim(),
                        mEmail.getText().toString().trim(),
                        mAddress.getText().toString().trim(),
                        dep_id);
                if (!error) {
                    mPresenter.onStudentAdd();
                    mPresenter.getStudentList(dep_name);
                    addStudentDialog.dismiss();
                }
            }
        });


        addStudentDialog.show();
    }

    @Override
    public void showListView(ArrayList<StudentDetailsRef> studentList) {
        listView.setAdapter(new ListAdapter(getActivity(), studentList));
    }

    @Override
    public void showErrorToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
