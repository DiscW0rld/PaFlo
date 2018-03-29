package com.example.florian.projekt;

/**
 * Created by patricia on 22.03.18.
 */

public class DefinitionXmlParser {

    public static class DefinitionEntry {
        public final String defTitel;
        public final String erklaerung;

        private DefinitionEntry(String defTitel, String erklaerung) {
            this.defTitel = defTitel;
            this.erklaerung = erklaerung;
        }
    }
}
