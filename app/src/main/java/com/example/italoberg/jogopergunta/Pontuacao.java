package com.example.italoberg.jogopergunta;

import com.orm.SugarRecord;

/**
 * Created by julia on 13/03/16.
 */
public class Pontuacao extends SugarRecord {

    public String nome;
    public int pontos;

    public void Pontuacao(){

    }

    public String show()
    {
        return nome + " -  " + pontos;
    }

}
