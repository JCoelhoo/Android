package com.example.joao.ttt;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Board extends AppCompatActivity implements OnClickListener {
    private int p1;
    private int p2;
    private Table table;
    private TextView t1;
    private TextView t2;
    private Button[] buttons;
    boolean turn = true;
    int plays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        p1 = 0;
        p2 = 0;
        t1 = (TextView) findViewById(R.id.score1);
        t2 = (TextView) findViewById(R.id.score2);
        table = new Table();
        buttons = new Button[]{(Button) findViewById(R.id.button1),(Button) findViewById(R.id.button2),  (Button) findViewById(R.id.button3), (Button) findViewById(R.id.button4), (Button) findViewById(R.id.button5), (Button) findViewById(R.id.button6), (Button) findViewById(R.id.button7), (Button) findViewById(R.id.button8), (Button) findViewById(R.id.button9)};
        Button restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(this);
        resetTable();
        for (Button b: buttons) {
            b.setOnClickListener(this);
        }
    }

    private void resetTable() {
        for (Button b: buttons){
            b.setText("");
            b.setBackgroundColor(Color.parseColor("#424242"));
            b.setClickable(true);
        }
        table = new Table();
        turn = true;
        plays = 0;
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        if (b.getId() == R.id.restart) {resetTable(); return;}
        if (turn)
            b.setText("X");
        else b.setText("O");
        turn = !turn;
        b.setBackgroundColor(Color.parseColor("#FFFFFF"));
        b.setClickable(false);
        plays++;
        checkWinner();
    }

    public void checkWinner() {
        String s = "";
        //toast("1: " + buttons[0].getText() + " 4: " + buttons[3].getText() + " 7: " + buttons[6].getText()+ " 4: " + buttons[3].getText() +" 5: " + buttons[4].getText());
        if (buttons[0].getText() == buttons[4].getText() && buttons[4].getText() == buttons[8].getText()) {
            s = buttons[0].getText().toString();
        } else if (buttons[2].getText() == buttons[4].getText() && buttons[4].getText() == buttons[6].getText()) {
            s = buttons[2].getText().toString();
        } else  {
            for (int i = 0; i < 7; i+=3){
                if (buttons[i].getText() == buttons[i+1].getText() && buttons[i+1].getText() == buttons[i+2].getText()) {
                    s = buttons[i].getText().toString();
                    break;
                }
            }
            for (int i = 0; i < 3; i++){
                if (buttons[i].getText() == buttons[i+3].getText() && buttons[i+3].getText() == buttons[i+6].getText()) {
                    s = buttons[i].getText().toString();
                    break;
                }
            }
        }
        if (s.equals("") && plays != 9) return;
        else if( s.equals("X")) {p1++; t1.setText("Player 1: "+p1);toast("Player 1 won!");}
        else if( s.equals("O")) {p2++; t2.setText("Player 2: "+p2);toast("Player 2 won!");}

        resetTable();
    }

    public void toast(String m){Toast.makeText(getApplicationContext(), m, Toast.LENGTH_SHORT).show();}
}
