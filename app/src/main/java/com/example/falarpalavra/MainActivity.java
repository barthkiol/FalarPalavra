package com.example.falarpalavra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView texto;
    ImageView icone;
    String ditado;
    private final int ID_TEXTO_PARA_VOZ = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        icone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ivoz = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                ivoz.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"pt_BR");
                ivoz.putExtra(RecognizerIntent.EXTRA_PROMPT, "Diga alguma coisa!");

                try{
                    startActivityForResult(ivoz, ID_TEXTO_PARA_VOZ);
                }catch(ActivityNotFoundException a){
                    Toast.makeText(getApplicationContext(), "Seu aparelho não  suporta a aplicação", Toast.LENGTH_LONG).show();

                }
            }
        });



    }

    protected void onActivityResult(int id, int resultCodeId, Intent dados){
        super.onActivityResult(id, resultCodeId, dados);
        switch(id){
            case ID_TEXTO_PARA_VOZ:
                if(resultCodeId == RESULT_OK && null != dados){
                    ArrayList<String> result = dados.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ditado = result.get(0);
                    comparador();

                }
                break;
        }



    }


    public void comparador(){
        texto.setText(ditado);
    }
}