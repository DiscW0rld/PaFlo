package com.example.florian.projekt;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


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

public class DownloadAndSaveXml extends AppCompatActivity{

    public static class AsyncStuff{
        String url;
        Context cont;
        public AsyncStuff(String url, Context cont){
            this.url = url;
            this.cont = cont;
        }

    }
        private String filename = "m";
        ProgressDialog pDialog;

        //private static String xmlURL = "https://raw.githubusercontent.com/DiscW0rld/PaFlo-quiz/master/quiz_automaten.xml";

        //um von außen auf die privaten Strings zugreifen zu können
        public void setFileName(String newName) {
                filename = newName; }


        public class DownloadXML extends AsyncTask<AsyncStuff, Context, Boolean>{

                private String xmlURL = "b";
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
                protected Boolean doInBackground(AsyncStuff... xmlURL) {

                    boolean successful = false;
                        HttpURLConnection urlConnection = null;
                        String result = "";
                        //FileOutputStream foStream;



                        //Context context = QuizAuswahl.getApplicationContext();
                        try {
                                URL url = new URL(xmlURL[0].url);
                                Context cont = xmlURL[0].cont;

                            urlConnection = (HttpURLConnection) url.openConnection();

                            int code = urlConnection.getResponseCode();

                            if(code==200){
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
                                if (foStream == null){
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
        }


        public void saveXml(Context context, String dokument, String xmlName) {
                FileOutputStream foStream = null;
                filename = "data";
                File file = new File(context.getFilesDir(), filename);

                try {

                        //was macht context.??
                        foStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        //File file = new File(getFilesDir() +  "/data/", xmlName);

                        Log.w("Dokumentinhalt", dokument);
                        foStream.write(dokument.getBytes());
                        //das eigentliche Speichern....?
                        foStream.close();

                } catch (Exception e) {
                        Log.d("saveXml", "ERROR, fatal, emergency, fatal!!!1!11");
                        e.printStackTrace();
                }
        }

       /*public void saveXml(Document doc, String xmlName) {
                FileOutputStream foStream = null;

                try {

                        //was macht context.??
                        foStream = getApplicationContext().openFileOutput(xmlName, Context.MODE_PRIVATE);
                        //File file = new File(getFilesDir() +  "/data/", xmlName);

                        //hier muss noch was passieren... toString() macht Blödsinn draus.
                        String dokument = doc.toString();
                        Log.w("Dokumentinhalt", dokument);
                        foStream.write(dokument.getBytes());
                        //das eigentliche Speichern....?
                        foStream.close();

                } catch (Exception e) {
                        Log.d("saveXml", "ERROR, fatal, emergency, fatal!!!1!11");
                        e.printStackTrace();
                }
        }*/


}
