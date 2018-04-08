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

import java.util.ArrayList;
import java.util.List;

public class DownloadActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        final Button defDownload = (Button) findViewById(R.id.download_definition);
        final EditText defLink = (EditText) findViewById(R.id.neue_def_edit);
        final Spinner defSpinner = (Spinner) findViewById(R.id.new_def_spinner);
        final Button defDownloadStart = (Button) findViewById(R.id.download_def_button);
        final Button quizDownload = (Button) findViewById(R.id.download_quiz);
        final EditText quizLink = (EditText) findViewById(R.id.neues_quiz_edit);
        final Spinner quizSpinner = (Spinner) findViewById(R.id.new_quiz_spinner);
        final Button quizDownloadStart = (Button) findViewById(R.id.download_quiz_button);
        List<String> def_spinner_array = new ArrayList<String>();
        final List<GeneralXmlParser.Index> defIndex = GeneralXmlParser.getIndex("defIndex.xml", getApplicationContext());
        for (int i = 0; i<defIndex.size(); ++i){
            def_spinner_array.add(i, defIndex.get(i).name);
        }
        //def_spinner_array = GeneralXmlParser.getIndex("defIndex.xml", getApplicationContext()).name;
        ArrayAdapter<String> downloadable_defs = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, def_spinner_array);
        defSpinner.setAdapter(downloadable_defs);


        defSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                defLink.setText(defSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }


        });

        defDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.download_definition){

                    defLink.setEnabled(true);
                    defSpinner.setVisibility(View.VISIBLE);
                    defDownloadStart.setEnabled(true);
                    quizLink.setEnabled(false);
                    quizSpinner.setVisibility(View.INVISIBLE);
                    quizDownloadStart.setEnabled(false);
                }
            }

        });


        defDownloadStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!defLink.getText().toString().equals(defSpinner.getSelectedItem().toString())) {
                    AddNewXml.downloadXml(defLink.getText().toString(), getApplicationContext());
                }
                else AddNewXml.downloadXml(defIndex.get(defSpinner.getSelectedItemPosition()).url, getApplicationContext());
            }
        });


        List<String> quiz_spinner_array = new ArrayList<String>();
        final List<GeneralXmlParser.Index> quizIndex = GeneralXmlParser.getIndex("quizIndex.xml", getApplicationContext());
        for (int i = 0; i<defIndex.size(); ++i){
            quiz_spinner_array.add(i, quizIndex.get(i).name);
        }

        //Füllen des quizSpinners
        final ArrayAdapter<String> downloadable_quizzes = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, quiz_spinner_array);
        quizSpinner.setAdapter(downloadable_quizzes);

        quizSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                quizLink.setText(quizSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }


        });




        quizDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.download_quiz){

                    defLink.setEnabled(false);
                    defSpinner.setVisibility(View.INVISIBLE);
                    defDownloadStart.setEnabled(false);
                    quizLink.setEnabled(true);
                    quizSpinner.setVisibility(View.VISIBLE);
                    quizDownloadStart.setEnabled(true);
                    quizSpinner.setAdapter(downloadable_quizzes);

                    /*Intent intent = new Intent(DownloadActivity.this, DefinitionsAuswahl.class);
                    startActivity(intent);*/
                }
            }

        });

        quizDownloadStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // falls der Text, der im EditText steht, nicht mit dem ausgewählten defSpinner-Eintrag übereinstimmt,
                // wird davon ausgegangen, dass der User einen eigenen xml-Link eingegeben hat.
                //dann wird der eingegebene Link downgeloadet
                if (!quizLink.getText().toString().equals(quizSpinner.getSelectedItem().toString())) {
                    AddNewXml.downloadXml(quizLink.getText().toString(), getApplicationContext());
                }
                else AddNewXml.downloadXml(quizIndex.get(quizSpinner.getSelectedItemPosition()).url, getApplicationContext());
            }
        });


    }



}
