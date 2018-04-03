package com.example.florian.projekt;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Array;
import java.util.*;

public class Quiz extends AppCompatActivity {

    private String[] shuffleArray(String[] antwortfragen){
        for (int i = antwortfragen.length; i>-1; --i) {
           int j = (int) (i);// * Math.random());
            String helper = antwortfragen[i];
            antwortfragen[i] = antwortfragen[j];
            antwortfragen[j] = helper;
        }
        return antwortfragen;
    }

   /*public void mischen() {
        for (int i = 1; i < 50; i++) {

            int x = (int) (4 * Math.random());
            int y = (int) (4 * Math.random());
            String Puffer = antwortfragen[x];
            antwortfragen[x] = antwortfragen[y];
            antwortfragen[y] = Puffer;
        }
    }*/
    String[] fragen = {"Ein Alphabet ist ...", "Frage 2", "Frage 3"};
    String[] antworten = {"eine endliche Menge Buchstaben", "eine unendliche Menge Buchstaben", "eine endliche Menge von Wörtern", "eine endliche Menge von Wörtern und Buchstaben"};
    String[] richtigeAntwort = {"eine endliche Menge Buchstaben"};
    String[] antwortfragen = {antworten[0], antworten[1] , antworten[2], antworten[3]};





    TextView frage;
    Button button1,button2,button3,button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        frage = (TextView) findViewById(R.id.frage);
        frage.setText(fragen[0]);
        antwortfragen = shuffleArray(antworten);
        button1 = (Button) findViewById(R.id.antwort_a);
        button1.setText(antwortfragen[0]);
        button2 = (Button) findViewById(R.id.antwort_b);
        button2.setText(antwortfragen[1]);
        button3 = (Button) findViewById(R.id.antwort_c);
        button3.setText(antwortfragen[2]);
        button4 = (Button) findViewById(R.id.antwort_d);
        button4.setText(antwortfragen[3]);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button4.setBackgroundResource(android.R.drawable.btn_default);
                button2.setBackgroundResource(android.R.drawable.btn_default);
                button3.setBackgroundResource(android.R.drawable.btn_default);


                if (button1.getText().equals(richtigeAntwort[0]))
                    button1.setBackgroundColor(0xFF00FF00);

                else

                    button1.setBackgroundColor(Color.RED);

            }

        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setBackgroundResource(android.R.drawable.btn_default);
                button4.setBackgroundResource(android.R.drawable.btn_default);
                button3.setBackgroundResource(android.R.drawable.btn_default);


                if (button2.getText().equals(richtigeAntwort[0]))
                    button2.setBackgroundColor(0xFF00FF00);

                else

                    button2.setBackgroundColor(Color.RED);

            }

        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setBackgroundResource(android.R.drawable.btn_default);
                button2.setBackgroundResource(android.R.drawable.btn_default);
                button4.setBackgroundResource(android.R.drawable.btn_default);


                if (button3.getText().equals(richtigeAntwort[0]))
                    button3.setBackgroundColor(0xFF00FF00);

                else

                    button3.setBackgroundColor(Color.RED);
            }

        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button1.setBackgroundResource(android.R.drawable.btn_default);
                button2.setBackgroundResource(android.R.drawable.btn_default);
                button3.setBackgroundResource(android.R.drawable.btn_default);
                if (button4.getText().equals(richtigeAntwort[0]))
                    button4.setBackgroundColor(0xFF00FF00);

                else
                    button4.setBackgroundColor(Color.RED);
            }

        });


/*
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (button4.getText().equals(richtigeAntwort[0]))
                    button4.setBackgroundColor(0xFF00FF00);

                else

                    button4.setBackgroundColor(Color.RED);

            }

        });
*/

    }

}
