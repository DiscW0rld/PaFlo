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

    String link;
    String[] def, defbeschreibung;
    List<DefinitionXmlParser.DefinitionEntry> thisDef;
    TextView besch, defTitel;
    Button aufdecken, neueDef;
    DefinitionXmlParser definitionXmlParser = new DefinitionXmlParser();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definitionsabfrage);

        XmlPullParser parser = getApplicationContext().getResources().getXml(R.xml.definitionen);
        link = DefinitionsAuswahl.getLink();
        thisDef = DefinitionXmlParser.getDef(link, getApplicationContext());
        besch = (TextView) findViewById(R.id.defText);
        defTitel = (TextView) findViewById(R.id.defName);
        aufdecken = (Button) findViewById(R.id.aufdecken);
        neueDef = (Button) findViewById(R.id.neueDef);
        Resources res = getResources();
        def = new String [thisDef.size()];
        defbeschreibung = new String[thisDef.size()];

        for (int i=0; i<thisDef.size(); ++i){
            def[i] = thisDef.get(i).defTitel;
        }

        for (int i=0; i<thisDef.size(); ++i){
            defbeschreibung[i] =thisDef.get(i).erklaerung;
        }


        final int zufall = (int) (def.length * Math.random());
        defTitel.setText(def[zufall]);
        besch.setText(defbeschreibung[zufall]);
        besch.setVisibility(View.INVISIBLE);



        neueDef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int zufall = (int) (def.length *Math.random());
                defTitel.setText(def[zufall]);
                besch.setText(defbeschreibung[zufall]);
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
