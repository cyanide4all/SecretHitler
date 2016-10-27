package cyanoboru.secrethitler.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

/**
 * Created by cya on 25/10/16.
 */

public class Partida {

    private static Partida instance = null;

    public static Partida getInstance(){
        if (instance == null){
            instance = new Partida();
        }
        return instance;
    }

    private Tablero tablero;
    private ArrayList<Jugador> jugadores;

    protected Partida (){
        jugadores = new ArrayList<Jugador>();
    }

    public void addPlayer(String playerName ){
        jugadores.add(new Jugador(playerName));
    }

    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }

    //Crea el array de jugadores en funcion del numero de jugadores que existan
    public void repartirRoles(){
        int numJugadores = jugadores.size();
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
        // create a non repeating random numbers machine
        Integer[] arr = new Integer[numJugadores];
        for (int i = 0; i<numJugadores; i++){
            arr[i] = i;
        }
        Collections.shuffle(Arrays.asList(arr));
        int pos = 0;

        jugadores.get(arr[pos]).setHitler();
        pos++;

        while (criterio > 0) {
            jugadores.get(arr[pos]).setFascista();
            criterio--;
            pos++;
        }
        // todos los jugadores tienen ahora su rol
    }

}
