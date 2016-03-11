package com.example.italoberg.jogopergunta;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class PerguntasActivity extends AppCompatActivity {
    Pergunta p;
    TextView titulo;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioButton r4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        titulo = (TextView) findViewById(R.id.textViewTitulo);
        //Apague
        fillDatabase();
        r1 = (RadioButton) findViewById(R.id.radio_r1);
        r2 = (RadioButton) findViewById(R.id.radio_r2);
        r3 = (RadioButton) findViewById(R.id.radio_r3);
        r4 = (RadioButton) findViewById(R.id.radio_r4);

        p = new Pergunta();



        titulo.setText();

    }

    public void fillDatabase(){
        p = new Pergunta();
        p.titulo = "Qual dessas versões do Android foi lançada por ultimo?";
        p.r1 = "Marshmallow";
        p.r2 = "Lolipop";
        p.r3 = "Kitkat";
        p.r4 = "Donut";
        p.difculdade = 4;
        p.save();
    }

}
