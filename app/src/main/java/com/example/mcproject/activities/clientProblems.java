package com.example.mcproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.mcproject.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class clientProblems extends AppCompatActivity {
    String Client_ID;
    String Religion;
    String Language;
    String BackupPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_problems);
        Intent regAcc = getIntent();
        Client_ID = regAcc.getStringExtra("Client_ID");
        Religion = regAcc.getStringExtra("Client_Religion_Selected");
        Language = regAcc.getStringExtra("Client_Language");
        BackupPin = regAcc.getStringExtra("Backup Pin");
    }
    public void FinishRegClient(View v){

    
                String problem;
                int Count=0;
                CheckBox ADD = (CheckBox) findViewById(R.id.cbxAddiction);
                CheckBox ANX = (CheckBox) findViewById(R.id.cbxAnxiety);
                CheckBox ANM = (CheckBox) findViewById(R.id.cbxAnger);
                CheckBox CAD = (CheckBox) findViewById(R.id.cbxCareer);
                CheckBox DEP = (CheckBox) findViewById(R.id.cbxDepression);
                CheckBox EAD = (CheckBox) findViewById(R.id.cbxEating);
                CheckBox FAC = (CheckBox) findViewById(R.id.cbxFamily);
                CheckBox GRL = (CheckBox) findViewById(R.id.cbxGrief);
                CheckBox LGB = (CheckBox) findViewById(R.id.cbxLGBTQ);
                CheckBox MID = (CheckBox) findViewById(R.id.cbxMental);
                CheckBox PAR = (CheckBox) findViewById(R.id.cbxParenting);
                CheckBox REL = (CheckBox) findViewById(R.id.cbxRelationship);
                CheckBox SEE = (CheckBox) findViewById(R.id.cbxSelf);
                CheckBox SLD = (CheckBox) findViewById(R.id.cbxSleeping);
                CheckBox STR = (CheckBox) findViewById(R.id.cbxStress);
                CheckBox TRA = (CheckBox) findViewById(R.id.cbxTrauma);

                if (ADD.isChecked()){
                    problem = "ADD";
                    addproblem(problem);
                    ++Count;
                }
                if (ANX.isChecked()){
                    problem = "ANX";
                    addproblem(problem);
                    ++Count;
                }
                if (ANM.isChecked()){
                    problem = "ANM";
                    addproblem(problem);
                    ++Count;
                }
                if (CAD.isChecked()){
                    problem = "CAD";
                    addproblem(problem);
                    ++Count;
                }
                if (DEP.isChecked()){
                    problem = "DEP";
                    addproblem(problem);
                    ++Count;
                }
                if (EAD.isChecked()){
                    problem = "EAD";
                    addproblem(problem);
                    ++Count;
                }
                if (FAC.isChecked()){
                    problem = "FAC";
                    addproblem(problem);
                    ++Count;
                }
                if (GRL.isChecked()){
                    problem = "GRL";
                    addproblem(problem);
                    ++Count;
                }
                if (LGB.isChecked()){
                    problem = "LGB";
                    addproblem(problem);
                    ++Count;
                }
                if (MID.isChecked()){
                    problem = "MID";
                    addproblem(problem);
                    ++Count;
                }
                if (PAR.isChecked()){
                    problem = "PAR";
                    addproblem(problem);
                    ++Count;
                }
                if (REL.isChecked()){
                    problem = "REL";
                    addproblem(problem);
                    ++Count;
                }
                if (SEE.isChecked()){
                    problem = "SEE";
                    addproblem(problem);
                    ++Count;
                }
                if (SLD.isChecked()){
                    problem = "SLD";
                    addproblem(problem);
                    ++Count;
                }
                if (STR.isChecked()){
                    problem = "STR";
                    addproblem(problem);
                    ++Count;
                }
                if (TRA.isChecked()){
                    problem = "TRA";
                    addproblem(problem);
                    ++Count;
                }

                if (Count==0) {
                    Toast.makeText(getApplicationContext(), "Please select at least one problem", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent RegCoun1 = new Intent(clientProblems.this, WelcomeClient.class);
                    // Start the new activity.
                    RegCoun1 .putExtra("Client_ID", Client_ID);
                    RegCoun1 .putExtra("Client_Religion_Selected", Religion);
                    RegCoun1 .putExtra("Client_Language", Language);
                    RegCoun1.putExtra("Backup Pin", BackupPin);
                    startActivity(RegCoun1);
                }
            }



    public void addproblem(String Problem){
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/client_problems.php?").newBuilder();
        urlBuilder.addQueryParameter("Client_ID", Client_ID);
        urlBuilder.addQueryParameter("Problem_Code", Problem);
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