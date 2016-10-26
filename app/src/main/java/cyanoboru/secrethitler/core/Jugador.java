package cyanoboru.secrethitler.core;

/**
 * Created by cya on 22/10/16.
 */

public class Jugador {
    private int id;
    private CartaDePartido partido;
    private CartaDeIdentidad personaje;
    // Cambiado por nemo para que los jugadores tengan nombre
    private String nombre;

    public Jugador(String nombre, CartaDePartido pa, CartaDeIdentidad pe){
        partido = pa;
        personaje = pe;
        this.nombre = nombre;
    }

    // Constructor adicional para que se use con partida
    public Jugador(String nombre){
        this.nombre = nombre;
        // todo el mundo es liberal hasta que se corrompe
        this.setLiberal();
    }

    public void setPartido( CartaDePartido p){
        partido = p;
    }

    public void setPersonaje( CartaDeIdentidad i ){
        personaje = i;
    }

    public void setHitler(){
        this.personaje = new Hitler();
        this.partido = new PartidoFascista();
    }

    public void setFascista(){
        this.personaje = new PersonajeFascista();
        this.partido = new PartidoFascista();
    }

    public void setLiberal(){
        this.personaje = new PersonajeLiberal();
        this.partido = new PartidoLiberal();
    }

    public String getNombre(){
        return nombre;
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
