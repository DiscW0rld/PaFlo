package com.example.florian.projekt;


import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


// Was soll hier passieren?
//  anhand eines Links ein Quiz herunterladen, an den Parser schicken und schlussendlich anzeigen
//  (Aufruf von activity_quiz)
// Was ist ausgelagert?
//  das Parsen, Downloaden und Speichern,
public class AddNewQuiz extends DownloadAndSaveXml{

    public AddNewQuiz(){

        xmlURL = this.xmlURL;
    }
    TextView downloadtext;
    private Button button_a, button_b, button_c, button_d;
    private ProgressDialog pDialog;
    private String filename = "";

    private static String xmlURL = "https://raw.githubusercontent.com/DiscW0rld/PaFlo-quiz/master/quiz_automaten.xml";

    //um von außen auf den private String zugreifen zu können
    public static void setURL(String urlString){
        xmlURL = urlString;
    }
    public void setFileName(String newName) { filename = newName; }

    public boolean download(AsyncStuff bla){
        // Execute DownloadXML AsyncTask
        //setURL();
        try {
            new DownloadXML().execute(bla);
        } catch (Exception e){
            Log.w("download", "Download muss fehlgeschlagen haben");
            return false;
        }
        //downloadtext.setText("erfolgreich heruntergeladen!");
        /*try {
            wait(3000);
        } catch (InterruptedException e) {
            Log.w("wait", "hat nicht geklappt");
            e.printStackTrace();
        }*/
        return true;
    }
    //erstellt das Fenster
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_appziel);
        //downloadtext.setText("Download läuft");
        //downloadtext = (TextView) findViewById(R.id.AppInfos);

        // Execute DownloadXML AsyncTask
        //setURL();
        new DownloadXML().execute(xmlURL);

        //downloadtext.setText("erfolgreich heruntergeladen!");
        try {
            wait(3000);
        } catch (InterruptedException e) {
            Log.w("wait", "hat nicht geklappt");
            e.printStackTrace();
        }

        //Intent intent = new Intent(AddNewQuiz.this, QuizAuswahl.class);
        //startActivity(intent);


    }*/
}
