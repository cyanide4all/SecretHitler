package cyanoboru.secrethitler;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;

import cyanoboru.secrethitler.core.Jugador;
import cyanoboru.secrethitler.core.Partida;

public class show_role extends AppCompatActivity implements View.OnClickListener {

    TextView playername;
    ImageView showroleparty;

    Button showbutton;
    Button nextbutton;

    Partida partida;
    Iterator<Jugador> it;
    Jugador currentJugador;
    boolean show = true;

    protected void nextPlayer(){
        if (it.hasNext()) {
            currentJugador = it.next();
            playername.setText("Player : "+currentJugador.getNombre());
            if(currentJugador.getCartaDeIdentidad().getPersonaje().compareTo("Hitler") == 0){
                showroleparty.setImageResource(R.mipmap.cartahitler);
            }else if(currentJugador.getCartaDeIdentidad().getPersonaje().compareTo("Fascista") == 0){
                showroleparty.setImageResource(R.mipmap.cartafascista);
            }else {
                showroleparty.setImageResource(R.mipmap.cartaliberal);
            }
            Toast.makeText(show_role.this,"Next player", Toast.LENGTH_SHORT).show();
        }else{
            jumpToGamePhase();
        }
    }

    protected void jumpToGamePhase(){
        startActivity(new Intent(show_role.this,MainActivity.class));
    }

    protected void showCarta(boolean show){
        if(show){
            // previously invisible view
            View myView = showroleparty;

            int cx = myView.getWidth() / 2;
            int cy = myView.getHeight() ;

            float finalRadius = (float) Math.hypot(cx, cy);

            Animator anim = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
            }

            myView.setVisibility(View.VISIBLE);
            anim.start();
        }else{
            // previously visible view
            final View myView = showroleparty;

            int cx = myView.getWidth() / 2;
            int cy = myView.getHeight() ;

            float initialRadius = (float) Math.hypot(cx, cy);

            Animator anim = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);
            }

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            });

            anim.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_role);

        partida = Partida.getInstance();
        it = partida.getJugadores().iterator();

        playername = (TextView) findViewById(R.id.playerName);
        showroleparty = (ImageView) findViewById(R.id.showImage);

        showbutton = (Button) findViewById(R.id.showButton);
        nextbutton = (Button) findViewById(R.id.okButton);

        nextPlayer();

        showroleparty.setVisibility(View.INVISIBLE);

        showbutton.setOnClickListener(this);

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPlayer();
            }
        });
    }

    @Override
    public void onClick(View v) {
        showCarta(show);
        show = !show;
    }
}
