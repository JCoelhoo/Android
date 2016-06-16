package com.example.joao.ttt;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class Sequence extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Button> sequence;
    //private ArrayList<Button> user;
    private Button[] buttons;
    private int turn = 0;
    private TextView current;
    private TextView maximum;
    private int lvl = 1;
    private int maxLvl = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence);

        Button x = (Button) findViewById(R.id.restart);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
        current = (TextView) findViewById(R.id.current);
        maximum = (TextView) findViewById(R.id.maximum);
        buttons = new Button[]{(Button) findViewById(R.id.b1), (Button) findViewById(R.id.b2), (Button) findViewById(R.id.b3), (Button) findViewById(R.id.b4), (Button) findViewById(R.id.b5), (Button) findViewById(R.id.b6), (Button) findViewById(R.id.b7), (Button) findViewById(R.id.b8), (Button) findViewById(R.id.b9)};
        for (Button b: buttons){
            b.setOnClickListener(this);
        }
        sequence = new ArrayList<Button>();
        newSequence();
    }

    private void newSequence() {
        Random r = new Random();
        sequence.add(buttons[r.nextInt(9)]);
        turn = 0;

        toast(sequence.get(sequence.size()-1).toString());

        for (Button b: buttons){
            b.setClickable(false);
        }
        for (Button b: sequence){
            ColorDrawable buttonColor = (ColorDrawable) b.getBackground();
            final int colorId = buttonColor.getColor();
            b.setBackgroundColor(Color.parseColor("#80323232"));

            b.setBackgroundColor(colorId);
        }
        for (Button b: buttons){
            b.setClickable(true);
        }
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;

        if (b == sequence.get(turn)){
            turn++;
            //b.setBackgroundColor(Color.parseColor("#80323232"));
            //sequence.remove(0);
        } else {
            toast("You have lost :(");
            newGame();
        }
        if (turn == sequence.size()){
            toast("Very well! Next level...");
            lvl++;
            current.setText("Current Score: "+ lvl);
            if (lvl>maxLvl) {maxLvl = lvl; maximum.setText("High Score: "+ maxLvl);}
            turn = 0;
            newSequence();
        }


    }

    public void newGame() {
        lvl=1;
        current.setText("Current Score: "+ lvl);
        sequence.clear();
        newSequence();
    }

    public void toast(String m){
        Toast.makeText(getApplicationContext(), m, Toast.LENGTH_SHORT).show();
    }

}
