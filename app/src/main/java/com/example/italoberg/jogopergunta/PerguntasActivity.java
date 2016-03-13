package com.example.italoberg.jogopergunta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PerguntasActivity extends AppCompatActivity {

    List<Pergunta> perguntadas;
    Pergunta p;
    TextView textViewTitulo;
    RadioButton radioButtonR1;
    RadioButton radioButtonR2;
    RadioButton radioButtonR3;
    RadioButton radioButtonR4;
    int dificuldade;
    TextView textViewDebug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Pergunta> tudo = Pergunta.listAll(Pergunta.class);

        if (tudo.size() == 0) {
            fillDatabase();
        }
        Log.i("Banco_Size",Integer.toString(tudo.size()));

        if (perguntadas == null){
            perguntadas = new ArrayList<Pergunta>();
        }
        Log.i("Perguntadas_Size",Integer.toString(perguntadas.size()));


        textViewTitulo = (TextView) findViewById(R.id.textViewTitulo);
        radioButtonR1 = (RadioButton) findViewById(R.id.radio_r1);
        radioButtonR2 = (RadioButton) findViewById(R.id.radio_r2);
        radioButtonR3 = (RadioButton) findViewById(R.id.radio_r3);
        radioButtonR4 = (RadioButton) findViewById(R.id.radio_r4);

        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        dificuldade = bundle.getInt("DIFICULDADE");
        Log.i("Dificuldade_recebido",Integer.toString(dificuldade));
        //dificuldade = getIntent().getExtras().getInt("dificuldade");


        p = RandomQuestionByLevel(tudo, dificuldade);

        Log.i("Pergunta", p.titulo);

        fillViewWithQuestion(p);

        Log.i("Dificuldade", Integer.toString(dificuldade));


        /*List<Pergunta> round = RandomByLevel(tudo,dificuldade);

        ;
        Log.i("Pergunta", round.get(1).titulo);
        Log.i("Tamanhooooo", Integer.toString(round.size()));

        fillViewWithQuestion(round.get(0));*/
    }

    public void fillViewWithQuestion(Pergunta p){
        textViewTitulo.setText(p.titulo);

        Random rand = new Random();
        int num = rand.nextInt(5);
        Log.i("Random", Integer.toString(num));

        switch (num){

            case 1:
                radioButtonR1.setText(p.r1);
                radioButtonR2.setText(p.r2);
                radioButtonR3.setText(p.r3);
                radioButtonR4.setText(p.r4);
                break;

            case 2:
                radioButtonR1.setText(p.r2);
                radioButtonR2.setText(p.r4);
                radioButtonR3.setText(p.r3);
                radioButtonR4.setText(p.r1);
                break;

            case 3:
                radioButtonR1.setText(p.r3);
                radioButtonR2.setText(p.r2);
                radioButtonR3.setText(p.r1);
                radioButtonR4.setText(p.r4);
                break;

            case 4:
                radioButtonR1.setText(p.r4);
                radioButtonR2.setText(p.r3);
                radioButtonR3.setText(p.r2);
                radioButtonR4.setText(p.r1);
                break;

            default:
                radioButtonR1.setText(p.r4);
                radioButtonR2.setText(p.r2);
                radioButtonR3.setText(p.r3);
                radioButtonR4.setText(p.r1);
                break;
        }
    }

    public Pergunta RandomQuestionByLevel(List<Pergunta> perguntas, int dif){

        Random rand = new Random();
        List<Pergunta> todas;
        Pergunta saida;
        int num;

        todas = Pergunta.find(Pergunta.class, "difculdade=?",  Integer.toString(dif));
        num = rand.nextInt(todas.size());
        saida = todas.get(num);

        while (perguntadas.contains(todas.get(num)) && perguntadas.size() < 2){
            num = rand.nextInt(todas.size());
            saida = todas.get(num);
        }

        Log.i("Pergunta_saida", saida.titulo);
        perguntadas.add(saida);
        return saida;
    }

    /*public List<Pergunta> RandomByLevel(List<Pergunta> perguntas, int dif){

        Random rand = new Random();
        int num;
        List<Pergunta> out = perguntas;

        if(dif==1) {
            out = Pergunta.find(Pergunta.class, "difculdade<=?", "4");

            while (perguntas.size()>2){
                num = rand.nextInt(perguntas.size());
                perguntas.remove(num);
            }
            out = perguntas;
        }
        else if(dif==2){
             perguntas = Pergunta.find(Pergunta.class, "difculdade<=? and difculdade>?", "7", "4");
            Log.i("Pergunta",perguntas.get(0).titulo );
            while (perguntas.size()>3){
                num = rand.nextInt(perguntas.size());
                perguntas.remove(num);
            }
            out = perguntas;
        }
        else {
            perguntas = Pergunta.find(Pergunta.class, "difculdade>?", "7");
            Log.i("Pergunta",perguntas.get(0).titulo );

        }
        return out;
    }

    public void showBank(){
        textViewDebug = (TextView) findViewById(R.id.textViewDebug);

        List<Pergunta> todo = Pergunta.listAll(Pergunta.class);

        String out ="";
        for(int i=0; i<todo.size(); i++){
            out+= todo.get(i).show();
        }

        textViewDebug.setText(out);


    }*/

    public void fillDatabase(){
        //Pergunta.deleteAll(Pergunta.class);

        //1
        p = new Pergunta();
        p.titulo = "Qual dessas versões do Android foi lançada por ultimo?";
        p.r1 = "Marshmallow";
        p.r2 = "Lolipop";
        p.r3 = "Kitkat";
        p.r4 = "Donut";
        p.difculdade = 1;
        p.save();

        //2
        p = new Pergunta();
        p.titulo = "Quantas ligações faz o carbono?";
        p.r1 = "4";
        p.r2 = "2";
        p.r3 = "3";
        p.r4 = "Nenhuma";
        p.difculdade = 1;
        p.save();
        //3
        p = new Pergunta();
        p.titulo = "Qual o continente que tem mais países?";
        p.r1 = "África";
        p.r2 = "Europa";
        p.r3 = "Ásia";
        p.r4 = "América";
        p.difculdade = 3;
        p.save();
        //4
        p = new Pergunta();
        p.titulo = "Qual é a maior cidade da Turquia?";
        p.r1 = "Istambul";
        p.r2 = "Ancara";
        p.r3 = "Bagdá";
        p.r4 = "Teerã";
        p.difculdade = 3;
        p.save();
        //5
        p = new Pergunta();
        p.titulo = "Quantas estrelas tem a bandeira da China?";
        p.r1 = "Cinco";
        p.r2 = "Duas";
        p.r3 = "Três";
        p.r4 = "Uma";
        p.difculdade = 3;
        p.save();
        //6
        p = new Pergunta();
        p.titulo = "Qual a montanha mais elevada da África?";
        p.r1 = "Kilimanjaro";
        p.r2 = "Monte Branco";
        p.r3 = "Aconcágua";
        p.r4 = "Everest";
        p.difculdade = 2;
        p.save();
    }

    public void buttonClickResponder(View v){

        /*dificuldade = getIntent().getExtras().getInt("dificuldade");
        Log.i("Dificuldade", Integer.toString(dificuldade));

        TextView textViewDebug = (TextView) findViewById(R.id.textViewDebug);
        textViewDebug.setText(Integer.toString(dificuldade));

        showBank();*/

    }

}
