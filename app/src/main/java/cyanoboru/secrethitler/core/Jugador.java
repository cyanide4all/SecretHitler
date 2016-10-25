package cyanoboru.secrethitler.core;

/**
 * Created by cya on 22/10/16.
 */

public class Jugador {
    private int id;
    private CartaDePartido partido;
    private CartaDeIdentidad personaje;

    public Jugador(CartaDePartido pa, CartaDeIdentidad pe){
        partido = pa;
        personaje = pe;
    }

    public CartaDePartido getCartaDePartido(){
        return partido;
    }
    public CartaDeIdentidad getCartaDeIdentidad(){
        return personaje;
    }
    public void setID(int i){
        id=i;
    }


}
