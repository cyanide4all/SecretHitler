package cyanoboru.secrethitler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cyanoboru.secrethitler.core.CartaDeLey;
import cyanoboru.secrethitler.core.Partida;
import cyanoboru.secrethitler.core.Tablero;

public class legislar extends AppCompatActivity {

    protected TextView law1;
    protected TextView law2;
    protected TextView law3;

    protected Button showLaws;

    protected List<TextView> showCartas = new ArrayList<>();
    protected boolean hidden;
    protected List<CartaDeLey> cartas;
    protected Tablero tablero;

    protected void discardCard(TextView card, CartaDeLey cartaDeLey){
        // todo no mostrar la carta en tooglelaws
        // todo dialogo
        // todo presidente y canciller
        if(!hidden) {
            showCartas.remove(card);
            cartas.remove(cartaDeLey);
            card.setText("");
            // todo esto es cutre
            TextView jugando = (TextView) findViewById(R.id.ID_Jugando);
            jugando.setText("Canciller");
            toogleLaws();
            if (showCartas.size() == 1) {
                this.setResult(1, new Intent().putExtra("carta", cartas.get(0)));
                this.finish();
            }
        }
    }

    protected void toogleLaws(){
        if(!hidden){
            for(TextView t: showCartas) {
                t.setText("hidden");
            }
        }else{
            int i = 0;
            for(TextView t: showCartas) {
                t.setText(cartas.get(i++).getLey());
            }
        }
        hidden = !hidden;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislar);

        hidden = true;
        law1 = (TextView) findViewById(R.id.ley1);
        law2 = (TextView) findViewById(R.id.ley2);
        law3 = (TextView) findViewById(R.id.ley3);
        showCartas.add(law1);
        showCartas.add(law2);
        showCartas.add(law3);

        this.tablero = Partida.getInstance().getTablero();
        this.cartas = tablero.get3Cartas();

        showLaws = (Button) findViewById(R.id.showLawsButton);

        showLaws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleLaws();
            }
        });

        law1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discardCard(law1, cartas.get(0));
            }
        });
        law2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discardCard(law2, cartas.get(1));
            }
        });
        law3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discardCard(law3, cartas.get(2));
            }
        });
    }
}
