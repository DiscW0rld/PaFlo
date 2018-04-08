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

public class DefinitionsAuswahl extends AppCompatActivity{

    EditText deflink;
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
        setContentView(R.layout.activity_choose_quiz);

        download = (Button) findViewById(R.id.download_button);
        startDefinition = (Button) findViewById(R.id.start_datei);
        deflinkspinner = (Spinner) findViewById(R.id.new_file);
        deflink = (EditText) findViewById(R.id.neue_datei);
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

                if(ce == R.id.download_button){
                    download.setText("lädt herunter");
                    /*AddNewXml downloaden = new AddNewXml();
                    DownloadAndSaveXml.AsyncStuff asyncData = new DownloadAndSaveXml.AsyncStuff(quizlinkspinner.getSelectedItem().toString(), getApplicationContext());
                    boolean successful = downloaden.download(asyncData);
                    if (successful){
                        download.setText("erfolgreich!");
                    } else download.setText("das hat nicht geklappt... :(");*/
                    AddNewXml.downloadXml(deflinkspinner.getSelectedItem().toString(), getApplicationContext());
                    download.setText("Fertig! Nächster Download?");
                }
            }

        });

        //man wählt ein bereits heruntergeladenes Quiz aus.
        //Es wird der Name der Datei angezeigt, die man öffnen möchte.
        //Mit Klick auf "Start Quiz!" wird diese Datei
        //im Quizordner herausgesucht, geparst und verwendet.
        downloaded_definitions = (Spinner) findViewById(R.id.offline_dateien);
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

                if(ce == R.id.start_datei){

                    linkname = downloaded_definitions.getSelectedItem().toString();
                    startDefinition.setText("Definitionen laden");

                    Intent intent = new Intent(DefinitionsAuswahl.this, Definition.class);
                    startActivity(intent);
                }
            }

        });

    }

}
