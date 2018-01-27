package com.example.florian.projekt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.*;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final Button def = (Button) findViewById(R.id.button3);
       final Button qui = (Button) findViewById(R.id.button2);

       def.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


                   System.exit(0);
               }

       });

        qui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if(ce == R.id.button2){

                    Intent intent = new Intent(MainActivity.this, Quiz.class);
                    startActivity(intent);
                }
            }

        });
    }
}
