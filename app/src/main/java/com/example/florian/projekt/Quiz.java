package com.example.florian.projekt;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;

import java.util.*;



//in dieser Klasse wird das Quiz durchgeführt;
//dazu wird eine Quizdatei (filename) eingelesen,
//an QuizXmlParser übergeben und
//das geparste Quiz wird in eine verarbeitungsfreundliche Form gebracht.
public class Quiz extends AppCompatActivity {

    public static class SingleQuiz {
        String filename;

        public SingleQuiz(String filename) {
            this.filename = filename;
        }
    }

    public List<QuizXmlParser.QuizEntry> parseQuiz(SingleQuiz singleQuiz){
        List<QuizXmlParser.QuizEntry> thisQuiz = null;
        try{
            FileInputStream fis = getApplicationContext().openFileInput("hello.txt");
            //InputStreamReader isr = new InputStreamReader(fis);
            thisQuiz = QuizXmlParser.parse(fis);
        }
        catch (Exception e){
            Log.e("File not found", "right Filename?");
        }

        return thisQuiz;
    }


    List<QuizXmlParser.QuizEntry> testQuiz = QuizXmlParser.getExample();


    private String[] shuffleArray(String[] antwortfragen){
        for (int i = antwortfragen.length -1; i>-1; --i) {
           int j = (int) (i * Math.random());
            String helper = antwortfragen[i];
            antwortfragen[i] = antwortfragen[j];
            antwortfragen[j] = helper;
        }
        return antwortfragen;
    }

    String[] fragen = {"Ein Alphabet ist ...", "Frage 2", "Frage 3"};
    String[] antworten = {"eine endliche Menge Buchstaben", "eine unendliche Menge Buchstaben", "eine endliche Menge von Wörtern", "eine endliche Menge von Wörtern und Buchstaben"};
    String[] richtigeAntwort = {"eine endliche Menge Buchstaben"};
    String[] antwortfragen = {antworten[0], antworten[1] , antworten[2], antworten[3]};

    //String[] fragen = new String[testQuiz.size()];
    TextView frage, richtigefragen, gesamtfragen;
    Button button1,button2,button3,button4,naechsteFrage, quizauswahl, hauptmenu;
    int richtig,gesamt,fra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        String link = QuizAuswahl.getLink();
        List<QuizXmlParser.QuizEntry> thisQuiz = QuizXmlParser.getQuiz(link, getApplicationContext());


        /*for (int i=0; i<testQuiz.size(); ++i){
            fragen[i] = testQuiz.get(i).question;
        }*/

        richtig = 0;
        gesamt = 1;
        fra = 0;

        String cookie = thisQuiz.get(0).question;
        String cake = cookie;
        cookie = thisQuiz.get(0).rightAns;
        cake = cookie;
        cookie = thisQuiz.get(0).wrAns1;
        cake = cookie;
        cookie = thisQuiz.get(0).wrAns2;
        cake = cookie;
        cookie = thisQuiz.get(0).wrAns3;
        cake = cookie;
        cookie = thisQuiz.get(1).question;
        cake = cookie;
        richtigefragen = (TextView) findViewById(R.id.richtigefragen);
        richtigefragen.setText("richtige beantwortete Fragen: " + richtig);
        gesamtfragen = (TextView) findViewById(R.id.gesamtfragen);
        gesamtfragen.setText("Frage: " + gesamt + " von 3");
        frage = (TextView) findViewById(R.id.frage);
        frage.setText(fragen[fra]);
        antwortfragen = shuffleArray(antwortfragen);
        //mischen();
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
        quizauswahl = (Button) findViewById(R.id.quizauswahl);
        quizauswahl.setEnabled(false);
        quizauswahl.setVisibility(View.INVISIBLE);
        hauptmenu = (Button) findViewById(R.id.hauptmenu);
        hauptmenu.setEnabled(false);
        hauptmenu.setVisibility(View.INVISIBLE);
        List <QuizXmlParser.QuizEntry> namengeben = new ArrayList<>();
        //namengeben = QuizXmlParser.getexample();


            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (button1.getText().equals(richtigeAntwort[0])) {
                        button1.setBackgroundColor(0xFF00FF00);
                        richtig++;
                    } else

                        button1.setBackgroundColor(Color.RED);

                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);
                    if (fra != 2)
                    naechsteFrage.setEnabled(true);
                    else{
                        quizauswahl.setVisibility(View.VISIBLE);
                        quizauswahl.setEnabled(true);
                        hauptmenu.setVisibility(View.VISIBLE);
                        hauptmenu.setEnabled(true);
                        gesamtfragen.setTextSize(18);
                        richtigefragen.setTextSize(18);
                    }
                    gesamt++;
                    richtigefragen.setText("richtige beantwortete Fragen: " + richtig);

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
                    if (fra != 2)
                        naechsteFrage.setEnabled(true);
                    else{
                        quizauswahl.setVisibility(View.VISIBLE);
                        quizauswahl.setEnabled(true);
                        hauptmenu.setVisibility(View.VISIBLE);
                        hauptmenu.setEnabled(true);
                        gesamtfragen.setTextSize(18);
                        richtigefragen.setTextSize(18);
                    }
                    gesamt++;
                    richtigefragen.setText("richtige beantwortete Fragen: " + richtig);

                }

            });
                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            if (button3.getText().equals(richtigeAntwort[0])) {
                                button3.setBackgroundColor(0xFF00FF00);
                                richtig++;
                            } else

                                button3.setBackgroundColor(Color.RED);

                            button1.setEnabled(false);
                            button2.setEnabled(false);
                            button4.setEnabled(false);
                            if (fra != 2)
                                naechsteFrage.setEnabled(true);
                            else{
                                quizauswahl.setVisibility(View.VISIBLE);
                                quizauswahl.setEnabled(true);
                                hauptmenu.setVisibility(View.VISIBLE);
                                hauptmenu.setEnabled(true);
                                gesamtfragen.setTextSize(18);
                                richtigefragen.setTextSize(18);
                            }
                            gesamt++;
                            richtigefragen.setText("richtige beantwortete Fragen: " + richtig);

                        }

                    });

                    button4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            if (button4.getText().equals(richtigeAntwort[0])) {
                                button4.setBackgroundColor(0xFF00FF00);
                                richtig++;
                            } else

                                button4.setBackgroundColor(Color.RED);

                            button1.setEnabled(false);
                            button2.setEnabled(false);
                            button3.setEnabled(false);
                            if (fra != 2)
                                naechsteFrage.setEnabled(true);
                            else{
                                quizauswahl.setVisibility(View.VISIBLE);
                                quizauswahl.setEnabled(true);
                                hauptmenu.setVisibility(View.VISIBLE);
                                hauptmenu.setEnabled(true);
                                gesamtfragen.setTextSize(18);
                                richtigefragen.setTextSize(18);
                            }
                            gesamt++;
                            richtigefragen.setText("richtige beantwortete Fragen: " + richtig);

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
                            gesamtfragen.setText("Frage: " + gesamt + " von 3");
                        }

                    });

        quizauswahl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.quizauswahl){

                    Intent intent = new Intent(Quiz.this, QuizAuswahl.class);
                    startActivity(intent);
                }
            }

        });

        hauptmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.hauptmenu){

                    Intent intent = new Intent(Quiz.this, MainActivity.class);
                    startActivity(intent);
                }
            }

        });


                }



}
