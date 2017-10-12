package com.example.nikhil.studentregistrationsystem.allStudentActivtiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.nikhil.studentregistrationsystem.R;
import com.example.nikhil.studentregistrationsystem.mainActivity_MVP.MainActivity;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by Nikhil on 2/10/17.
 */

public class AllStudentsActivity extends AppCompatActivity implements AllMVP.view {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        recyclerView = (RecyclerView) findViewById(R.id.allList);
        final AllStudentListPresenter presenter = new AllStudentListPresenter(this, this);
        presenter.getStudentList();
        FloatingActionButton bt1 = (FloatingActionButton) findViewById(R.id.menu_item);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deleteAll();
                recyclerView.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public void setStudentListAdapter(ArrayList<AllStudentListRef> list) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new AllStudentListAdapter(this, list));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
