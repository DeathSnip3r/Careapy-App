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

public class RegisterActivityClient extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] languages = {"English","Afrikaans","Xhosa","Zulu","Tswana","Ndebele","Tsonga","Swati"};
    String[] Religion = {"Christian", "Islam", "Hinduism", "Judaism","Buddhism","No Religion"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);
        Intent registerClient = getIntent();

        Spinner spinLang = findViewById(R.id.inputSpinnerLanguage);
        spinLang.setOnItemSelectedListener(this);
        Spinner spinReligion = findViewById(R.id.inputSpinnerReligion);
        spinReligion.setOnItemSelectedListener(this);

        ArrayAdapter<String> language = new ArrayAdapter(this, android.R.layout.simple_spinner_item, languages);

        language.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinLang.setAdapter(language);

        ArrayAdapter<String> religion = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Religion);

        religion.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinReligion.setAdapter(religion);

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg3, long arg2) {
        switch (arg0.getId()){
            case R.id.inputSpinnerLanguage:
                Toast.makeText(getApplicationContext(), languages[arg3], Toast.LENGTH_LONG).show();
                break;
            case R.id.inputReligion:
                Toast.makeText(getApplicationContext(), Religion[arg3], Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void CounProblemPage(View v){
        Button next= (Button) findViewById(R.id.btnClientProblem);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the intent which will start your new activity.
                Intent ClientProbs = new Intent(RegisterActivityClient.this, clientProblems.class);
                // Start the new activity.
                startActivity(ClientProbs);
            }
        });
    }
}