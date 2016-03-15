package com.example.italoberg.jogopergunta;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Button buttonJogar;
    int dificuldade;
    private Bitmap bitmap;
    ImageView img;
    ImageView img2;
    EditText editNome;


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

        img = (ImageView) findViewById(R.id.imageView);
        img2 = (ImageView) findViewById(R.id.imageView1);

        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                abrirCamera();
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                carregarGaleria();
            }
        });
    }

    public void abrirCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    public void carregarGaleria(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        InputStream stream = null;
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                if (bitmap != null) {
                    bitmap.recycle();
                }
                stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);
                img2.setImageBitmap(bitmap);
            }
            catch(FileNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                if (stream != null)
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

        }
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
            finish();

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
        editNome = (EditText) findViewById(R.id.nome);
        String nome = editNome.getText().toString();

        Pontuacao pont = new Pontuacao();
        pont.nome = nome;
        //pont.pontos = pontos;
        pont.save();

        Bundle bundle = new Bundle();
        bundle.putInt("DIFICULDADE", dificuldade);
        bundle.putInt("PONTOS", 0);
        bundle.putInt("RODADA", 0);
        bundle.putLong("ID", pont.getId());

        Intent intent = new Intent(this, PerguntasActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }


}
