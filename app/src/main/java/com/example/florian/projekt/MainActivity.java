package com.example.florian.projekt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.*;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button download_stuff = (Button) findViewById(R.id.neuer_content);
        final Button definition = (Button) findViewById(R.id.definition);
        final Button abfrage = (Button) findViewById(R.id.abfrage);
        final Button zurapp = (Button) findViewById(R.id.zurapp);
        final Button quiz = (Button) findViewById(R.id.quiz);

        download_stuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.neuer_content){

                    Intent intent = new Intent(MainActivity.this, DownloadActivity.class);
                    startActivity(intent);
                }
            }

        });

        definition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.definition){

                    Intent intent = new Intent(MainActivity.this, DefinitionsAuswahl.class);
                    startActivity(intent);
                }
            }

        });


        abfrage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.abfrage){

                    Intent intent = new Intent(MainActivity.this, DefinitionsabfrageAuswahl.class);
                    startActivity(intent);
                }
            }

        });

        zurapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.zurapp){

                    Intent intent = new Intent(MainActivity.this, AppInformationen.class);
                    startActivity(intent);
                }
            }

        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.quiz){


                    Intent intent = new Intent(MainActivity.this, QuizAuswahl.class);
                    startActivity(intent);
                }
            }

        });



    }
}
