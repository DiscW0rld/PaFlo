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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Definition extends AppCompatActivity{

    String link;
    String [] def, defbeschreibung;
    List<DefinitionXmlParser.DefinitionEntry> thisDef;
    private Spinner spinner; //der Spinner muss noch Einträge kriegen!!
    private TextView besch;
    private TextView defTitel;List<String> spinnerarray = new ArrayList<String>();
    DefinitionXmlParser definitionXmlParser = new DefinitionXmlParser();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);
        link = DefinitionsAuswahl.getLink();
        XmlPullParser parser = getApplicationContext().getResources().getXml(R.xml.definitionen);
        spinner = (Spinner) findViewById(R.id.defAuswahl);
        besch = (TextView) findViewById(R.id.defText);
        defTitel = (TextView) findViewById(R.id.defName);
        def = new String[thisDef.size()];
        defbeschreibung = new String[thisDef.size()];
        List<String> spinnerarray = new ArrayList<String>();

        for (int i=0; i<thisDef.size(); ++i){
            def[i] = thisDef.get(i).defTitel;
        }

        for (int i=0; i<thisDef.size(); ++i){
            defbeschreibung[i] = thisDef.get(i).erklaerung;
        }

        for (int i=0; i<thisDef.size(); ++i){
            spinnerarray.add(thisDef.get(i).defTitel);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerarray);



        spinner.setAdapter(adapter);

            //defTitelData = definitionXmlParser.parse(parser);

        Resources res = getResources();



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               defTitel.setText(def[position]);
               besch.setText(defbeschreibung[position]);
            }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }
}
