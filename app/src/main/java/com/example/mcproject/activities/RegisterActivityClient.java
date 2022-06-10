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

import java.util.Random;

public class RegisterActivityClient extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] languages = {"","English","Afrikaans","Xhosa","Zulu","Tswana","Ndebele","Tsonga","Swati"};
    String[] Religion = {"","Christian", "Islam", "Hinduism", "Judaism","Buddhism","None"};

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
                //Toast.makeText(getApplicationContext(), languages[arg3], Toast.LENGTH_LONG).show();
                break;
            case R.id.inputReligion:
                //Toast.makeText(getApplicationContext(), Religion[arg3], Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void ClientProblemPage(View v){
        boolean blnValidInput = true;

        EditText UsernameText = (EditText) findViewById(R.id.inputFirstNames);
        EditText PasswordText = (EditText) findViewById(R.id.inputPassword);
        EditText ConfirmPasswordText = (EditText) findViewById(R.id.inputConfirmPassword);
        Spinner LanguageSpin = (Spinner) findViewById(R.id.inputSpinnerLanguage);
        Spinner ReligionSpin = (Spinner) findViewById(R.id.inputSpinnerReligion);

        String Username = UsernameText.getText().toString();
        String Password = PasswordText.getText().toString();
        String ConfirmPassword = ConfirmPasswordText.getText().toString();
        String Language = LanguageSpin.getSelectedItem().toString();
        String Religion = ReligionSpin.getSelectedItem().toString();


        blnValidInput = ValidateFields(Username,Password,ConfirmPassword,Language,Religion);
        if(blnValidInput == true){
            if(!Password.equals(ConfirmPassword)){
                Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
                PasswordText.setText(null);
                ConfirmPasswordText.setText(null);
            }
            else{
                String Safetypin = random();
                addClient(Username,Password,Language,Religion,Safetypin);
            }
        }
    }

    private String random(){
            Random rand = new Random(); //instance of random class
            String randomNumber = String.format("%04d", (Object) Integer.valueOf(rand.nextInt(1001)));
            return randomNumber;
        }


    public boolean ValidateFields(String username, String password, String confirmpassword,String language, String religion)
    {
        if(username.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter in your Username", Toast.LENGTH_LONG).show();
            return false;
        }
        if(password.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter in your Password", Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(), "Please Confirm Religion", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }

    public void addClient(String username, String password, String language, String religion,String SafetyPin)
    {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/client_signup.php?").newBuilder();
        urlBuilder.addQueryParameter("Client_Username", username);
        urlBuilder.addQueryParameter("Client_Password", password);
        urlBuilder.addQueryParameter("Client_Safety_Pin", SafetyPin);
        urlBuilder.addQueryParameter("Languages", language);
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
                final String myResponse = response.body().string();
                RegisterActivityClient.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (myResponse.equals("Username already exists")){
                            Toast.makeText(getApplicationContext(), myResponse, Toast.LENGTH_LONG).show();
                        }
                        else{
                            String Client_ID = myResponse;
                            Intent ClientProblems = new Intent(RegisterActivityClient.this, clientProblems.class);
                            ClientProblems .putExtra("Client_ID", Client_ID);
                            ClientProblems .putExtra("Client_Religion_Selected", religion);
                            ClientProblems .putExtra("Client_Language", language);
                            ClientProblems .putExtra("Backup Pin", SafetyPin);

                            // Start the new activity.
                            startActivity(ClientProblems);
                            finish();
                        }
                    }
                });

            }
        });

    }
}