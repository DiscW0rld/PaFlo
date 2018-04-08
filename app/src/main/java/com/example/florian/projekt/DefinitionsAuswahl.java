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

public class DefinitionsAuswahl extends AppCompatActivity{


    public static String linkname;
    TextView geladeneDatein;
    Button startDefinition,downloadDefinition;
    Spinner downloaded_definition;

    public static String getLink(){
        return linkname;
    }


    public void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_file);

        geladeneDatein = (TextView) findViewById(R.id.heruntergeladene_datein);
        geladeneDatein.setText("Vorhandene Definitionspakete");
        downloadDefinition = (Button) findViewById(R.id.download_datei);
        downloadDefinition.setText("Neues Definitionspaket herunterladen");
        startDefinition = (Button) findViewById(R.id.start_datei);
        startDefinition.setText("Ausgewähltes Definitionspaket starten");
        downloaded_definition = (Spinner) findViewById(R.id.spinner_heruntergeladene_datein);
        // quizlink = (EditText) findViewById(R.id.neues_quiz);
        //Log.w("FileListe", files[0]);

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
        downloaded_definition.setAdapter(adapter2);

        downloaded_definition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                startDefinition.setText(downloaded_definition.getSelectedItem().toString()+ " starten!");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }


        });
        startDefinition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.start_datei){

                    linkname = downloaded_definition.getSelectedItem().toString();
                    startDefinition.setText("Definitionspaket lädt");
                    /*Quiz.SingleQuiz startedQuiz = new Quiz.SingleQuiz(linkname);
                    List<QuizXmlParser.QuizEntry> getQuiz = QuizXmlParser.getQuiz(linkname);*/

                    Intent intent = new Intent(DefinitionsAuswahl.this, Definition.class);
                    startActivity(intent);
                    startDefinition.setText("Ausgewähltes Definitionspaket starten");


                }
            }

        });

        downloadDefinition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.download_datei){



                    Intent intent = new Intent(DefinitionsAuswahl.this, DownloadActivity.class);
                    startActivity(intent);



                }
            }

        });

    }

}



    /*EditText deflink;
    Spinner deflinkspinner;
    public static String linkname;
    Button startDefinition;
    Spinner downloaded_definitions;
    Button download;


    public static String getLink(){
        return linkname;
    }

    public void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_file);

        download = (Button) findViewById(R.id.download_datei);
        startDefinition = (Button) findViewById(R.id.start_datei);
        deflinkspinner = (Spinner) findViewById(R.id.spinner_heruntergeladene_datein);
        //deflink = (EditText) findViewById(R.id.neue_datei);
        //Log.w("FileListe", files[0]);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.quizlinks, android.R.layout.simple_spinner_item);
        deflinkspinner.setAdapter(adapter);

        //bei Auswahl eines Quizlinks wird im EditText dieser Link angezeigt;
        //dann soll man auf "Download!" klicken und der Text im EditText wird dann als Link verwendet,
        //der dann heruntergeladen und gespeichert wird.
        deflinkspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                deflink.setText(deflinkspinner.getSelectedItem().toString());
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

                if(ce == R.id.download_datei){
                    download.setText("lädt herunter");
                    /*AddNewXml downloaden = new AddNewXml();
                    DownloadAndSaveXml.AsyncStuff asyncData = new DownloadAndSaveXml.AsyncStuff(quizlinkspinner.getSelectedItem().toString(), getApplicationContext());
                    boolean successful = downloaden.download(asyncData);
                    if (successful){
                        download.setText("erfolgreich!");
                    } else download.setText("das hat nicht geklappt... :(");
                    AddNewXml.downloadXml(deflinkspinner.getSelectedItem().toString(), getApplicationContext());
                    download.setText("Fertig! Nächster Download?");
                }
            }

        });

        //man wählt ein bereits heruntergeladenes Quiz aus.
        //Es wird der Name der Datei angezeigt, die man öffnen möchte.
        //Mit Klick auf "Start Quiz!" wird diese Datei
        //im Quizordner herausgesucht, geparst und verwendet.
        downloaded_definitions = (Spinner) findViewById(R.id.heruntergeladene_definitionen);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.heruntergeladen, android.R.layout.simple_spinner_item);
        downloaded_definitions.setAdapter(adapter2);

        downloaded_definitions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                startDefinition.setText(downloaded_definitions.getSelectedItem().toString() + " starten!");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }


        });
        startDefinition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.start_definition){

                    linkname = downloaded_definitions.getSelectedItem().toString();
                    startDefinition.setText("Definitionen laden");

                    Intent intent = new Intent(DefinitionsAuswahl.this, Definition.class);
                    startActivity(intent);
                }
            }

        });

    }
*/

