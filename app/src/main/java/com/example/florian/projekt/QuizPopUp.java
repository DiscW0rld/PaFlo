package com.example.florian.projekt;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizPopUp extends AppCompatActivity {

    TextView Text;
    Button ok;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpopup);

        Button ok = (Button) findViewById(R.id.Button);
        TextView Text = (TextView) findViewById(R.id.textView);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigh = dm.heightPixels;

        getWindow().setLayout(width*4,heigh*3);
    }
}