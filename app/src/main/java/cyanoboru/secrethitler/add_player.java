package cyanoboru.secrethitler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cyanoboru.secrethitler.core.Partida;

public class add_player extends AppCompatActivity {

    Button add_more;
    Button no_more;
    EditText player_name;
    String player;
    Partida partida;

    protected void addPlayer( String name ){
        partida.addPlayer(name);
    }

    protected void showPlayerRole(){
        // jump to the activity that shows the role to the player
        Intent i = new Intent(add_player.this, show_role.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

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
                Toast.makeText(add_player.this,"Player "+p+" Created", Toast.LENGTH_LONG).show();
            }
        });
    }
}
