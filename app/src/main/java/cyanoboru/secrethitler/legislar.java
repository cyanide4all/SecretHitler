package cyanoboru.secrethitler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cyanoboru.secrethitler.core.CartaDeLey;
import cyanoboru.secrethitler.core.Partida;
import cyanoboru.secrethitler.core.Tablero;

public class legislar extends AppCompatActivity {

    protected TextView law1;
    protected TextView law2;
    protected TextView law3;

    protected Button showLaws;

    protected boolean hidden;
    protected CartaDeLey[] cartas;
    protected Tablero tablero;

    protected void discardCard(TextView card){
        // todo no mostrar la carta en tooglelaws
        // todo dialogo
        // todo presidente y canciller
        card.setText("");

    }

    protected void toogleLaws(){
        if(hidden){
            law1.setText("hidden");
            law2.setText("hidden");
            law3.setText("hidden");
        }else{
            // todo coger 3 cartas
            law1.setText(cartas[0].getLey());
            law2.setText(cartas[1].getLey());
            law3.setText(cartas[2].getLey());
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
                discardCard(law1);
            }
        });
        law2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discardCard(law2);
            }
        });
        law3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discardCard(law3);
            }
        });
    }
}
