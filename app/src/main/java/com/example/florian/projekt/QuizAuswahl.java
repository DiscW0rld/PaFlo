package com.example.florian.projekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class QuizAuswahl extends AppCompatActivity{

    EditText quizlink;
    Spinner quizlinkspinner;
    public static String linkname;
    Button startQuiz;
    Spinner downloaded_quizzes;

    public static String getLink(){
        return linkname;
    }

    public void onCreate(Bundle savedInstanceState) {

        Button download;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_quiz);

        download = (Button) findViewById(R.id.download_button);
        startQuiz = (Button) findViewById(R.id.start_quiz);
        quizlinkspinner = (Spinner) findViewById(R.id.new_quiz);
        quizlink = (EditText) findViewById(R.id.neues_quiz);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
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


        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.download_button){

                    Intent intent = new Intent(QuizAuswahl.this, AddNewQuiz.class);
                    startActivity(intent);
                }
            }

        });

        //man wählt ein bereits heruntergeladenes Quiz aus.
        //Es wird der Name der Datei angezeigt, die man öffnen möchte.
        //Mit Klick auf "Start Quiz!" wird diese Datei
        //im Quizordner herausgesucht, geparst und verwendet.
        downloaded_quizzes = (Spinner) findViewById(R.id.offline_quizzes);
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

                if(ce == R.id.start_quiz){

                    linkname = downloaded_quizzes.getSelectedItem().toString();
                    //.getFileInput(downloaded_quizzes.getSelectedItem().toString());

                    Intent intent = new Intent(QuizAuswahl.this, Quiz.class);
                    startActivity(intent);


                }
            }

        });

    }

}
