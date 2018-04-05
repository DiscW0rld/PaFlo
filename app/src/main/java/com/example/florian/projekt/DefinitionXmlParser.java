package com.example.florian.projekt;

import android.util.Xml;

import com.example.florian.projekt.QuizXmlParser.QuizEntry;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by patricia on 22.03.18.
 */

public class DefinitionXmlParser extends GeneralXmlParser{

    List<DefinitionEntry> defTitelData;

    public List<DefinitionEntry> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readDefinitions(parser);
        } finally {
            in.close();
        }
    }

    /*DefinitionXmlParser(List<DefinitionEntry> defTitelData){
        defTitelData = this.defTitelData;
    }*/

    //Sammelt alles ein, was mit dem Tag "Definition" beginnt und endet,
    // ist also die Funktion, die ich in der Class "Definition" aufrufe, wenn ich eine Liste von Definitionseinträgen will
    public static List<DefinitionEntry> readDefinitions(XmlPullParser parser) throws XmlPullParserException, IOException {
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
    private static String readErklaerung(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "erklaerung");
        String wrAns2 = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "erklaerung");
        return wrAns2;
    }
    private static DefinitionEntry readDefinitionEntry (XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "definition");
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

    public static List<DefinitionEntry> getExampleDef(){
        List<DefinitionEntry> definition = new ArrayList<>();
        DefinitionEntry def1 = new DefinitionEntry("Didaktik", "Theorie des Unterrichts bzw. des Lehrens und Lernens");
        DefinitionEntry def2 = new DefinitionEntry("Unterricht", "die regelmäßige und systematische Vermittlung von Wissen durch einen Lehrer an Schüler");
        DefinitionEntry def3 = new DefinitionEntry("Lernen", "sich Wissen und Fähigkeiten aneignen");
        DefinitionEntry def4 = new DefinitionEntry("Lehren", "Vermittlung von Fähigkeiten und Kenntnissen an andere Personen");
        DefinitionEntry def5 = new DefinitionEntry("Kompetenz", "Ein Sachverstand / eine Fähigkeit");
        definition.add(def1);
        definition.add(def2);
        definition.add(def3);
        definition.add(def4);
        definition.add(def5);

        return definition;
    }
}
