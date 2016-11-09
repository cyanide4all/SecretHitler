package cyanoboru.secrethitler.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cya on 22/10/16.
 */

public class Tablero {
    private List<LeyLiberal> filaLiberal;
    private List<LeyFascista> filaFascista;
    private int contadorDeCaos;
    private MazoDeLeyes leyes;



    public Tablero(int nJug){
        filaLiberal = new ArrayList<>();
        filaFascista = new ArrayList<>();
        leyes = new MazoDeLeyes();
        contadorDeCaos = 0;

    }

    public List<CartaDeLey> get3Cartas(){
        return leyes.legislacion();
    }

    public void aprobarLey(CartaDeLey cdl){
        if (cdl.getLey().equals("Fascista")){
            filaFascista.add((LeyFascista) cdl); //Aqui podría hacerse la comprobacion de poderes
        }else{
            filaLiberal.add((LeyLiberal) cdl);
        }
        resetCaos(); //Esta norma nos la saltabamos porque no me la sabia pero ahora si
    }

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
