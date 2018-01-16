package com.example.kolot.test_balina_1.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.kolot.test_balina_1.R;
import com.example.kolot.test_balina_1.adapters.SectionsPagerAdapter;
import com.example.kolot.test_balina_1.fragments.LoginFragment;
import com.example.kolot.test_balina_1.fragments.RegistrationFragment;

public class MainActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public void setupViewPager (ViewPager mViewPager){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment(), "Login");
        adapter.addFragment(new RegistrationFragment(), "Registration");
        mViewPager.setAdapter(adapter);
    }


}
