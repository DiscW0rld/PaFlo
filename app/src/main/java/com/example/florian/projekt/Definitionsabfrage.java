package com.example.florian.projekt;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

public class Definitionsabfrage extends AppCompatActivity {


    TextView besch, defTitel;
    Button aufdecken, neueDef;
    DefinitionXmlParser definitionXmlParser = new DefinitionXmlParser();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definitionsabfrage);

        XmlPullParser parser = getApplicationContext().getResources().getXml(R.xml.definitionen);

        besch = (TextView) findViewById(R.id.defText);
        defTitel = (TextView) findViewById(R.id.defName);
        aufdecken = (Button) findViewById(R.id.aufdecken);
        neueDef = (Button) findViewById(R.id.neueDef);
        List<DefinitionXmlParser.DefinitionEntry> TestDefabfrage = DefinitionXmlParser.getExampleDef();
        Resources res = getResources();
        final String[] defNamen = new String [TestDefabfrage.size()];
        final String[] beschreibung = new String[TestDefabfrage.size()];

        for (int i=0; i<TestDefabfrage.size(); ++i){
            defNamen[i] = TestDefabfrage.get(i).defTitel;
        }

        for (int i=0; i<TestDefabfrage.size(); ++i){
            beschreibung[i] = TestDefabfrage.get(i).erklaerung;
        }


        final int zufall = (int) (defNamen.length * Math.random());
        defTitel.setText(defNamen[zufall]);
        besch.setText(beschreibung[zufall]);
        besch.setVisibility(View.INVISIBLE);



        neueDef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int zufall = (int) (defNamen.length *Math.random());
                defTitel.setText(defNamen[zufall]);
                besch.setText(beschreibung[zufall]);
                besch.setVisibility(View.INVISIBLE);

            }

        });

        aufdecken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                besch.setVisibility(View.VISIBLE);

            }

        });
    }
}
