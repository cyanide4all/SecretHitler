package cyanoboru.secrethitler;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cyanoboru.secrethitler.core.CartaDeLey;
import cyanoboru.secrethitler.core.Partida;
import cyanoboru.secrethitler.core.Tablero;

public class legislar extends AppCompatActivity {

    protected View container;
    protected Button showLaws;

    protected List<ImageView> showCartas = new ArrayList<>();
    protected boolean hidden;
    protected List<CartaDeLey> cartas;
    protected String c1;
    protected String c2;
    protected String c3;
    protected Tablero tablero;
    protected ImageView[] showcartas;

    protected void discardCard(String card, ImageView im){
        if(!hidden) {
            TextView t = (TextView) findViewById(R.id.ID_Jugando);
            t.setText("Canciller");
            //showCartas.remove(im);
            removeCarta(card);

            im.setVisibility(View.GONE);


            if (cartas.size() == 1) {
                this.setResult(1, new Intent().putExtra("carta", cartas.get(0)));
                Log.d("me quedo con",cartas.get(0).getLey());
                this.finish();
            }else{
                toogleLaws();
            }

        }else{
            Toast.makeText(this,"Touch show laws to discard one",Toast.LENGTH_SHORT).show();
        }
    }

    protected void removeCarta(String carta){
        for (CartaDeLey c: cartas){
            if(c.getLey().compareTo(carta) == 0){
                Log.d("borrada ",c.getLey());
                cartas.remove(c);
                break;
            }
        }
    }

    protected void toogleLaws(){
        if(hidden){
            // previously invisible view
            View myView = container;

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
            final View myView = container;

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
        hidden = !hidden;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislar);

        showcartas = new ImageView[3];

        hidden = true;
        showcartas[0] = (ImageView) findViewById(R.id.law1);
        showcartas[1] = (ImageView) findViewById(R.id.law2);
        showcartas[2] = (ImageView) findViewById(R.id.law3);

        this.tablero = Partida.getInstance().getTablero();
        this.cartas = tablero.get3Cartas();
        c1 = cartas.get(0).getLey();
        c2 = cartas.get(1).getLey();
        c3 = cartas.get(2).getLey();

        showLaws = (Button) findViewById(R.id.showLawsButton);

        showLaws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleLaws();
            }
        });

        container = findViewById(R.id.LeyesContainer);
        container.setVisibility(View.GONE);

        int i = 0;
        for(ImageView im: showcartas){
            if(cartas.get(i++).getLey().compareTo("Liberal") == 0){
                im.setImageResource(R.mipmap.leyliberal);
            }else{
                im.setImageResource(R.mipmap.leyfascista);
            }
        }

        showcartas[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discardCard(c1, showcartas[0]);
            }
        });
        showcartas[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discardCard(c2, showcartas[1]);
            }
        });
        showcartas[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discardCard(c3, showcartas[2]);
            }
        });
    }
}
