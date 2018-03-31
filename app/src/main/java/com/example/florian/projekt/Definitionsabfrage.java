package com.example.florian.projekt;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Definitionsabfrage extends AppCompatActivity {


    TextView besch, defTitel;
    Button aufdecken, neueDef;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definitionsabfrage);


        besch = (TextView) findViewById(R.id.defText);
        defTitel = (TextView) findViewById(R.id.defName);
        aufdecken = (Button) findViewById(R.id.aufdecken);
        neueDef = (Button) findViewById(R.id.neueDef);
        Resources res = getResources();
        final String[] defNamen = res.getStringArray(R.array.definitionen);
        final String[] beschreibung = res.getStringArray(R.array.erklaerungen);
        final int zufall = (int) (3 * Math.random());
        defTitel.setText(defNamen[zufall]);


       aufdecken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                besch.setText(beschreibung[zufall]);

            }

        });

        neueDef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int zufall = (int) (3 * Math.random());
                defTitel.setText(defNamen[zufall]);
                besch.setText("");

            }

        });

    }
}
