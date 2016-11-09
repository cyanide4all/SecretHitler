package cyanoboru.secrethitler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cyanoboru.secrethitler.core.CartaDeLey;
import cyanoboru.secrethitler.core.Partida;
import cyanoboru.secrethitler.core.Tablero;

public class MainActivity extends AppCompatActivity {

    protected static int REQUEST_CARTADELEY = 1;
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
                startActivityForResult(new Intent(MainActivity.this, legislar.class), REQUEST_CARTADELEY);
            }
        });

        partida = Partida.getInstance();
        if (!partida.rolesListos){
            startActivity(new Intent(MainActivity.this, add_player.class));
            // redirect main activity to add player
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CARTADELEY){
            Toast.makeText(MainActivity.this,"LEY APROBADA", Toast.LENGTH_LONG).show();
            Partida.getInstance().getTablero().aprobarLey((CartaDeLey) data.getExtras().getSerializable("carta"));
        }
    }
}
