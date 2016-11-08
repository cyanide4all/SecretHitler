package cyanoboru.secrethitler.core;


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

    //Devuelve un mazo con todas las cartas
    public static MazoDeLeyes barajar(){
        return new MazoDeLeyes();
    }

    public int getCartasRestantes(){
        return cartas.capacity();
    }


    //Saca una carta
    public CartaDeLey caos(){
        if(!cartas.isEmpty()){
            return cartas.pop();
        }else{
            return null; //Esto no debería pasar jamas
        }
    }

    //Saca tres cartas
    public CartaDeLey[] legislacion(){
        if(cartasRestantes > 2){
            CartaDeLey[] toRet = new CartaDeLey[3];
            toRet[0] = cartas.pop();
            toRet[1] = cartas.pop();
            toRet[2] = cartas.pop();
            cartasRestantes-=3;
            return toRet;
        }
        // barajamos otra vez
        this.cartas = new MazoDeLeyes().cartas;
        this.cartasRestantes = nLeyesFascistas + nLeyesLiberales;
        // Si creas una funcion que baraje el mismo mazo sin tener que hacer otro mejor

        return this.legislacion();
    }



}//fin de clase
