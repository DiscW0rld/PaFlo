package com.example.florian.projekt;


import android.app.ProgressDialog;

import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;


// Was soll hier passieren?
//  anhand eines Links ein Quiz herunterladen, an den Parser schicken und schlussendlich anzeigen
//  (Aufruf von activity_quiz)
// Was ist ausgelagert?
//  das Parsen, Downloaden und Speichern,
public class AddNewQuiz extends DownloadAndSaveXml{

    TextView fragestellung;
    private Button button_a, button_b, button_c, button_d;
    private ProgressDialog pDialog;
    private String filename = "";

    private static String xmlURL = "https://raw.githubusercontent.com/DiscW0rld/PaFlo-quiz/master/quiz_automaten.xml";

    //um von außen auf den private String zugreifen zu können
    public static void setURL(String urlString){
        xmlURL = urlString;
    }
    public void setFileName(String newName) { filename = newName; }

    //erstellt das Fenster
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        fragestellung = (TextView) findViewById(R.id.frage);
        // Execute DownloadXML AsyncTask
        //setURL();
        new DownloadXML().execute(xmlURL);


    }

    /*private class DownloadXML extends AsyncTask<String, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressbar
            pDialog = new ProgressDialog(AddNewQuiz.this);
            // Set progressbar title
            pDialog.setTitle("Download");
            // Set progressbar message
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            // Show progressbar
            pDialog.show();
        }

        @Override
        protected Void doInBackground(String... xmlURL) {

            try {
                URL url = new URL(xmlURL[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                // Download the XML file
                Document doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

                setFileName(doc.getElementById("explicit_filename").toString());
                saveXml(getApplicationContext(), doc, filename);

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
    }
    public void saveXml(Context context, Document doc, String xmlName) {
        FileOutputStream foStream;
        try {
            foStream = context.openFileOutput(xmlName, Context.MODE_PRIVATE);
            //das eigentliche Speichern....?
            foStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 2, Something went wrong!");
            e.printStackTrace();
        }
    }
*/

}
