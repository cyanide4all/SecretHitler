package cyanoboru.secrethitler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cyanoboru.secrethitler.core.CartaDeLey;
import cyanoboru.secrethitler.core.Partida;
import cyanoboru.secrethitler.core.Tablero;

public class MainActivity extends AppCompatActivity {

    protected static int REQUEST_CARTADELEY = 1;
    protected Partida partida;
    protected Button leg;
    protected Button caos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        partida = Partida.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leg = (Button) findViewById(R.id.legislacionButton);
        leg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lanzamiento de una legislacion
                startActivityForResult(new Intent(MainActivity.this, legislar.class), REQUEST_CARTADELEY);
            }
        });

        caos = (Button) findViewById(R.id.CaosButton);
        caos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aumenta caos
                partida.incrementarCaos();
                //LANZAR METODO QUE ACTUALICE LOS CAMPOS NECESARIOS
                actualizarTodo();
                if(partida.getTablero().getCaos()==0){
                    Toast.makeText(MainActivity.this,"LEY APROBADA POR CAOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        if (!partida.rolesListos){
            startActivity(new Intent(MainActivity.this, add_player.class));
            // redirect main activity to add player
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CARTADELEY){
            Toast.makeText(MainActivity.this,"LEY APROBADA POR LEGISLACION", Toast.LENGTH_LONG).show();
            Partida.getInstance().getTablero().aprobarLey((CartaDeLey) data.getExtras().getSerializable("carta"));
        }
        actualizarTodo();
    }

    //Actualiza todos los textview necesarios para mostrar que esta pasando en el tablero
    public void actualizarTodo(){
        TextView update;
        //Acualizamos el contador de liberales y fascistas
        update = (TextView) findViewById(R.id.FascistasAprobadas);
        update.setText(String.valueOf(partida.getTablero().getFascistas()));
        update = (TextView) findViewById(R.id.LiberalesAprobadas);
        update.setText(String.valueOf(partida.getTablero().getLiberales()));

        //Actualizamos el contador de caos
        update = (TextView) findViewById(R.id.NivelDeCaos);
        update.setText(String.valueOf(partida.getTablero().getCaos()));

        //Actualizamos el textbox de info importante
        //TODO MUY FUERTE


    }
}
