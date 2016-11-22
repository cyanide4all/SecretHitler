package cyanoboru.secrethitler.core;


import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by cya on 21/10/16.
 */

public class MazoDeLeyes {
    //CONSTANTES
    private static final int nLeyesLiberales = 6;
    private static final int nLeyesFascistas = 11;
    private static final double randomLimit = 0.3;

    //ATRIBUTOS
    private Stack<CartaDeLey> cartas;
    private ArrayList<CartaDeLey> aBarajar;
    private int cartasRestantes;
    //METODOS

    //Constructor
    public MazoDeLeyes(){
        cartas = new Stack<CartaDeLey>();
        aBarajar = new ArrayList<>();
        int liberalesPorIntroducir= nLeyesLiberales;
        int fascistasPorIntroducir= nLeyesFascistas;

        while (liberalesPorIntroducir > 0 ||
                fascistasPorIntroducir > 0){
            if(Math.random()> randomLimit) {
                if(fascistasPorIntroducir > 0) {
                    cartas.push(new LeyFascista());
                    fascistasPorIntroducir--;
                }
            }else{
                if(liberalesPorIntroducir > 0) {
                    cartas.push(new LeyLiberal());
                    liberalesPorIntroducir--;
                }
            }
        }
        cartasRestantes = nLeyesFascistas + nLeyesLiberales;
    }

    //Cartas se baraja todas las cartas de aBarajar
    public void barajar(){
        cartas = new Stack<>();
        ArrayList<Integer> randomVector = new ArrayList<>(aBarajar.size());
        int counter = 0;

        for (int t:randomVector){
             randomVector.set(counter, counter++);
        }
        // crear un arrayList tipo [1,2,3,4,5,6,7,8,9..]
        Collections.shuffle(randomVector);
        // ahora tiene randoms tipo [2,4,1,7,8...]
        // que NO SE REPITEN de modo que NO hay cartas repetidas indebidamente

        for(int i = 0; i<aBarajar.size(); i++){
            cartas.push(aBarajar.get(i)); //Pusheamos en orden normal
        }
        for(CartaDeLey c: cartas){
            Log.d("Carta:",c.getLey());
        }
        aBarajar = new ArrayList<>(); //Reiniciamos
    }

    public int getCartasRestantes(){
        return cartas.capacity();
    }

    public void aprobadaQueNoSeBaraja(CartaDeLey c){
        aBarajar.remove(c);
    }

    //Saca una carta y baraja si no quedan
    public CartaDeLey caos(){
        if(!cartas.isEmpty()){
            return cartas.pop();
        }else{
            this.barajar();  //Baraja
            this.cartasRestantes = nLeyesFascistas + nLeyesLiberales;
            return this.caos();     //y reintenta
        }
    }

    //Saca tres cartas ya baraja si no quedan
    public List<CartaDeLey> legislacion(){
        if(cartasRestantes > 2){
            List<CartaDeLey> toRet = new ArrayList<>(3);
            CartaDeLey aux;
            for(int i = 0; i<3;i++) {
                aux = cartas.pop();
                toRet.add(aux);
                Log.d("carta",aux.getLey());
                aBarajar.add(aux);
            }
            cartasRestantes-=3;
            return toRet;
        }else {
            // ahora baraja bien
            // barajamos otra vez
            this.barajar();
            this.cartasRestantes = nLeyesFascistas + nLeyesLiberales;
            return this.legislacion();
        }
    }

    public List<CartaDeLey> verTresPrimerasCartas(){
        List<CartaDeLey> l = new ArrayList<>(3);
        Stack<CartaDeLey> clone = (Stack<CartaDeLey>) this.cartas.clone();
        for(int i = 0; i<3;i++) {
            l.add(clone.pop());
        }
        return l;
    }



}//fin de clase // gracias capitan!
