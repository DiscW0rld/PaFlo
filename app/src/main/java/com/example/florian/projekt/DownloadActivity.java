package com.example.florian.projekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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



        defSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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
                    defSpinner.setEnabled(true);
                    defDownloadStart.setVisibility(View.VISIBLE);
                    quizLink.setEnabled(false);
                    quizSpinner.setVisibility(View.INVISIBLE);
                    quizDownloadStart.setEnabled(false);
                    /*Intent intent = new Intent(DownloadActivity.this, DefinitionsAuswahl.class);
                    startActivity(intent);*/
                }
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
                    /*Intent intent = new Intent(DownloadActivity.this, DefinitionsAuswahl.class);
                    startActivity(intent);*/
                }
            }

        });

    }



}
