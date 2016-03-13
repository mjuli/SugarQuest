package com.example.italoberg.jogopergunta;

import com.orm.SugarRecord;

/**
 * Created by Italo Berg on 09/03/2016.
 */
public class Pergunta extends SugarRecord {

    public String titulo;
    public String r1;
    public String r2;
    public String r3;
    public String r4;
    public int difculdade;

    public void Pergunta(){

    }

    public String show()
    {
        return titulo + " " + r1 + " " + r2 + " " + r3 + " " + r4 + System.lineSeparator();
    }

}
