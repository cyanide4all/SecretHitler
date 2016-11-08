package cyanoboru.secrethitler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cyanoboru.secrethitler.core.Partida;

public class MainActivity extends AppCompatActivity {

    protected Partida partida;
    protected Button leg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leg = (Button) findViewById(R.id.legislacionButton);
        leg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, legislar.class));
            }
        });

        partida = Partida.getInstance();
        if (!partida.rolesListos){
            startActivity(new Intent(MainActivity.this, add_player.class));
            // redirect main activity to add player
        }
    }
}
