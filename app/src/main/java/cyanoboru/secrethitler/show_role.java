package cyanoboru.secrethitler;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Iterator;

import cyanoboru.secrethitler.core.Jugador;
import cyanoboru.secrethitler.core.Partida;

public class show_role extends AppCompatActivity {

    TextView playername;
    TextView showroleparty;
    TextView showroleplayer;

    Button showbutton;
    Button nextbutton;

    Partida partida;
    Iterator<Jugador> it;
    Jugador currentJugador;

    protected void nextPlayer(){
        if (it.hasNext()) {
            currentJugador = it.next();
            playername.setText("Player : "+currentJugador.getNombre());
        }else{
            jumpToGamePhase();
        }
    }

    protected void jumpToGamePhase(){
        startActivity(new Intent(show_role.this,MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_role);

        partida = Partida.getInstance();
        it = partida.getJugadores().iterator();

        playername = (TextView) findViewById(R.id.playerName);
        showroleparty = (TextView) findViewById(R.id.showRoleParty);
        showroleplayer = (TextView) findViewById(R.id.ShowRolePlayer);

        showbutton = (Button) findViewById(R.id.showButton);
        nextbutton = (Button) findViewById(R.id.okButton);

        nextPlayer();

        showbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    showroleparty.setText(currentJugador.getCartaDePartido().getPartido());
                    showroleplayer.setText(currentJugador.getCartaDeIdentidad().getPersonaje());
                }else{
                    showroleparty.setText("Press show to view");
                    showroleplayer.setText("Press show to view");
                }
            return true;
            }
        });

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPlayer();
            }
        });
    }
}
