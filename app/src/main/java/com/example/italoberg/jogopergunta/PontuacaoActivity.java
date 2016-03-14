package com.example.italoberg.jogopergunta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class PontuacaoActivity extends AppCompatActivity {
    private TextView textPontuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontuacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textPontuacao = (TextView) findViewById(R.id.textViewPontuacao);
        fillPoints();
    }

    public void fillPoints(){
        List<Pontuacao> points = Pontuacao.findWithQuery(Pontuacao.class, "SELECT * FROM Pontuacao ORDER BY pontos DESC", null);
        String pontuacaoFinal = "";
        int posicao = 1;
        for(Pontuacao p : points){
            pontuacaoFinal += posicao + "º  " + p.nome + " - " + p.pontos + "\n";
            posicao++;
            //só mostra os 10 primeiros
            if (posicao == 11)
                break;
        }
        textPontuacao.setText(pontuacaoFinal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_pontuacao) {
            Intent pontuacao = new Intent(this, PontuacaoActivity.class);
            startActivity(pontuacao);

        } else if (id == R.id.action_settings){
            return true;

        }else if (id == R.id.action_sobre){
            Intent sobre = new Intent(this, SobreActivity.class);
            startActivity(sobre);

        }else if (id == R.id.action_close){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonVoltar(View v){
        Intent intent = new Intent(PontuacaoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
