package com.example.florian.projekt;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Definition extends AppCompatActivity {

    Spinner spinner;
    TextView besch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

       spinner = (Spinner) findViewById(R.id.spinner);
       besch = (TextView) findViewById(R.id.textview);
       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.definitionen, android.R.layout.simple_spinner_item);
       spinner.setAdapter(adapter);
        Resources res = getResources();
        final String[] beschreibung = res.getStringArray(R.array.erklaerungen);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               switch (position){

                   case 0:

                       besch.setText(beschreibung[position]);
                       break;

                   case 1:

                       besch.setText(beschreibung[position]);
                       break;
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }
}
