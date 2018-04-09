package com.example.florian.projekt;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by patricia on 13.03.18.
 */

public class DownloadAndSaveXml extends AppCompatActivity {

    private String filename = "m";
    private ProgressDialog p;

    public static class AsyncStuff {
        String url;
        Context cont;
        private ProgressDialog p;

        public AsyncStuff(String url, Context cont) {
            this.url = url;
            this.cont = cont;
            this.p = new ProgressDialog(cont);
        }

    }


    //private static String xmlURL = "https://raw.githubusercontent.com/DiscW0rld/PaFlo-quiz/master/quiz_automaten.xml";

    //um von außen auf die privaten Strings zugreifen zu können
    public void setFileName(String newName) {
        filename = newName;
    }


    public class DownloadXML extends AsyncTask<AsyncStuff, Context, Boolean> {

        String url;
        Context cont;
        private ProgressDialog p;

        public DownloadXML(AsyncStuff stuffy) {
            this.url = stuffy.url;
            this.cont = stuffy.cont;
            this.p = new ProgressDialog(cont);
        }


        private String xmlURL = "b";

        @Override
        protected void onPreExecute() {

          /*  if (!((url.equals("https://raw.githubusercontent.com/DiscW0rld/PaFlo-definitions/Development/defIndex.xml")||
                    url.equals("https://raw.githubusercontent.com/DiscW0rld/PaFlo-quiz/master/quizIndex.xml"))))
            {
                super.onPreExecute();
                p.setMessage("Datei wird gespeichert");
                p.setIndeterminate(false);
                p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                p.setCancelable(false);
                p.show();
            }*/
        }

        @Override
        protected Boolean doInBackground(AsyncStuff... xmlURL) {

            boolean successful = false;
            HttpURLConnection urlConnection = null;
            String result = "";

            try {
                URL url = new URL(xmlURL[0].url);
                Context cont = xmlURL[0].cont;

                urlConnection = (HttpURLConnection) url.openConnection();

                int code = urlConnection.getResponseCode();

                if (code == 200) {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    if (in != null) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                        String line = "";

                        while ((line = bufferedReader.readLine()) != null)
                            result += line;
                    }
                    in.close();
                }

                DocumentBuilderFactory dbf = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                // Download the XML file
                Document doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

                // das Element finden, das die ID explicit_filename hat,
                // dieses den String zwischen Start- und Endtag konvertieren
                filename = doc.getElementById("explicit_filename").getTextContent();
                FileOutputStream foStream = cont.openFileOutput(filename, MODE_PRIVATE);
                if (foStream == null) {
                    Log.w("foStream", "ist null");
                }

                //das eigentliche Speichern....
                foStream.write(result.getBytes());

                foStream.close();

                successful = true;
                String item = "blub";

            } catch (Exception e) {
                Log.e("Laden Error", e.getMessage());
                e.printStackTrace();
                successful = false;
            }


            return successful;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            //p.dismiss();
            if (!(url.equals("https://raw.githubusercontent.com/DiscW0rld/PaFlo-definitions/Development/defIndex.xml") ||
                    url.equals("https://raw.githubusercontent.com/DiscW0rld/PaFlo-quiz/master/quizIndex.xml"))) {
                if (result)
                    Toast.makeText(cont, "Download beendet!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(cont, "Download ist gescheitert, überprüfe am besten deinen Link und/oder deine Netzwerkverbindung.", Toast.LENGTH_LONG).show();
            }
        }
    }
}