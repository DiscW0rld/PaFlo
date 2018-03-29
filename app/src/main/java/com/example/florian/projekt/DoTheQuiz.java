package com.example.florian.projekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by patricia on 29.03.18.
 */

public class DoTheQuiz extends AppCompatActivity{



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_quiz);
        final Button newQuiz = (Button) findViewById(R.id.new_quiz);

        newQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ce = v.getId();

                if (ce == R.id.definition) {

                    Intent intent = new Intent(DoTheQuiz.this, AddNewQuiz.class);
                    startActivity(intent);
                }
            }

        });
    }
    //von Flo übernehmen
}
