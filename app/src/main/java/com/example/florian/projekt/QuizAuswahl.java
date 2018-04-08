package com.example.florian.projekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class QuizAuswahl extends AppCompatActivity{


    public static String linkname;
    TextView geladeneDatein;
    Button startQuiz,downloadQuiz;
    Spinner downloaded_quizzes;

    public static String getLink(){
        return linkname;
    }


    public void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_file);

        geladeneDatein = (TextView) findViewById(R.id.heruntergeladene_datein);
        geladeneDatein.setText("Vorhandene Quizzes");
        downloadQuiz = (Button) findViewById(R.id.download_datei);
        downloadQuiz.setText("Neues Quiz herunterladen");
        startQuiz = (Button) findViewById(R.id.start_datei);
        startQuiz.setText("Ausgewähltes Quiz starten");
        downloaded_quizzes = (Spinner) findViewById(R.id.spinner_heruntergeladene_datein);
       // quizlink = (EditText) findViewById(R.id.neues_quiz);
        //Log.w("FileListe8", files[0]);

        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.quizlinks, android.R.layout.simple_spinner_item);
        quizlinkspinner.setAdapter(adapter);

        //bei Auswahl eines Quizlinks wird im EditText dieser Link angezeigt;
        //dann soll man auf "Download!" klicken und der Text im EditText wird dann als Link verwendet,
        //der dann heruntergeladen und gespeichert wird.
        quizlinkspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                quizlink.setText(quizlinkspinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //quizlink.setText("https://raw.githubusercontent.com/DiscW0rld/PaFlo-quiz/master/quiz_automaten.xml");
            }
b

        }); */
       /* download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.download_button){
                    download.setText("lädt herunter");
                    AddNewQuiz downloaden = new AddNewQuiz();
                    DownloadAndSaveXml.AsyncStuff bla = new DownloadAndSaveXml.AsyncStuff(quizlinkspinner.getSelectedItem().toString(), getApplicationContext());
                    boolean successful = downloaden.download(bla);
                    if (successful){
                        download.setText("erfolgreich!");
                    } else download.setText("das hat nicht geklappt... :(");
                }
            }

        }); */

        //man wählt ein bereits heruntergeladenes Quiz aus.
        //Es wird der Name der Datei angezeigt, die man öffnen möchte.
        //Mit Klick auf "Start Quiz!" wird diese Datei
        //im Quizordner herausgesucht, geparst und verwendet.
       // downloaded_quizzes = (Spinner) findViewById(R.id.offline_quizzes);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.heruntergeladen, android.R.layout.simple_spinner_item);
        downloaded_quizzes.setAdapter(adapter2);

        downloaded_quizzes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                startQuiz.setText(downloaded_quizzes.getSelectedItem().toString()+ " starten!");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }


        });

        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.start_datei){

                    linkname = downloaded_quizzes.getSelectedItem().toString();
                    startQuiz.setText("Quiz lädt");
                   /* Quiz.SingleQuiz startedQuiz = new Quiz.SingleQuiz(linkname);
                    List<QuizXmlParser.QuizEntry> getQuiz = QuizXmlParser.getQuiz(linkname);*/

                    Intent intent = new Intent(QuizAuswahl.this, Quiz.class);
                    startActivity(intent);


                }
            }

        });

        downloadQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.download_datei){


                    Intent intent = new Intent(QuizAuswahl.this, DownloadActivity.class);
                    startActivity(intent);



                }
            }

        });

    }

}
