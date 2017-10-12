package com.example.nikhil.studentregistrationsystem.mainActivity_MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.nikhil.studentregistrationsystem.R;
import com.example.nikhil.studentregistrationsystem.databaseModel.DepartmentDetailsRef;
import com.example.nikhil.studentregistrationsystem.loginActivity.LoginActivity;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainMVP.view {

    private ViewPager mViewPager;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Check for previous login
        if (!Prefs.getBoolean("login", false)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        mainPresenter = new MainPresenter(this, this);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        ArrayList<DepartmentDetailsRef> list = mainPresenter.getDepartmentDetailsList();
        for (DepartmentDetailsRef s : list) {
            MainFragment fragment = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putString("dep_name", s.getDep_name());
            bundle.putString("dep_id", s.getDep_id());
            bundle.putString("courses", s.getCourses());
            fragment.setArguments(bundle);
            adapter.addFragment(fragment, s.getDep_name());
        }

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
