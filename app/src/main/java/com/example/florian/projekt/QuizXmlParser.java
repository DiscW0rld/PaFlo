package com.example.florian.projekt;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.w3c.dom.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by patricia on 22.03.18.
 */

public class QuizXmlParser extends GeneralXmlParser {


    public static List<QuizXmlParser.QuizEntry> getQuiz(String xmlName, Context context){
        List<QuizEntry> quizdata = new ArrayList<QuizEntry>();
        try {
            FileInputStream fis = context.openFileInput(xmlName);
            //InputStreamReader isr = new InputStreamReader(fis);
            quizdata = QuizXmlParser.parse(fis);
            /*FileInputStream is;
            //is = openFileInput(name);
            is = new FileInputStream(xmlName);
            quizdata = QuizXmlParser.parse(is);*/
        }
        catch(Exception e){
            Log.e("InputStream", e.getMessage());
            e.printStackTrace();
        }
        return quizdata;
    }


    public static class QuizEntry {
        String question;
        String rightAns;
        String wrAns1;
        String wrAns2;
        String wrAns3;
        QuizEntry(String question, String rightAns, String wrAns1, String wrAns2, String wrAns3) {
            this.question = question;
            this.rightAns = rightAns;
            this.wrAns1 = wrAns1;
            this.wrAns2 = wrAns2;
            this.wrAns3 = wrAns3;
        }
    }


    public static List<QuizEntry> getExample(){
        List<QuizEntry> quiz = new ArrayList<>();
        QuizEntry Frage1 = new QuizEntry("Ein Alphabet ist ...", "eine endliche Menge Buchstaben", "eine unendliche Menge Buchstaben", "eine endliche Menge von Wörtern", "eine endliche Menge von Wörtern und Buchstaben");
        QuizEntry Frage2 = new QuizEntry("Frage 2", "richtige Antwort Frage 2", "1. falsche Antwort Frage 2", "2. falsche Antwort Frage 2", "3. falsche Antwort Frage 2");
        QuizEntry Frage3 = new QuizEntry("Frage 3", "richtige Antwort Frage 3", "1. falsche Antwort Frage 3", "2. falsche Antwort Frage 3", "3. falsche Antwort Frage 3");
        QuizEntry Frage4 = new QuizEntry("Frage 4", "richtige Antwort Frage 4", "1. falsche Antwort Frage 4", "2. falsche Antwort Frage 4", "3. falsche Antwort Frage 4");
        QuizEntry Frage5 = new QuizEntry("Frage 5", "richtige Antwort Frage 5", "1. falsche Antwort Frage 5", "2. falsche Antwort Frage 5", "3. falsche Antwort Frage 5");
        quiz.add(Frage1);
        quiz.add(Frage2);
        quiz.add(Frage3);
        quiz.add(Frage4);
        quiz.add(Frage5);
        return quiz;
    }
    public static List<QuizEntry> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            String bruchpunkt = parser.getText();
            return readQuizzes(parser);
        } finally {
            in.close();
        }
    }
    private static List<QuizEntry> readQuizzes(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<QuizEntry> quizdata = new ArrayList<QuizEntry>();

        parser.require(XmlPullParser.START_TAG, ns, "quiz");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("quizdata")) {
                quizdata.add(readQuiz(parser));
            } else {
                skipTag(parser);
            }
        }
        return quizdata;
    }

    //Funktion findet einzelne Quizfrage und ordnet Frage und Antworten den richtigen Strings zu.
    //gibt eine einzelne Quizfrage zurück,
    //deren Daten in List<QuizEntry> readFeed(..) zu einer Liste aus Quizeinträgen gemacht werden.
    private static QuizEntry readQuiz(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "quizdata");
        String question = null;
        String rightAns = null;
        String wrAns1 = null;
        String wrAns2 = null;
        String wrAns3 = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("question")) {
                question = readQuestion(parser);
            } else if (name.equals("RightAnswer")) {
                rightAns = readRightAns(parser);
            } else if (name.equals("WrAns1")) {
                wrAns1 = readWrAns1(parser);
            } else if (name.equals("WrAns2")) {
                wrAns2 = readWrAns2(parser);
            } else if (name.equals("WrAns3")) {
                wrAns3 = readWrAns3(parser);
            } else {
                skipTag(parser);
            }
        }
        return new QuizEntry(question, rightAns, wrAns1, wrAns2, wrAns3);
    }


    //liest alles innerhalb eines Tags "question", also eine einzelne Frage
    private static String readQuestion(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "question");
        String question = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "question");
        return question;
    }

    //liest die richtige Antwort
    private static String readRightAns(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "RightAnswer");
        String rightAns = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "RightAnswer");
        return rightAns;
    }

    //liest falsche Antworten
    private static String readAnswers(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns,"answers");
        String answers = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "answers");
        return answers;
    }

    private String readQuizName(XmlPullParser parser) throws IOException, XmlPullParserException{
        parser.require(XmlPullParser.START_TAG, ns, "quizname");
        String quizName = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "quizname");
        return quizName;
    }

    private static String readWrAns1(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "WrAns1");
        String wrAns3 = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "WrAns1");
        return wrAns3;
    }

    private static String readWrAns2(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "WrAns2");
        String wrAns3 = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "WrAns2");
        return wrAns3;
    }

    //liest die dritte falsche Antwort
    private static String readWrAns3(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "WrAns3");
        String wrAns3 = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "WrAns3");
        return wrAns3;
    }
    /*public static  List<QuizEntry> getexample() {
        List<QuizEntry> Beispiel = new ArrayList<QuizEntry>();

       // QuizEntry Frage1 = ("A", "B", "C", "D", "e");
        QuizEntry Frage2 = new QuizEntry("Hallo", "ABC", "B", "C", "D");
        QuizEntry Frage3 = new QuizEntry("Hallo", "A", "B", "C", "D");
        QuizEntry Frage4 = new QuizEntry("Hallo", "A", "B", "C", "D");
        QuizEntry Frage5 = new QuizEntry("Hallo", "A", "B", "C", "D");

        Beispiel.add(Frage2);

        return Beispiel;
    }
*/
}

