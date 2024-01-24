package com.rto.prepzone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    DashboardFragment dashboardFragment = new DashboardFragment();
    Toolbar toolbar;
    Fragment fragment;
    String fName;
    FragmentManager fm;
    Activity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        // appBarLayout = findViewById(R.id.appbar);

        context = this;
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        replaceFragment(new DashboardFragment());


    }
    @Override
    public void onBackPressed() {

            replaceFragment(dashboardFragment);

            super.onBackPressed();

    }

    public void replaceFragment(Fragment fragment) {
        this.fragment=fragment;
        fName = fragment.getClass().getSimpleName();
        fm = getSupportFragmentManager();
        FragmentTransaction fTransaction = fm.beginTransaction();
        fTransaction.replace(R.id.frame, fragment);
       // overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        fTransaction.addToBackStack(null);
        fTransaction.commit();

//        if (drawer != null) {
//            drawer.closeDrawer(GravityCompat.START);
//        }

    }

}