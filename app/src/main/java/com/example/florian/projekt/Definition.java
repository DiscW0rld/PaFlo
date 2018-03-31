package com.example.florian.projekt;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Definition extends AppCompatActivity {
    Definition definition = new Definition();

    private Spinner spinner; //der Spinner muss noch Einträge kriegen!!
    private TextView besch;
    private TextView defTitel;
    private List<DefinitionXmlParser.DefinitionEntry> defTitelData = new ArrayList<DefinitionXmlParser.DefinitionEntry>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);


        //mittelfristig kommt die xml von online
        XmlPullParser parser = getApplicationContext().getResources().getXml(R.xml.definitionen);

        spinner = (Spinner) findViewById(R.id.defAuswahl);
        besch = (TextView) findViewById(R.id.defText);
        defTitel = (TextView) findViewById(R.id.defName);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.definitionen, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


        defTitelData = definition.DefinitionXmlParser.readDefinitions(parser);


        Resources res = getResources();
        final String[] defNamen = res.getStringArray(R.array.definitionen);
        final String[] beschreibung = res.getStringArray(R.array.erklaerungen);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               defTitel.setText(defNamen[position]);
               besch.setText(beschreibung[position]);

               /*switch (position){

                   case 0:
                       defTitel.setText(defNamen[position]);
                       besch.setText(beschreibung[position]);
                       break;

                   case 1:
                       defTitel.setText(defNamen[position]);
                       besch.setText(beschreibung[position]);
                       break;
                   case 2:
                       defTitel.setText(defNamen[position]);
                       besch.setText(beschreibung[position]);
                       break;

                   case 3:
                       defTitel.setText(defNamen[position]);
                       besch.setText(beschreibung[position]);
                       break;
                   case 4:
                       defTitel.setText(defNamen[position]);
                       besch.setText(beschreibung[position]);
                       break;

                   case 5:
                       defTitel.setText(defNamen[position]);
                       besch.setText(beschreibung[position]);
                       break;
                   case 6:
                       defTitel.setText(defNamen[position]);
                       besch.setText(beschreibung[position]);
                       break;

                   case 7:
                       defTitel.setText(defNamen[position]);
                       besch.setText(beschreibung[position]);
                       break;
                   case 8:
                       defTitel.setText(defNamen[position]);
                       besch.setText(beschreibung[position]);
                       break;

                   case 9:
                       defTitel.setText(defNamen[position]);
                       besch.setText(beschreibung[position]);
                       break;
                   case 10:
                       defTitel.setText(defNamen[position]);
                       besch.setText(beschreibung[position]);
                       break;

                   case 11:
                       defTitel.setText(defNamen[position]);
                       besch.setText(beschreibung[position]);
                       break;
               }*/
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }
}
