package com.example.florian.projekt;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by patricia on 13.03.18.
 */

public class DownloadAndSaveXml extends AppCompatActivity{

        private ProgressDialog pDialog;
        private String filename = "";

        private static String xmlURL = "https://raw.githubusercontent.com/DiscW0rld/PaFlo-quiz/master/quiz_automaten.xml";

        //um von außen auf die privaten Strings zugreifen zu können
        public void setFileName(String newName) { filename = newName; }


        public class DownloadXML extends AsyncTask<String, Void, Void>{

                @Override
                protected void onPreExecute() {
                       /* super.onPreExecute();
                        // Create a progressbar
                        pDialog = new ProgressDialog(getApplicationContext());
                        // Set progressbar title
                        pDialog.setTitle("Download");
                        // Set progressbar message
                        pDialog.setMessage("Loading...");
                        pDialog.setIndeterminate(false);
                        // Show progressbar
                        pDialog.show();*/
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

                                // das Element finden, das die ID explicit_filename hat,
                                // dieses zu String konvertieren
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
                        //File file = new File(getFilesDir() +  "/data/", xmlName);
                        String dokument = doc.toString();
                        foStream.write(dokument.getBytes());
                        //das eigentliche Speichern....?
                        foStream.close();
                } catch (Exception e) {
                        Log.d("saveXml", "ERROR, fatal, emergency, fatal!!!1!11");
                        e.printStackTrace();
                }
        }


}
