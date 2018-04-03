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

    private Spinner spinner; //der Spinner muss noch Einträge kriegen!!
    private TextView besch;
    private TextView defTitel;

    DefinitionXmlParser definitionXmlParser = new DefinitionXmlParser();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);


        //mittelfristig kommt die xml von online

        XmlPullParser parser = getApplicationContext().getResources().getXml(R.xml.definitionen);

        spinner = (Spinner) findViewById(R.id.defAuswahl);
        besch = (TextView) findViewById(R.id.defText);
        defTitel = (TextView) findViewById(R.id.defName);
        List<DefinitionXmlParser.DefinitionEntry> defTitelData = new ArrayList<>();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.definitionen, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

            //defTitelData = definitionXmlParser.parse(parser);

        Resources res = getResources();
        final String[] defNamen = res.getStringArray(R.array.definitionen);
        final String[] beschreibung = res.getStringArray(R.array.erklaerungen);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               defTitel.setText(defNamen[position]);
               besch.setText(beschreibung[position]);
            }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }
}
