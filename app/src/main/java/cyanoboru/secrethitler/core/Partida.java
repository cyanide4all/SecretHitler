package cyanoboru.secrethitler.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cya on 25/10/16.
 */

public class Partida {
    private int numJugadores;
    private Tablero tablero;
    private Jugador[] jugadores;

    public Partida (int numJug){
        numJugadores = numJug;
        tablero = new Tablero(numJugadores);
        jugadores = new Jugador[numJugadores];
        repartirRoles();
        }

    //Crea el array de jugadores en funcion del numero de jugadores que existan
    public void repartirRoles(){
        int criterio;
        if(numJugadores<7){
            criterio = 1;
        }else{
            if(numJugadores<9){
                criterio = 2;
            }else{
                criterio = 3;
            }
        }
        List<Jugador> aux = new ArrayList<Jugador>();
        aux.add( new Jugador(new PartidoFascista(), new Hitler()));
        for (int i=0; i<numJugadores-1; i++) {
            if (i < criterio) {
                aux.add(new Jugador(new PartidoFascista(), new PersonajeFascista()));
            } else {
                aux.add(new Jugador(new PartidoLiberal(), new PersonajeLiberal()));
            }
        }
        Collections.shuffle(aux);
        for(int i = 0; i<aux.size(); i++){
            jugadores[i] = aux.get(i);
            jugadores[i].setID(i);
        }
    }

}
