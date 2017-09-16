package cyanoboru.secrethitler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cyanoboru.secrethitler.core.Jugador;
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

        ListView listView = (ListView) findViewById(R.id.listaView);
        listView.setAdapter(new VictoriaAdapter(this));

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
            editor.putInt( "L", ++liberalesGanadas );
            editor.putInt( "F", fascistasGanadas );
        }else{
            editor.putInt( "L", liberalesGanadas );
            editor.putInt( "F", ++fascistasGanadas );
        }
        editor.commit();
    }
}

class VictoriaAdapter extends BaseAdapter{

    ArrayList<Jugador> jugadores;
    Context ctx;

    public VictoriaAdapter(Context ctx){
        this.ctx = ctx;
        jugadores = Partida.getInstance().getJugadores();
    }

    @Override
    public int getCount() {
        return jugadores.size();
    }

    @Override
    public Object getItem(int i) {
        return jugadores.get(i);
    }

    @Override
    public long getItemId(int i) {
        return jugadores.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView tv = new TextView(ctx);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextAppearance(android.R.style.TextAppearance_Large);
        }
        tv.setText(jugadores.get(i).getNombre()+ " : "+jugadores.get(i).getCartaDeIdentidad().getPersonaje());
        return tv;
    }
}
