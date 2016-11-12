package cyanoboru.secrethitler.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cya on 22/10/16.
 */

public class Tablero {
    private int filaLiberal;
    private int filaFascista;
    private int contadorDeCaos;
    private MazoDeLeyes leyes;



    public Tablero(int nJug){
        filaLiberal = 0;
        filaFascista = 0;
        leyes = new MazoDeLeyes();
        contadorDeCaos = 0;

    }

    public int getLiberales(){
        return filaLiberal;
    }
    public int getFascistas(){
        return filaFascista;
    }
    public int getCaos(){
        return contadorDeCaos;
    }

    public List<CartaDeLey> get3Cartas(){
        return leyes.legislacion();
    }




    //***********************************************************************************
    //*ESTE METODO ES SUPER IMPORTANTE TAMBIEN TIENE QUE CHEKEAR CONDICIONES DE VICTORIA*
    //***********************************************************************************

    //OBSERVA CUANTO ESFUERZO INNECESARIO HAY AQUI
    public void aprobarLey(CartaDeLey cdl){
        if (cdl.getLey().equals("Fascista")){
            filaFascista++; //Aqui podría hacerse la comprobacion de poderes
        }else{
            filaLiberal++;
        }
        leyes.aprobadaQueNoSeBaraja(cdl);
        resetCaos(); //Esta norma nos la saltabamos porque no me la sabia pero ahora si
    }

    //***********************************************************************************
    //***********************************HASTA AQUI**************************************
    //***********************************************************************************




    private void resetCaos() {
        contadorDeCaos = 0;
    }

    public void aumentarCaos(){
        contadorDeCaos++;
        if(contadorDeCaos==3){
            contadorDeCaos = 0;
            aprobarLey(leyes.caos());
        }
    }
}
