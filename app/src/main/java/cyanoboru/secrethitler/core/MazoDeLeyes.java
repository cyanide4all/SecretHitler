package cyanoboru.secrethitler.core;


import java.util.ArrayList;
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
        for(int i = 0; i<aBarajar.size(); i++){
            cartas.push(aBarajar.get(i)); //Pusheamos en orden normal TODO hacerlo con random
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
            return this.caos();     //y reintenta
        }
    }

    //Saca tres cartas ya baraja si no quedan
    public List<CartaDeLey> legislacion(){
        if(cartasRestantes > 2){
            List<CartaDeLey> toRet = new ArrayList<>();
            CartaDeLey aux;
            for(int i = 0; i<3;i++) {
                aux = cartas.pop();
                toRet.add(aux);
                aBarajar.add(aux);
            }
            cartasRestantes-=3;
            return toRet;
        }else {
            // TODO esto pero bien, que esta barajando mal
            // barajamos otra vez
            this.barajar();
            this.cartasRestantes = nLeyesFascistas + nLeyesLiberales;
            // Si creas una funcion que baraje el mismo mazo sin tener que hacer otro mejor -> GG
            return this.legislacion();
        }
    }



}//fin de clase
