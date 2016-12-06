package cyanoboru.secrethitler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cyanoboru.secrethitler.core.Partida;

public class vitoria extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitoria);

        TextView winners = (TextView) findViewById(R.id.ganadores);
        Intent i = getIntent();
        winners.setText(i.getStringExtra("winners"));

        Button reiniciar = (Button) findViewById(R.id.reiniciarPartida);
        reiniciar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        llevarCuenta();
        Partida.clear();
        startActivity(new Intent(vitoria.this,MainActivity.class));
    }

    public void llevarCuenta(){
        // llevar la cuenta de las partidas ganadas y las perdidas
        // aqui puedes leer y escribir el score total y ensenarlo.
    }
}
