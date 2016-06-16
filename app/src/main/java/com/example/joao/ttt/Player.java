package com.example.joao.ttt;

/**
 * Created by João on 12/06/2016.
 */
public class Player {
    private String symbol;

    public Player(String symbol){
        this.symbol = symbol;
    }

    public boolean play(int x, int y, Table t){
        return t.play(symbol, x, y);
    }

}
