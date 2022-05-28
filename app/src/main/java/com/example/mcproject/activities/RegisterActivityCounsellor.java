package com.example.mcproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.mcproject.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivityCounsellor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] languages = {"","Afrikaans","English","Ndebele","Swati","Tsonga","Tswana","Xhosa","Zulu"};
    String[] Religions = {"","Christian", "Islam", "Hinduism", "Judaism","Buddhism","None"};
    String[] Genders ={"","Male","Female","Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_counsellor);
        Intent registerCouns = getIntent();

        Spinner spinLang = findViewById(R.id.inputSpinnerLanguage);
        spinLang.setOnItemSelectedListener(this);

        Spinner spinReligion = findViewById(R.id.inputSpinnerReligion);
        spinReligion.setOnItemSelectedListener(this);

        Spinner spinGender = findViewById(R.id.inputSpinnerGender);
        spinGender.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter<String> lang = new ArrayAdapter(this, android.R.layout.simple_spinner_item, languages);
        // set simple layout resource file
        // for each item of spinner
        lang.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinLang.setAdapter(lang);

        ArrayAdapter<String> religion = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Religions);
        religion.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinReligion.setAdapter(religion);

        ArrayAdapter<String> gender = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Genders);
        gender.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinGender.setAdapter(gender);



        }

    // Performing action when ItemSelected
    // from spinner, Overriding onItemSelected method

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg3, long arg2) {
        switch (arg0.getId()){
            case R.id.inputSpinnerLanguage:
                //Toast.makeText(getApplicationContext(), languages[arg3], Toast.LENGTH_LONG).show();
                break;
            case R.id.inputSpinnerReligion:
                //Toast.makeText(getApplicationContext(), Religion[arg3], Toast.LENGTH_LONG).show();
                break;
            case R.id.inputSpinnerGender:
                //Toast.makeText(getApplicationContext(), Gender[arg3], Toast.LENGTH_LONG).show();
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
                boolean blnValidInput = true;

                EditText First_Names = (EditText) findViewById(R.id.inputFirstNames);
                EditText Surname = (EditText) findViewById(R.id.inputSurname);
                EditText Counsellor_Email = (EditText) findViewById(R.id.inputEmail);
                EditText Counsellor_Password = (EditText) findViewById(R.id.inputPassword);
                Spinner Languages = (Spinner) findViewById(R.id.inputSpinnerLanguage);
                Spinner Religion = (Spinner) findViewById(R.id.inputSpinnerReligion);
                Spinner Gender = (Spinner) findViewById(R.id.inputSpinnerGender);
                EditText Age = (EditText) findViewById(R.id.inputAge);
                EditText ConfirmPass = (EditText) findViewById(R.id.inputConfirmPassword);

                String FName = First_Names.getText().toString();
                String SName = Surname.getText().toString();
                String CounEmail = Counsellor_Email.getText().toString();
                String CounPass = Counsellor_Password.getText().toString();
                String Lang = Languages.getSelectedItem().toString();
                String Relig = Religion.getSelectedItem().toString();
                String Gen = Gender.getSelectedItem().toString();
                String age = Age.getText().toString();
                String confirmPass = ConfirmPass.getText().toString();

                blnValidInput = ValidateFields(FName,SName,CounEmail,CounPass,confirmPass,Lang, Relig, Gen, age);
                if(blnValidInput == true){
                    if(CounPass != confirmPass){
                        Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_LONG).show();
                        Counsellor_Password.setText(null);
                        ConfirmPass.setText(null);
                    }
                    else{
                        addCounsellor(CounEmail,CounPass,FName,SName,Gen,age, Relig,Lang);
                        String Counsellor_ID = "4";
                        // Create the intent which will start your new activity.
                        Intent CounProbs = new Intent(RegisterActivityCounsellor.this, CounsellorProblemsActivity.class);
                        CounProbs.putExtra("Counsellor_ID", Counsellor_ID);
                        // Start the new activity.
                        startActivity(CounProbs);
                    }

                }

            }
        });
    }

    public boolean ValidateFields(String First_Names, String Surname, String Counsellor_Email, String Counsellor_Password,
                                  String ConfirmPass,String Languages, String Religion, String Gender, String Age)
    {

        if(First_Names.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter in your First Names", Toast.LENGTH_LONG).show();
            return false;
        }
        if(Surname.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter in your Surname", Toast.LENGTH_LONG).show();
            return false;
        }
        if(Counsellor_Email.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter in your Email", Toast.LENGTH_LONG).show();
            return false;
        }
        if(Counsellor_Password.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter in a Password", Toast.LENGTH_LONG).show();
            return false;
        }
        if(ConfirmPass.equals("")){
            Toast.makeText(getApplicationContext(), "Please Confirm Password", Toast.LENGTH_LONG).show();
            return false;
        }
        if(Languages.equals("")){
            Toast.makeText(getApplicationContext(), "Please select a Language", Toast.LENGTH_LONG).show();
            return false;
        }
        if(Religion.equals("")){
            Toast.makeText(getApplicationContext(), "Please select a Religion", Toast.LENGTH_LONG).show();
            return false;
        }
        if(Gender.equals("")){
            Toast.makeText(getApplicationContext(), "Please select a Gender", Toast.LENGTH_LONG).show();
            return false;
        }
        if(Age.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter in an Age", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            return true;
        }
    }

    public void addCounsellor(String Counsellor_Email,String Password,String firstName,String surname,String Gender,
                              String Age,String Relig,String Lang)
    {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/counsellor_problems.php?").newBuilder();
        urlBuilder.addQueryParameter("Counsellor_Email", Counsellor_Email);
        urlBuilder.addQueryParameter("Counsellor_Password", Password);
        urlBuilder.addQueryParameter("First_Names", firstName);
        urlBuilder.addQueryParameter("Surname", surname);
        urlBuilder.addQueryParameter("Gender", Gender);
        urlBuilder.addQueryParameter("Age", Age);
        urlBuilder.addQueryParameter("Religion", Relig);
        urlBuilder.addQueryParameter("Languages", Lang);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });

    }
}

