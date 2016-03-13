package com.example.italoberg.jogopergunta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Button buttonJogar;
    int dificuldade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //Referencias a View

        spinner = (Spinner) findViewById(R.id.spinner);
        buttonJogar = (Button) findViewById(R.id.buttonJogar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dificuldades_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //StringBuilder sb = new StringBuilder();
                //sb.append(position);
                // Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                Log.i("Id", Long.toString(id));
                if(id!=0) {
                    buttonJogar.setVisibility(View.VISIBLE);
                    if(id==1)
                        dificuldade = 1;
                    else if(id ==2)
                        dificuldade = 2;
                    else
                        dificuldade =  3;
                    Log.i("Dificuldade",Integer.toString(dificuldade));

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
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

    public void buttonJogarClick(View v){
        /*
        //Create the bundle
        Bundle bundle = new Bundle();
        //Add your data from getFactualResults method to bundle
        bundle.putString("VENUE_NAME", venueName);
        //Add the bundle to the intent
        i.putExtras(bundle);
        startActivity(i);
        */

        Bundle bundle = new Bundle();
        bundle.putInt("DIFICULDADE", dificuldade);
        bundle.putInt("PONTOS", 0);
        Intent intent = new Intent(this, PerguntasActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
