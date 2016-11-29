package cyanoboru.secrethitler;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import cyanoboru.secrethitler.core.Partida;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import cyanoboru.secrethitler.core.Partida;

public class MatarJugador extends AppCompatActivity {

    private boolean isAlive;
    private Button returnButton;
    private ImageView image;
    private TextView title;
    private TextView infoAsesinato;

    public void kill(int i){
        isAlive = !isAlive;
        Partida.getInstance().getJugadores().get(i).kill();
        if(!Partida.getInstance().getJugadores().get(i).getCartaDeIdentidad().getPersonaje().equals("Hitler")){
            infoAsesinato.setText(Partida.getInstance().getJugadores().get(i).getNombre()+" ha sido asesinado. \nNo era Hitler.");
        }else{
            infoAsesinato.setText("Hitler ha muerto");
            //TODO enviar partida ganada por parte liberal
        }
        returnButton.setVisibility(View.VISIBLE);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investigar_jugador);
        Partida partida = Partida.getInstance();
        ArrayList<Button> botones = new ArrayList<>();
        isAlive = true;

        //Toston de declarar every boton
        botones.add((Button) findViewById(R.id.jugador1));
        botones.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAlive) {
                    kill(0);
                }
            }
        });
        botones.add(1,(Button) findViewById(R.id.jugador2));
        botones.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAlive){
                    kill(1);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador3));
        botones.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAlive) {
                    kill(2);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador4));
        botones.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAlive) {
                    kill(3);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador5));
        botones.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAlive) {
                    kill(4);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador6));
        botones.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAlive) {
                    kill(5);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador7));
        botones.get(6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAlive) {
                    kill(6);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador8));
        botones.get(7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAlive) {
                    kill(7);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador9));
        botones.get(8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAlive) {
                    kill(8);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador10));
        botones.get(9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAlive) {
                    kill(9);
                }
            }
        });
        returnButton = (Button) findViewById(R.id.returnButton);
        //Hemos sobrevivido, albricias

        int i;
        for(i = 0; i<partida.getJugadores().size() ; i ++){
            botones.get(i).setText(partida.getJugadores().get(i).getNombre());
        }
        while(i<10){
            botones.get(i++).setVisibility(View.GONE);
        }
        for(int j = 0; j<partida.getJugadores().size(); j++){
            if(!partida.getJugadores().get(j).estaVivo()){
                botones.get(j).setVisibility(View.GONE);
            }
        }
        returnButton.setVisibility(View.GONE);
        image = (ImageView) findViewById(R.id.imageView2);
        image.setVisibility(View.GONE);
        title = (TextView) findViewById(R.id.superpoderID);
        title.setText("Asesina un Jugador");
        infoAsesinato = (TextView) findViewById(R.id.infoAsesinato);
        infoAsesinato.setText("");
    }

}
