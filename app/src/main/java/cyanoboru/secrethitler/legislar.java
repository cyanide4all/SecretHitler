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

    protected ImageView law1;
    protected ImageView law2;
    protected ImageView law3;

    protected View container;
    protected Button showLaws;

    protected List<ImageView> showCartas = new ArrayList<>();
    protected boolean hidden;
    protected List<CartaDeLey> cartas;
    protected List<CartaDeLey> cloneCartas;
    protected Tablero tablero;

    protected void discardCard(String card, ImageView im){
        if(!hidden) {
            TextView t = (TextView) findViewById(R.id.ID_Jugando);
            t.setText("Canciller");
            showCartas.remove(im);
            cartas.remove(card);

            im.setVisibility(View.GONE);


            if (showCartas.size() == 1) {
                this.setResult(1, new Intent().putExtra("carta", cartas.get(0)));
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

        hidden = true;
        law1 = (ImageView) findViewById(R.id.law1);
        law2 = (ImageView) findViewById(R.id.law2);
        law3 = (ImageView) findViewById(R.id.law3);
        showCartas.add(law1);
        showCartas.add(law2);
        showCartas.add(law3);

        this.tablero = Partida.getInstance().getTablero();
        this.cartas = tablero.get3Cartas();
        this.cloneCartas = this.cartas;

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
        for(ImageView im:showCartas){
            if(cartas.get(i++).getLey().compareTo("Liberal") == 0){
                im.setImageResource(R.mipmap.leyliberal);
            }else{
                im.setImageResource(R.mipmap.leyfascista);
            }
        }

        law1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discardCard(cloneCartas.get(0).getLey(), law1);
            }
        });
        law2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discardCard(cloneCartas.get(1).getLey(), law2);
            }
        });
        law3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discardCard(cloneCartas.get(2).getLey(), law3);
            }
        });
    }
}
