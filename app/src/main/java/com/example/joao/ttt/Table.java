package com.example.joao.ttt;

import java.util.ArrayList;

/**
 * Created by João on 12/06/2016.
 */
public class Table {
    private String[][] table = new String[3][3];

    public Table(){
        for (int i = 0; i<3; i++){
            for (String e: table[i]) {
                e = " ";
            }
        }
    }

    public boolean play(String s, int x, int y){
        if (!table[x][y].equals(" ")){
            return false;
        } else table[x][y] = s;
        return true;
    }
}
