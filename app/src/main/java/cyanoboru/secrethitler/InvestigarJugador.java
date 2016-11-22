package cyanoboru.secrethitler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import cyanoboru.secrethitler.core.Partida;

public class InvestigarJugador extends AppCompatActivity {

    private Button returnButton;
    private boolean canInvestigate;
    private ImageView image;

    private void investigar(int n){
        returnButton.setVisibility(View.VISIBLE);
        image.setVisibility(View.VISIBLE);
        canInvestigate = false;
        int idImagen;

        if(Partida.getInstance().getJugadores().get(n).getCartaDePartido().getPartido().equals("Fascista")){
            idImagen = R.mipmap.cartaPartidoFascista;
        }else{
            idImagen = R.mipmap.cartaPartidoLiberal;
        }
        image.setImageResource(idImagen);
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
        canInvestigate = true;

        //Toston de declarar every boton
        botones.add((Button) findViewById(R.id.jugador1));
        botones.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canInvestigate) {
                    investigar(0);
                }
            }
        });
        botones.add(1,(Button) findViewById(R.id.jugador2));
        botones.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canInvestigate){
                    investigar(1);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador3));
        botones.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canInvestigate) {
                    investigar(2);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador4));
        botones.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canInvestigate) {
                    investigar(3);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador5));
        botones.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canInvestigate) {
                    investigar(4);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador6));
        botones.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canInvestigate) {
                    investigar(5);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador7));
        botones.get(6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canInvestigate) {
                    investigar(6);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador8));
        botones.get(7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canInvestigate) {
                    investigar(7);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador9));
        botones.get(8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canInvestigate) {
                    investigar(8);
                }
            }
        });
        botones.add((Button) findViewById(R.id.jugador10));
        botones.get(9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canInvestigate) {
                    investigar(9);
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
        returnButton.setVisibility(View.GONE);
        image = (ImageView) findViewById(R.id.imageView2);
        image.setVisibility(View.GONE);
    }
}
