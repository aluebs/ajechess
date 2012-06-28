package ajechess.piezas.tipo.coronadores;

import ajechess.colores.Color.Colores;
import ajechess.juegos.Juego;
import ajechess.piezas.Pieza;
import ajechess.piezas.tipo.Alfil;
import ajechess.piezas.tipo.Caballo;
import ajechess.piezas.tipo.Dama;
import ajechess.piezas.tipo.Torre;
import java.util.Observable;

public class Coronador extends Observable{

    public enum Respuestas{DAMA,TORRE,ALFIL,CABALLO,NOSE}
    private Respuestas respuesta;
    private Juego juego;
    
    public Respuestas getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuestas respuesta) {
        this.respuesta = respuesta;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Coronador(Juego juego){
        setRespuesta(Respuestas.NOSE);
        setJuego(juego);
    }

    public synchronized Pieza obtenerPieza(Colores color){
        Pieza pieza=null;
        setRespuesta(Respuestas.NOSE);
        setChanged();
        notifyObservers();
        while(getRespuesta()==Respuestas.NOSE){
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
        if(getRespuesta()==Respuestas.DAMA){
            pieza=new Dama(color,getJuego());
        }
        if(getRespuesta()==Respuestas.TORRE){
            pieza=new Torre(color,getJuego());
        }
        if(getRespuesta()==Respuestas.ALFIL){
            pieza=new Alfil(color,getJuego());
        }
        if(getRespuesta()==Respuestas.CABALLO){
            pieza=new Caballo(color,getJuego());
        }
        return pieza;
    }
}
