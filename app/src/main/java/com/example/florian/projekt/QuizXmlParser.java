package com.example.florian.projekt;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by patricia on 22.03.18.
 */

public class QuizXmlParser extends GeneralXmlParser {

    private String readQuizName(XmlPullParser parser) throws IOException, XmlPullParserException{
        parser.require(XmlPullParser.START_TAG, ns, "quizname");
        String quizName = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "quizname");
        return quizName;
    }

    public static class QuizEntry {
        private String question;
        private String rightAns;
        private String wrAns1;
        private String wrAns2;
        private String wrAns3;
        private QuizEntry(String question, String rightAns, String wrAns1, String wrAns2, String wrAns3) {
            this.question = question;
            this.rightAns = rightAns;
            this.wrAns1 = wrAns1;
            this.wrAns2 = wrAns2;
            this.wrAns3 = wrAns3;
        }
    }


    private List<QuizEntry> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<QuizEntry> quizdata = new ArrayList<QuizEntry>();

        parser.require(XmlPullParser.START_TAG, ns, "");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("quizEntries")) {
                quizdata.add(readQuiz(parser));
            } else {
                skipTag(parser);
            }
        }
        return quizdata;
    }

    //Funktion findet einzelne Quizfrage und ordnet Frage und Antworten den richtigen Strings zu.
    //gibt eine einzelne Quizfrage zurück,
    //deren Daten in List<QuizEntry> readFeed(..) zu einem Array gemacht werden.
    private QuizEntry readQuiz(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "quizEntries");
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
            } else if (name.equals("rightAns")) {
                rightAns = readRightAns(parser);
            } else if (name.equals("wrAns1")) {
                wrAns1 = readWrAns1(parser);
            } else if (name.equals("wrAns2")) {
                wrAns2 = readWrAns2(parser);
            } else if (name.equals("wrAns3")) {
                wrAns3 = readWrAns3(parser);
            } else {
                skipTag(parser);
            }
        }
        return new QuizEntry(question, rightAns, wrAns1, wrAns2, wrAns3);
    }


    private String readQuestion(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "question");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "question");
        return title;
    }
    private String readRightAns(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "rightAns");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "rightAns");
        return title;
    }
    private String readWrAns1(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "wrAns1");
        String wrAns1 = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "wrAns1");
        return wrAns1;
    }
    private String readWrAns2(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "wrAns2");
        String wrAns2 = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "wrAns2");
        return wrAns2;
    }
    private String readWrAns3(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "wrAns3");
        String wrAns3 = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "wrAns3");
        return wrAns3;
    }

}

