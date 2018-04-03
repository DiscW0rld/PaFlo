package com.example.florian.projekt;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.*;

public class Quiz extends AppCompatActivity {

   public void mischen() {
        for (int i = 1; i < 50; i++) {

            int x = (int) (4 * Math.random());
            int y = (int) (4 * Math.random());
            String Puffer = antwortfragen[x];
            antwortfragen[x] = antwortfragen[y];
            antwortfragen[y] = Puffer;
        }
    }
    String[] fragen = {"Ein Alphabet ist ...", "Frage 2", "Frage 3"};
    String[] antworten = {"eine endliche Menge Buchstaben", "eine unendliche Menge Buchstaben", "eine endliche Menge von Wörtern", "eine endliche Menge von Wörtern und Buchstaben"};
    String[] richtigeAntwort = {"eine endliche Menge Buchstaben"};
    String[] antwortfragen = {antworten[0], antworten[1] , antworten[2], antworten[3]};



    int zufall = (int) (5*Math.random());
    int[] reihenfolge = {1,2,3};
    //shuffleArray(reihenfolge);
    int richtig,gesamt,fra;
    TextView frage, richtigefragen, gesamtfragen;
    Button button1,button2,button3,button4,naechsteFrage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        richtig = 0;
        gesamt = 0;
        fra = 0;

        richtigefragen = (TextView) findViewById(R.id.richtigefragen);
        gesamtfragen = (TextView) findViewById(R.id.gesamtfragen);
        frage = (TextView) findViewById(R.id.frage);
        frage.setText(fragen[fra]);
        mischen();
        button1 = (Button) findViewById(R.id.antwort_a);
        button1.setText(antwortfragen[0]);
        button2 = (Button) findViewById(R.id.antwort_b);
        button2.setText(antwortfragen[1]);
        button3 = (Button) findViewById(R.id.antwort_c);
        button3.setText(antwortfragen[2]);
        button4 = (Button) findViewById(R.id.antwort_d);
        button4.setText(antwortfragen[3]);
        naechsteFrage = (Button) findViewById(R.id.naechsteFrage);
        naechsteFrage.setEnabled(false);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (button1.getText().equals(richtigeAntwort[0])) {
                    button1.setBackgroundColor(0xFF00FF00);
                    richtig++;
                }
                else

                    button1.setBackgroundColor(Color.RED);

                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                naechsteFrage.setEnabled(true);
                gesamt++;
                richtigefragen.setText("richtige beantwortete Fragen: " + richtig);
                gesamtfragen.setText("Anzahl Fragen: " + gesamt);
            }

        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (button2.getText().equals(richtigeAntwort[0])) {
                    button2.setBackgroundColor(Color.GREEN);
                    richtig++;
                }
                else

                    button2.setBackgroundColor(Color.RED);

                button1.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                naechsteFrage.setEnabled(true);
                gesamt++;
                richtigefragen.setText("richtige beantwortete Fragen: " + richtig);
                gesamtfragen.setText("Anzahl Fragen: " + gesamt);
            }

        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (button3.getText().equals(richtigeAntwort[0])) {
                    button3.setBackgroundColor(0xFF00FF00);
                    richtig++;
                }
                else

                    button3.setBackgroundColor(Color.RED);

                button1.setEnabled(false);
                button2.setEnabled(false);
                button4.setEnabled(false);
                naechsteFrage.setEnabled(true);
                gesamt++;
                richtigefragen.setText("richtige beantwortete Fragen: " + richtig);
                gesamtfragen.setText("Anzahl Fragen: " + gesamt);
            }

        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (button4.getText().equals(richtigeAntwort[0])) {
                    button4.setBackgroundColor(0xFF00FF00);
                    richtig++;
                }
                else

                    button4.setBackgroundColor(Color.RED);

                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                naechsteFrage.setEnabled(true);
                gesamt++;
                richtigefragen.setText("richtige beantwortete Fragen: " + richtig);
                gesamtfragen.setText("Anzahl Fragen: " + gesamt);
            }

        });

        naechsteFrage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fra++;
                frage.setText(fragen[fra]);
                naechsteFrage.setEnabled(false);
                button1.setBackgroundColor(Color.DKGRAY);
                button2.setBackgroundColor(Color.DKGRAY);
                button3.setBackgroundColor(Color.DKGRAY);
                button4.setBackgroundColor(Color.DKGRAY);
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                button4.setEnabled(true);
            }

        });



    }

}
