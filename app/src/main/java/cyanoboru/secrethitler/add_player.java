package cyanoboru.secrethitler;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cyanoboru.secrethitler.core.Jugador;
import cyanoboru.secrethitler.core.Partida;

public class add_player extends AppCompatActivity {

    Button add_more;
    Button no_more;
    EditText player_name;
    String player;
    Partida partida;
    ListView listView;

    protected void addPlayer( String name ){
        partida.addPlayer(name);
        ((add_playerAdapter)listView.getAdapter()).addData(name);
        ((add_playerAdapter)listView.getAdapter()).notifyDataSetChanged();

        if(partida.getJugadores().size()>9){
            add_more.setVisibility(View.GONE);
        }
        if(partida.getJugadores().size()>4){
            no_more.setVisibility(View.VISIBLE);
        }
    }

    protected void showPlayerRole(){
        // jump to the activity that shows the role to the player
        partida.repartirRoles();
        Intent i = new Intent(add_player.this, show_role.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new add_playerAdapter(this));

                add_more = (Button) findViewById(R.id.addMorePlayers);
        no_more = (Button) findViewById(R.id.noMorePLayers);
        player_name = (EditText) findViewById(R.id.newPlayerName);

        partida = Partida.getInstance();

        no_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlayerRole();
            }
        });

        add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p = player_name.getText().toString();
                addPlayer(p);
                player_name.setText("");
            }
        });

        no_more.setVisibility(View.GONE);
    }
}

class add_playerAdapter extends BaseAdapter{

    private ArrayList<String> jugadores;
    private Context context;

    public add_playerAdapter(Context ctx){
        this.context = ctx;
        jugadores = new ArrayList<>();
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
        TextView tv = new TextView(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextAppearance(android.R.style.TextAppearance_Large);
        }
        tv.setText(jugadores.get(i));
        return tv;
    }

    public void addData(String jugador){
        jugadores.add(jugador);
    }

    public void deletePosition(int position){
        jugadores.remove(position);
    }
}
