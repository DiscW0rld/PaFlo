package com.example.florian.projekt;

import com.example.florian.projekt.QuizXmlParser.QuizEntry;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by patricia on 22.03.18.
 */

public class DefinitionXmlParser extends GeneralXmlParser{


    //Sammelt alles ein, was mit dem Tag "Definition" beginnt und endet,
    // ist also die Funktion, die ich in der Class "Definition" aufrufe, wenn ich eine Liste von Definitionseinträgen will
    private List<DefinitionEntry> readDefinitions(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<DefinitionEntry> defTitelData = new ArrayList<DefinitionEntry>();

        parser.require(XmlPullParser.START_TAG, ns, "");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("Definition")) {
                defTitelData.add(readDefinitionEntry(parser));
            } else {
                skipTag(parser);
            }
        }
        return defTitelData;
    }


    //liest alles, was zwischen dem Starttag und Endtag "begriff" steht und gibt es zurück
    private static String readDefTitel(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "begriff");
        String wrAns2 = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "begriff");
        return wrAns2;
    }
    //liest alles, was zwischen dem Starttag und Endtag "erklaerung" steht und gibt es zurück
    private String readErklaerung(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "erklaerung");
        String wrAns2 = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "erklaerung");
        return wrAns2;
    }
    private DefinitionEntry readDefinitionEntry (XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "quizEntries");
        String defTitel = null;
        String erklaerung = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("begriff")) {
                defTitel = readDefTitel(parser);
            } else if (name.equals("erklaerung")) {
                erklaerung = readErklaerung(parser);
            } else {
                skipTag(parser);
            }
        }
        return new DefinitionEntry(defTitel, erklaerung);
    }


    public static class DefinitionEntry {
        public final String defTitel;
        public final String erklaerung;

        private DefinitionEntry(String defTitel, String erklaerung) {
            this.defTitel = defTitel;
            this.erklaerung = erklaerung;
        }
    }
}
