package com.example.mcproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mcproject.R;
import com.google.android.material.card.MaterialCardView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CounsellorProblemsActivity extends AppCompatActivity {
    String Counsellor_ID;
    String BackupPin;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsellor_problems);
        Intent regAcc = getIntent();
        Counsellor_ID = regAcc.getStringExtra("Counsellor_ID");
        BackupPin = regAcc.getStringExtra("Backup Pin");
        name = regAcc.getStringExtra("Name");

        //set onclicklistener for cardviews
        SetListeners();
    }

    public void FinishRegCoun(View v){

        MaterialCardView ADD = (MaterialCardView) findViewById(R.id.cardAddiction);
        MaterialCardView ANM = (MaterialCardView) findViewById(R.id.cardAnger);
        MaterialCardView ANX = (MaterialCardView) findViewById(R.id.cardAnxiety);
        MaterialCardView CAD = (MaterialCardView) findViewById(R.id.cardCareer);
        MaterialCardView DEP = (MaterialCardView) findViewById(R.id.cardDepression);
        MaterialCardView EAD = (MaterialCardView) findViewById(R.id.cardEating);
        MaterialCardView FAC = (MaterialCardView) findViewById(R.id.cardFamily);
        MaterialCardView GRL = (MaterialCardView) findViewById(R.id.cardGrief);
        MaterialCardView LGB = (MaterialCardView) findViewById(R.id.cardLGBTQ);
        MaterialCardView MID = (MaterialCardView) findViewById(R.id.cardMental);
        MaterialCardView PAR = (MaterialCardView) findViewById(R.id.cardParenting);
        MaterialCardView REL = (MaterialCardView) findViewById(R.id.cardRelationship);
        MaterialCardView SEE = (MaterialCardView) findViewById(R.id.cardSelf);
        MaterialCardView SLD = (MaterialCardView) findViewById(R.id.cardSleep);
        MaterialCardView STR = (MaterialCardView) findViewById(R.id.cardStress);
        MaterialCardView TRA = (MaterialCardView) findViewById(R.id.cardTrauma);

        String problem;
        int Count=0;

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
        else {
            // Create the intent which will start your new activity.
            Intent RegCoun = new Intent(CounsellorProblemsActivity.this, WelcomeCounsellor.class);
            RegCoun.putExtra("Backup Pin", BackupPin);
            RegCoun.putExtra("Name",name);
            // Start the new activity.
            startActivity(RegCoun);
            finish();
        }     


    }

    public void SetListeners(){
        MaterialCardView ADD = (MaterialCardView) findViewById(R.id.cardAddiction);
        MaterialCardView ANM = (MaterialCardView) findViewById(R.id.cardAnger);
        MaterialCardView ANX = (MaterialCardView) findViewById(R.id.cardAnxiety);
        MaterialCardView CAD = (MaterialCardView) findViewById(R.id.cardCareer);
        MaterialCardView DEP = (MaterialCardView) findViewById(R.id.cardDepression);
        MaterialCardView EAD = (MaterialCardView) findViewById(R.id.cardEating);
        MaterialCardView FAC = (MaterialCardView) findViewById(R.id.cardFamily);
        MaterialCardView GRL = (MaterialCardView) findViewById(R.id.cardGrief);
        MaterialCardView LGB = (MaterialCardView) findViewById(R.id.cardLGBTQ);
        MaterialCardView MID = (MaterialCardView) findViewById(R.id.cardMental);
        MaterialCardView PAR = (MaterialCardView) findViewById(R.id.cardParenting);
        MaterialCardView REL = (MaterialCardView) findViewById(R.id.cardRelationship);
        MaterialCardView SEE = (MaterialCardView) findViewById(R.id.cardSelf);
        MaterialCardView SLD = (MaterialCardView) findViewById(R.id.cardSleep);
        MaterialCardView STR = (MaterialCardView) findViewById(R.id.cardStress);
        MaterialCardView TRA = (MaterialCardView) findViewById(R.id.cardTrauma);

        ADD.setOnClickListener(view -> {
            ADD.setChecked(!ADD.isChecked());
        });
        ANM.setOnClickListener(view -> {
            ANM.setChecked(!ANM.isChecked());
        });
        ANX.setOnClickListener(view -> {
            ANX.setChecked(!ANX.isChecked());
        });
        CAD.setOnClickListener(view -> {
            CAD.setChecked(!CAD.isChecked());
        });
        DEP.setOnClickListener(view -> {
            DEP.setChecked(!DEP.isChecked());
        });
        EAD.setOnClickListener(view -> {
            EAD.setChecked(!EAD.isChecked());
        });
        FAC.setOnClickListener(view -> {
            FAC.setChecked(!FAC.isChecked());
        });
        GRL.setOnClickListener(view -> {
            GRL.setChecked(!GRL.isChecked());
        });
        LGB.setOnClickListener(view -> {
            LGB.setChecked(!LGB.isChecked());
        });
        MID.setOnClickListener(view -> {
            MID.setChecked(!MID.isChecked());
        });
        PAR.setOnClickListener(view -> {
            PAR.setChecked(!PAR.isChecked());
        });
        REL.setOnClickListener(view -> {
            REL.setChecked(!REL.isChecked());
        });
        SEE.setOnClickListener(view -> {
            SEE.setChecked(!SEE.isChecked());
        });
        SLD.setOnClickListener(view -> {
            SLD.setChecked(!SLD.isChecked());
        });
        STR.setOnClickListener(view -> {
            STR.setChecked(!STR.isChecked());
        });
        TRA.setOnClickListener(view -> {
            TRA.setChecked(!TRA.isChecked());
        });
    }

    public void addproblem(String Problem){
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/counsellor_problems.php?").newBuilder();
        urlBuilder.addQueryParameter("Counsellor_ID", Counsellor_ID);
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
