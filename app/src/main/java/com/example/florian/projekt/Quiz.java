package com.example.florian.projekt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.xmlpull.v1.*;

import java.io.IOException;

public class Quiz extends AppCompatActivity {

    public final String quizdata;
    public final String question;
    public final String rightAnswer;
    public final String wrAns1;
    public final String wrAns2;
    public final String wrAns3;

    private Quiz(String quizdata, String question, String rightAnswer, String wrAns1, String wrAns2, String wrAns3){
        this.quizdata = quizdata;
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.wrAns1 = wrAns1;
        this.wrAns2 = wrAns2;
        this.wrAns3 = wrAns3;
    }


    private Quiz readQuiz(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "entry");
        String title = null;
        String summary = null;
        String link = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
                title = readQuestion(parser);
            } else if (name.equals("rightAnswer")) {
                summary = readRightAnswer(parser);
            } else if (name.equals("wrAns1")) {
                link = readWrAns1(parser);
            } else if (name.equals("wrAns2")) {
                link = readWrAns2(parser);
            } else if (name.equals("wrAns3")) {
                link = readWrAns3(parser);
            } else {
                skip(parser);
            }
        }
        return new Quiz(quizdata, question, rightAnswer, wrAns1, wrAns2, wrAns3);
    }

    private String readQuestion(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "title");
        return title;
    }
    private String readRightAnswer(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "title");
        return title;
    }
    private String readWrAns1(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "title");
        return title;
    }
    private String readWrAns2(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "title");
        return title;
    }
    private String readWrAns3(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "title");
        return title;
    }
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }
}
