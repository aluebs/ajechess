package ajechess.juegos;

import ajechess.colores.Color;
import ajechess.colores.Color.Colores;
import ajechess.jugadores.Jugador;
import ajechess.jugadores.tipo.Computadora;
import ajechess.jugadores.tipo.Humano;
import ajechess.jugadores.tipo.HumanoEnRed;
import ajechess.tableros.Tablero;
import java.util.Observable;

public class Juego extends Observable implements Runnable{

    public enum Estados{INICIO,MITURNO,SUTURNO,FINAL,SALIR};
    public enum Respuestas{SI,NO,NOSE};
    private Estados estado;
    private Respuestas respuesta;
    private Jugador jugador;
    private Jugador oponente;
    private Jugador ganador;
    private Tablero tablero;

    public synchronized Estados getEstado() {
        return estado;
    }

    public synchronized void setEstado(Estados estado) {
        this.estado = estado;
    }

    public synchronized Respuestas getRespuesta() {
        return respuesta;
    }

    public synchronized void setRespuesta(Respuestas respuesta) {
        this.respuesta = respuesta;
    }

    public synchronized Jugador getJugador() {
        return jugador;
    }

    private synchronized void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public synchronized Jugador getOponente() {
        return oponente;
    }

    private synchronized void setOponente(Jugador oponente) {
        this.oponente = oponente;
    }

    public synchronized Jugador getGanador() {
        return ganador;
    }

    public synchronized void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    public synchronized Tablero getTablero() {
        return tablero;
    }

    public synchronized void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Juego(String nombreJugador,String nombreOponente,int minutos){
        setEstado(Estados.INICIO);
        Colores color=Color.GetRandomColor();
        setJugador(new Humano(color,nombreJugador,minutos,this));
        setOponente(new Humano(Color.getOtroColor(color),nombreOponente,minutos,this));
        setGanador(null);
        setTablero(null);
    }

    public Juego(String nombre,int minutos){
        setEstado(Estados.INICIO);
        Colores color=Color.GetRandomColor();
        setJugador(new Humano(color,nombre,minutos,this));
        setOponente(new HumanoEnRed(Color.getOtroColor(color),nombre,minutos,this));
        setGanador(null);
        setTablero(null);
    }

    public Juego(String nombre,String ip){
        setEstado(Estados.INICIO);
        setJugador(new Humano(Colores.BLANCO,nombre,0,this));
        setOponente(new HumanoEnRed(nombre,ip,this));
        setGanador(null);
        setTablero(null);
    }

    public Juego(String nombre,int minutos,int dificultad){
        setEstado(Estados.INICIO);
        Colores color=Color.GetRandomColor();
        setJugador(new Humano(color,nombre,minutos,this));
        setOponente(new Computadora(Color.getOtroColor(color), minutos,this));
        setGanador(null);
        setTablero(null);
    }

    public void run(){
        while(getEstado()!=Estados.SALIR){
            switch(getEstado()){
                case INICIO:{
                    estadoInicio();
                    break;
                }case MITURNO:{
                    estadoMiTurno();
                    break;
                }case SUTURNO:{
                    estadoSuTurno();
                    break;
                }case FINAL:{
                    estadoFinal();
                    break;
                }
            }
            setChanged();
            notifyObservers();
        }
        getJugador().cerrar();
        getOponente().cerrar();
    }

    private void estadoInicio(){
        if(getTablero()==null){
            setTablero(new Tablero(this));
        }
        getJugador().reiniciar();
        getOponente().reiniciar();
        if(getJugador().getColor()==Colores.BLANCO){
            setEstado(Estados.MITURNO);
        }else{
            setEstado(Estados.SUTURNO);
        }
        getTablero().reiniciar();
        setRespuesta(Respuestas.NOSE);
    }

    private void estadoMiTurno(){
        getJugador().getCuenta().setEncendido(true);
        getOponente().avisar(getJugador().jugar(),getJugador().getCuenta());
        if(getTablero().esJaqueMate(getOponente().getColor())){
            setGanador(getJugador());
            setEstado(Estados.FINAL);
        }else if(getJugador().getCuenta().isTermino()){
            setGanador(getOponente());
            setEstado(Estados.FINAL);
        }else if(getTablero().esTablas(getOponente().getColor())){
            setGanador(null);
            setEstado(Estados.FINAL);
        }else{
            setEstado(Estados.SUTURNO);
        }
        getJugador().getCuenta().setEncendido(false);
    }

    private void estadoSuTurno(){
        getOponente().getCuenta().setEncendido(true);
        getJugador().avisar(getOponente().jugar(),getOponente().getCuenta());
        if(getTablero().esJaqueMate(getJugador().getColor())){
            setGanador(getOponente());
            setEstado(Estados.FINAL);
        }else if(getOponente().getCuenta().isTermino()){
            setGanador(getJugador());
            setEstado(Estados.FINAL);
        }else if(getTablero().esTablas(getJugador().getColor())){
            setGanador(null);
            setEstado(Estados.FINAL);
        }else{
            setEstado(Estados.MITURNO);
        }
        getOponente().getCuenta().setEncendido(false);
    }

    private void estadoFinal(){
        getOponente().getCuenta().setEncendido(false);
        getJugador().getCuenta().setEncendido(false);
        while(getRespuesta()==Respuestas.NOSE){
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
        boolean jugar=false;
        if(getRespuesta()==Respuestas.SI){
            jugar=getOponente().volverAJugar(true);
        }else{
            jugar=getOponente().volverAJugar(false);
        }
        if(jugar){
            setEstado(Estados.INICIO);
        }else{
            setEstado(Estados.SALIR);
        }  
    }

    public synchronized Jugador getJugadorActual(){
        Jugador jugadorActual=null;
        if(getEstado()==Estados.MITURNO){
            jugadorActual=getJugador();
        }else if(getEstado()==Estados.SUTURNO){
            jugadorActual=getOponente();
        }
        return jugadorActual;
    }
}