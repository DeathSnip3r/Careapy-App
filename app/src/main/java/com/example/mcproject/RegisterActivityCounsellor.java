package com.example.mcproject;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mcproject.ui.main.SectionsPagerAdapter;
import com.example.mcproject.databinding.ActivityRegisterCounsellorBinding;

public class RegisterActivityCounsellor extends AppCompatActivity {

    private ActivityRegisterCounsellorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterCounsellorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        SectionsPagerAdapter.add(new F1Client());
        SectionsPagerAdapter.add(new F2Client());
        SectionsPagerAdapter.add(new F3Client());


    }
}