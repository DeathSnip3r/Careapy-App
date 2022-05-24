package com.example.mcproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mcproject.R;

public class RegisterActivityCounsellor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] languages = { "Afrikaans","English","Sotho","Xhosa","Zulu"};
    String[] Religion = {"Christian", "Islam", "Hinduism", "Judaism","Buddhism","No Religion"};
    Integer[] Age ={20,21,22,23};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_counsellor);
        Intent registerCouns = getIntent();

        Spinner spinLang = findViewById(R.id.inputSpinnerLanguage);
        spinLang.setOnItemSelectedListener(this);
        Spinner spinReligion = findViewById(R.id.inputSpinnerReligion);
        spinReligion.setOnItemSelectedListener(this);
        Spinner spinAge = findViewById(R.id.inputSpinnerAge);
        spinAge.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter<String> lang = new ArrayAdapter(this, android.R.layout.simple_spinner_item, languages);

        // set simple layout resource file
        // for each item of spinner
        lang.setDropDownViewResource(android.R.layout.simple_spinner_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinLang.setAdapter(lang);
        // Create the instance of ArrayAdapter
        // having the list of courses

        ArrayAdapter<String> relig = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Religion);

        relig.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinReligion.setAdapter(relig);

        ArrayAdapter<Integer> age = new ArrayAdapter(this, android.R.layout.simple_spinner_item,Age);

        age.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinAge.setAdapter(age);
    }
    // Performing action when ItemSelected
    // from spinner, Overriding onItemSelected method

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg3, long arg2) {
        switch (arg0.getId()){
            case R.id.inputSpinnerLanguage:
                Toast.makeText(getApplicationContext(), languages[arg3], Toast.LENGTH_LONG).show();
                break;
            case R.id.inputReligion:
                Toast.makeText(getApplicationContext(), Religion[arg3], Toast.LENGTH_LONG).show();
                break;
            case R.id.inputAge:
                Toast.makeText(getApplicationContext(), Age[arg3], Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void CounProblemPage(View v){
        Button next= (Button) findViewById(R.id.btnCounProblem);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the intent which will start your new activity.
                Intent CounProbs = new Intent(RegisterActivityCounsellor.this, CounsellorProblemsActivity.class);
                // Start the new activity.
                startActivity(CounProbs);
            }
        });
    }
}

