package com.example.mcproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mcproject.R;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Random;

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

    private String random(){
        Random rand = new Random(); //instance of random class
        String randomNumber = String.format("%04d", (Object) Integer.valueOf(rand.nextInt(1001)));
        return randomNumber;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void CounProblemPage(View v){

        boolean blnValidInput = true;

        EditText NameText = (EditText) findViewById(R.id.inputFirstNames);
        EditText SurnameText = (EditText) findViewById(R.id.inputSurname);
        EditText EmailText = (EditText) findViewById(R.id.inputEmail);
        EditText PasswordText= (EditText) findViewById(R.id.inputPassword);
        EditText ConfirmPasswordText = (EditText) findViewById(R.id.inputConfirmPassword);
        Spinner LanguageSpin = (Spinner) findViewById(R.id.inputSpinnerLanguage);
        Spinner ReligionSpin = (Spinner) findViewById(R.id.inputSpinnerReligion);
        Spinner GenderSpin = (Spinner) findViewById(R.id.inputSpinnerGender);
        EditText AgeText = (EditText) findViewById(R.id.inputAge);

        String Name = NameText.getText().toString();
        String Surname = SurnameText.getText().toString();
        String Email = EmailText.getText().toString();
        String Password = PasswordText.getText().toString();
        String ConfirmPassword = ConfirmPasswordText.getText().toString();
        String Language = LanguageSpin.getSelectedItem().toString();
        String Religion = ReligionSpin.getSelectedItem().toString();
        String Gender = GenderSpin.getSelectedItem().toString();
        String Age = AgeText.getText().toString();

        blnValidInput = ValidateFields(Name,Surname,Email,Password,ConfirmPassword,Language, Religion, Gender, Age);
        if(blnValidInput == true){
            if(!Password.equals(ConfirmPassword)){
                Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
                PasswordText.setText(null);
                ConfirmPasswordText.setText(null);
            }
            else{
                String Safetypin = random();
                addCounsellor(Name,Surname,Email,Password,Language,Religion,Gender,Age,Safetypin);
            }
        }
    }

    public boolean ValidateFields(String name, String surname, String email, String password,
                                  String confirmpassword,String language, String religion, String gender, String age)
    {
        if(name.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter in your First Names", Toast.LENGTH_LONG).show();
            return false;
        }
        if(surname.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter in your Surname", Toast.LENGTH_LONG).show();
            return false;
        }
        if(email.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter in your Email", Toast.LENGTH_LONG).show();
            return false;
        }
        if(password.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter in a Password", Toast.LENGTH_LONG).show();
            return false;
        }
        if(confirmpassword.equals("")){
            Toast.makeText(getApplicationContext(), "Please confirm your Password", Toast.LENGTH_LONG).show();
            return false;
        }
        if(language.equals("")){
            Toast.makeText(getApplicationContext(), "Please select a Language", Toast.LENGTH_LONG).show();
            return false;
        }
        if(religion.equals("")){
            Toast.makeText(getApplicationContext(), "Please select a Religion", Toast.LENGTH_LONG).show();
            return false;
        }
        if(gender.equals("")){
            Toast.makeText(getApplicationContext(), "Please select a Gender", Toast.LENGTH_LONG).show();
            return false;
        }
        if(age.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter in your Age", Toast.LENGTH_LONG).show();
            return false;
        }
            return true;
    }

    public void addCounsellor(String name, String surname, String email, String password,
                              String language, String religion, String gender, String age, String Safetypin)
    {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/counsellor_signup.php?").newBuilder();
        urlBuilder.addQueryParameter("Counsellor_Email", email);
        urlBuilder.addQueryParameter("Counsellor_Password", password);
        urlBuilder.addQueryParameter("First_Names", name);
        urlBuilder.addQueryParameter("Surname", surname);
        urlBuilder.addQueryParameter("Gender", gender);
        urlBuilder.addQueryParameter("Age", age);
        urlBuilder.addQueryParameter("Religion", religion);
        urlBuilder.addQueryParameter("Languages", language);
        urlBuilder.addQueryParameter("Counsellor_Safety_Pin", Safetypin);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull Response response) throws IOException {
                final String myResponse = response.body().string();
                RegisterActivityCounsellor.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        
                        if (myResponse.equals("Email already logged on system")){
                            Toast.makeText(getApplicationContext(), myResponse, Toast.LENGTH_LONG).show();
                        }
                        else{
                            String Counsellor_ID = myResponse;
                            Intent CounProbs = new Intent(RegisterActivityCounsellor.this, CounsellorProblemsActivity.class);
                            CounProbs.putExtra("Counsellor_ID", Counsellor_ID);
                            CounProbs.putExtra("Backup Pin", Safetypin);
                            CounProbs.putExtra("Name", name);
                            // Start the new activity.
                            startActivity(CounProbs);
                            finish();
                        }
                    }
                });

            }
        });

    }
}

