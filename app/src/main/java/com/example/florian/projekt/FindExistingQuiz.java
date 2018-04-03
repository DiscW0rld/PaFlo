package com.example.florian.projekt;

import android.content.Context;

import org.w3c.dom.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by patricia on 29.03.18.
 */

public class FindExistingQuiz {

    public void getFileInput(String xmlName){

        try {
            DocumentBuilderFactory factory;
            DocumentBuilder builder;
            InputStream is;
            Document dom;
            factory = DocumentBuilderFactory.newInstance();
            is = new FileInputStream(xmlName);
            builder = factory.newDocumentBuilder();

            dom = builder.parse(is);
        }
        catch(Exception e){}
    }


}
