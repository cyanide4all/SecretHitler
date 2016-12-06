package cyanoboru.secrethitler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cyanoboru.secrethitler.core.Partida;

public class vitoria extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences prefs;
    private int liberalesGanadas;
    private int fascistasGanadas;
    private TextView partidasGanadasView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitoria);


        TextView winners = (TextView) findViewById(R.id.ganadores);
        Intent i = getIntent();
        //Para lectura de preferencias
        prefs = this.getPreferences( Context.MODE_PRIVATE );
        liberalesGanadas = prefs.getInt("L",0);
        fascistasGanadas = prefs.getInt("F",0);

        llevarCuenta(i.getStringExtra("winners"));

        winners.setText(i.getStringExtra("winners"));

        Button reiniciar = (Button) findViewById(R.id.reiniciarPartida);
        reiniciar.setOnClickListener(this);

        partidasGanadasView = (TextView) findViewById(R.id.PartidasGanadasView);
        partidasGanadasView.setText("Los liberales han ganado "+liberalesGanadas+" veces hasta hoy\n" +
                                    "Los fascistas han ganado "+fascistasGanadas+" veces hasta hoy");
    }

    @Override
    public void onClick(View v) {
        Partida.clear();
        startActivity(new Intent(vitoria.this,MainActivity.class));
    }

    public void llevarCuenta(String msg){
        //Escritura
        SharedPreferences.Editor editor = prefs.edit();
        if(msg.equals("Los liberales ganan")){
            editor.putInt( "L", liberalesGanadas++ );
            editor.putInt( "F", fascistasGanadas );
        }else{
            editor.putInt( "L", liberalesGanadas );
            editor.putInt( "F", fascistasGanadas++ );
        }
        editor.apply();
    }


}
