package com.example.florian.projekt;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * This class parses XML feeds from stackoverflow.com.
 * Given an InputStream representation of a feed, it returns a List of entries,
 * where each list element represents a single entry (post) in the XML feed.
 */
public class GeneralXmlParser {
    static final String ns = null;



    public static List<List<String>> getDownloadedContent(Context context) {

        String[] fileList = context.fileList();
        List<List<String>> downloadedFiles = new ArrayList<>(2);
        List<String> quizzes = new ArrayList<>();
        List<String> defs = new ArrayList<>();

        for (int i = 0; i < fileList.length; ++i) {
            if (fileList[i].startsWith("Quiz_"))
                quizzes.add(fileList[i]);

            else if (fileList[i].startsWith("Def_"))
                defs.add(fileList[i]);
        }

        downloadedFiles.add(quizzes);
        downloadedFiles.add(defs);
        return downloadedFiles;
    }

    public static List<String> getDownloadedQuizzes(Context context){

        return getDownloadedContent(context).get(0);
    }

    public static List<String> getDownloadedDefs(Context context){

        return getDownloadedContent(context).get(1);
    }


    public static class Index{
        String name;
        String url;
        Index(String name, String url){
            this.name = name;
            this.url = url;
        }
    }

    //holt einen (bereits gespeicherten) Index mit dem Dateinamen "name" ab.
    public static List<Index> getIndex(String name, Context context){
        List<Index> index = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(name);
            //InputStreamReader isr = new InputStreamReader(fis);
            index = GeneralXmlParser.parseIndex(fis);
            /*FileInputStream is;
            //is = openFileInput(name);
            is = new FileInputStream(xmlName);
            quizdata = QuizXmlParser.parse(is);*/
        }
        catch(Exception e){
            Log.e("InputStream", e.getMessage());
            e.printStackTrace();
        }
        return index;
    }


    //von getIndex aufgerufen
    // parst eine gespeicherte Indexdatei (InputStream wird übergeben)
    public static List<Index> parseIndex(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readIndex(parser);
        } finally {
            in.close();
        }
    }

    //von parseIndex aufgerufen
    //liest eine Indexdatei aus
    private static List<Index> readIndex(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Index> index = new ArrayList<>();
        Index entry = null;

        parser.require(XmlPullParser.START_TAG, ns, "index");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("entry")) {
                entry = readEntry(parser);
                index.add(entry);
            } else {
                skipTag(parser);
            }
        }

        return index;
    }

    //von readIndex aufgerufen
    //liest einen einzelnen Indexeintrag aus
    private static Index readEntry(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "entry");
        String name = null;
        String url = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String eintrag = parser.getName();
            if (eintrag.equals("name")) {
                name = readName(parser);
            } else if (eintrag.equals("link")) {
                url = readUrl(parser);
            } else {
                skipTag(parser);
            }
        }

        return new Index(name, url);
    }


    private static String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        String name = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return name;
    }

    private static String readUrl(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "link");
        String url = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "link");
        return url;
    }
    /*public static void saveXml(Context context, Document doc, String xmlName) {
        FileOutputStream foStream;
        try {
            foStream = context.openFileOutput(xmlName + ".xml", Context.MODE_PRIVATE);
            doc
            foStream.close();
        } catch (Exception e) {
            Log.d("saveXml", "Fatal, fatal, emergency, ERROR ERROR!!!1!11!");
            e.printStackTrace();
        }
    }*/
    /*// Processes link tags in the feed.
    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
        String link = "";
        parser.require(XmlPullParser.START_TAG, ns, "link");
        String tag = parser.getName();
        String relType = parser.getAttributeValue(null, "rel");
        if (tag.equals("link")) {
            if (relType.equals("alternate")) {
                link = parser.getAttributeValue(null, "href");
                parser.nextTag();
            }
        }
        parser.require(XmlPullParser.END_TAG, ns, "link");
        return link;
    }*/

    // For the tags title and summary, extracts their text values.
    static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    // Skips tags the parser isn't interested in. Uses depth to handle nested tags. i.e.,
    // if the next tag after a START_TAG isn't a matching END_TAG, it keeps going until it
    // finds the matching END_TAG (as indicated by the value of "depth" being 0).
    static void skipTag(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()){
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}

/*
 * Created by patricia on 22.03.18.
 */



/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

