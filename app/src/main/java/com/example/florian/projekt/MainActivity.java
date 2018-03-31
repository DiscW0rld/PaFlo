package com.example.florian.projekt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.*;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button definition = (Button) findViewById(R.id.definition);
        final Button lektion = (Button) findViewById(R.id.lektion);
        final Button abfrage = (Button) findViewById(R.id.abfrage);
        final Button quiz = (Button) findViewById(R.id.quiz);
        final Button beenden = (Button) findViewById(R.id.beenden);

        definition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.definition){

                    Intent intent = new Intent(MainActivity.this, Definition.class);
                    startActivity(intent);
                }
            }

        });

        abfrage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.abfrage){

                    Intent intent = new Intent(MainActivity.this, Definitionsabfrage.class);
                    startActivity(intent);
                }
            }

        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.quiz){

                    Intent intent = new Intent(MainActivity.this, Quiz.class);
                    startActivity(intent);
                }
            }

        });

        beenden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.exit(0);
            }

        });

    }
}
