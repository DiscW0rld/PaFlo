package com.example.florian.projekt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Definition extends AppCompatActivity {

    String[] names = {"Alphabet" , " Wortlänge" };
    String[] beschreibung = {" ist eine endliche Menge von Buchstaben " , " Länge eines Wortes " };
    Spinner spinner;
    TextView besch;
    ArrayAdapter<String> adapter;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

       spinner = (Spinner) findViewById(R.id.spinner);
       besch = (TextView) findViewById(R.id.textview);
       adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,names);
       spinner.setAdapter(adapter);

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
