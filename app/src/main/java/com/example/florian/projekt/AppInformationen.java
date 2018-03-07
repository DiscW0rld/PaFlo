package com.example.florian.projekt;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by patricia on 06.03.18.
 */


public class AppInformationen extends AppCompatActivity{

    TextView appinfos;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appziel);
       /* Resources res = getResources();


        appinfos = (TextView) findViewById(R.id.AppInfos);
        appinfos.setText(res.getString(R.string.Appinformationen));*/
    }

}
