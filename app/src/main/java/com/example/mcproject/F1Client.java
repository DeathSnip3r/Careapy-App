package com.example.mcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class F1Client extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_f1_client, container, false);


        View rootView = inflater.inflate(R.layout.fragment_f1_client, container, false);
        Spinner  spinnerLanguages= rootView.findViewById(R.id.spinnerLanguage);

        //ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(, R.array.languages, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);


        return inflater.inflate(R.layout.fragment_f1_client, container, false);


    }
}



