package ajechess.tableros;

import ajechess.colores.Color;
import ajechess.colores.Color.Colores;
import ajechess.jugadas.Jugada;
import ajechess.jugadores.tipo.Humano;
import ajechess.juegos.Juego;
import ajechess.movimientos.Movimiento;
import ajechess.piezas.Pieza;
import ajechess.piezas.tipo.*;
import ajechess.posicion.Posicion;
import ajechess.tableros.celdas.Celda;
import java.util.ArrayList;
import java.util.Observable;

public class Tablero extends Observable{
    private ArrayList<ArrayList<Celda>> celdas;
    private Jugada ultimaJugada;
    private Jugada ultimaJugadaValida;
    private int cantidadClicks;
    private Juego juego;

    public final static int maxX=8;
    public final static int maxY=8;

    public ArrayList<ArrayList<Celda>> getCeldas() {
        return celdas;
    }

    private synchronized void setCeldas(ArrayList<ArrayList<Celda>> celdas) {
        this.celdas = celdas;
    }

    public synchronized Jugada getUltimaJugada() {
        return ultimaJugada;
    }

    public synchronized void setUltimaJugada(Jugada ultimaJugada) {
        this.ultimaJugada = ultimaJugada;
    }

    public Jugada getUltimaJugadaValida() {
        return ultimaJugadaValida;
    }

    public void setUltimaJugadaValida(Jugada ultimaJugadaValida) {
        this.ultimaJugadaValida = ultimaJugadaValida;
    }

    public synchronized int getCantidadClicks() {
        return cantidadClicks;
    }

    public synchronized void setCantidadClicks(int cantidadClicks) {
        this.cantidadClicks = cantidadClicks;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public synchronized Celda getCelda(Posicion posicion){
        return celdas.get(posicion.getX()).get(posicion.getY());
    }

    public Tablero(Juego juego){
        setJuego(juego);
        celdas=new ArrayList<ArrayList<Celda>>();
        ArrayList<Celda> fila;
        for(int i=0;i<maxX;i++){
            fila=new ArrayList<Celda>();
            for(int j=0;j<maxY;j++){
                fila.add(new Celda(getJuego()));
            }
            celdas.add(fila);
        }
        setUltimaJugada(new Jugada(null,null,null));
        setCantidadClicks(0);
    }

    public synchronized void reiniciar(){
        Colores colorJugador=getJuego().getJugador().getColor();
        Colores colorOponente=Color.getOtroColor(colorJugador);
        getCelda(new Posicion(0,0,getJuego())).setPieza(new Torre(colorJugador,getJuego()));
        getCelda(new Posicion(0,1,getJuego())).setPieza(new Caballo(colorJugador,getJuego()));
        getCelda(new Posicion(0,2,getJuego())).setPieza(new Alfil(colorJugador,getJuego()));
        if(colorJugador==Colores.BLANCO){
            getCelda(new Posicion(0,3,getJuego())).setPieza(new Dama(colorJugador,getJuego()));
            getCelda(new Posicion(0,4,getJuego())).setPieza(new Rey(colorJugador,getJuego()));
        }else{
            getCelda(new Posicion(0,3,getJuego())).setPieza(new Rey(colorJugador,getJuego()));
            getCelda(new Posicion(0,4,getJuego())).setPieza(new Dama(colorJugador,getJuego()));
        }
        getCelda(new Posicion(0,5,getJuego())).setPieza(new Alfil(colorJugador,getJuego()));
        getCelda(new Posicion(0,6,getJuego())).setPieza(new Caballo(colorJugador,getJuego()));
        getCelda(new Posicion(0,7,getJuego())).setPieza(new Torre(colorJugador,getJuego()));
        for(int i=0;i<maxY;i++){
            getCelda(new Posicion(1,i,getJuego())).setPieza(new Peon(colorJugador,getJuego()));
        }
        for(int i=2;i<(maxX-2);i++){
            for(int j=0;j<maxY;j++){
                getCelda(new Posicion(i,j,getJuego())).setPieza(null);
            }
        }
        for(int i=0;i<maxY;i++){
            getCelda(new Posicion(6,i,getJuego())).setPieza(new Peon(colorOponente,getJuego()));
        }
        getCelda(new Posicion(7,0,getJuego())).setPieza(new Torre(colorOponente,getJuego()));
        getCelda(new Posicion(7,1,getJuego())).setPieza(new Caballo(colorOponente,getJuego()));
        getCelda(new Posicion(7,2,getJuego())).setPieza(new Alfil(colorOponente,getJuego()));
        if(colorJugador==Colores.BLANCO){
            getCelda(new Posicion(7,3,getJuego())).setPieza(new Dama(colorOponente,getJuego()));
            getCelda(new Posicion(7,4,getJuego())).setPieza(new Rey(colorOponente,getJuego()));
        }else{
            getCelda(new Posicion(7,3,getJuego())).setPieza(new Rey(colorOponente,getJuego()));
            getCelda(new Posicion(7,4,getJuego())).setPieza(new Dama(colorOponente,getJuego()));
        }
        getCelda(new Posicion(7,5,getJuego())).setPieza(new Alfil(colorOponente,getJuego()));
        getCelda(new Posicion(7,6,getJuego())).setPieza(new Caballo(colorOponente,getJuego()));
        getCelda(new Posicion(7,7,getJuego())).setPieza(new Torre(colorOponente,getJuego()));
        setUltimaJugada(new Jugada(null,null,null));
        setUltimaJugadaValida(new Jugada(null,null,null));
        setCantidadClicks(0);
        setChanged();
        notifyObservers();
    }

    public synchronized boolean mover(Jugada jugada){
        boolean valida=false;
        if((jugada.getMovimiento()!=null)&&(jugada.getPosicion()!=null)){
            Pieza p=getCelda(jugada.getPosicion()).getPieza();
            valida=(p!=null)&&(p.getColor()==getJuego().getJugadorActual().getColor())&&jugada.validar()&&p.validar(jugada);
            if(valida){
                p.mover(jugada);
                Pieza temp=getCelda(jugada.getPosicion().mover(jugada.getMovimiento())).getPieza();
                if(temp!=null){
                    if(temp.getColor()==getJuego().getJugador().getColor()){
                        getJuego().getJugador().addComido(temp);
                    }else{
                        getJuego().getOponente().addComido(temp);
                    }
                }
                getCelda(jugada.getPosicion().mover(jugada.getMovimiento())).setPieza(p);
                getCelda(jugada.getPosicion()).setPieza(null);
                setUltimaJugadaValida(jugada);
                setChanged();
                notifyObservers();
            }
        }
        return valida;
    }

    public synchronized Pieza moverTemp(Jugada jugada){
        Pieza temp=getCelda(jugada.getPosicion().mover(jugada.getMovimiento())).getPieza();
        getCelda(jugada.getPosicion().mover(jugada.getMovimiento())).setPieza(getCelda(jugada.getPosicion()).getPieza());
        getCelda(jugada.getPosicion()).setPieza(null);
        return temp;
    }

    public synchronized void moverTempInv(Jugada jugada,Pieza pieza){
        getCelda(jugada.getPosicion().mover(jugada.getMovimiento())).setPieza(getCelda(jugada.getPosicion()).getPieza());
        getCelda(jugada.getPosicion()).setPieza(pieza);
    }

    public synchronized boolean esJaque(Colores color){
        boolean jaque=false;
        for(int i=0;i<maxX;i++){
            for(int j=0;j<maxY;j++){
                Posicion pos=new Posicion(i,j,getJuego());
                Celda c=getCelda(pos);
                Pieza p=c.getPieza();
                if((p!=null)&&p.isJaqueable()&&(p.getColor()==color)&&c.esAtacada(color,pos)){
                    jaque=true;
                }
            }
        }
        return jaque;
    }

    public synchronized boolean esJaqueMate(Colores color){
        boolean mate=false;
        if(esJaque(color)){
            mate=puedeMover(color);
        }else{
            mate=false;
        }
        return mate;
    }

    public synchronized boolean esTablas(Colores color){
        boolean tablas=false;
        if(!esJaque(color)){
            tablas=puedeMover(color);
        }else{
            tablas=false;
        }
        return tablas;
    }

    public synchronized boolean puedeMover(Colores color){
        boolean mover=true;
        for(int i=0;i<maxX;i++){
            for(int j=0;j<maxY;j++){
                Posicion pos=new Posicion(i,j,getJuego());
                Celda c=getCelda(pos);
                Pieza p=c.getPieza();
                if((p!=null)&&(p.getColor()==color)){
                    ArrayList<Movimiento> mov=p.getMovimientos();
                    for(int k=0;k<mov.size();k++){
                        Movimiento m=mov.get(k);
                        Jugada jug=new Jugada(pos,m,getJuego());
                        if(jug.validar()&&p.validar(jug)){
                            mover=false;
                        }
                    }
                    mov=p.getComidas();
                    for(int k=0;k<mov.size();k++){
                        Movimiento m=mov.get(k);
                        Jugada jug=new Jugada(pos,m,getJuego());
                        if(jug.validar()&&p.validar(jug)){
                            mover=false;
                        }
                    }
                }
            }
        }
        return mover;
    }

    public void seApreto(int x, int y) {
        Humano jugadorActual = (Humano) getJuego().getJugadorActual();
        if (getCantidadClicks() == 0) {
            Posicion p=new Posicion(x, y,getJuego());
            setUltimaJugada(new Jugada(p,null,getJuego()));
            setCantidadClicks(getCantidadClicks() + 1);
        } else {
            getUltimaJugada().setMovimiento(Movimiento.getMovimiento(getUltimaJugada().getPosicion(), new Posicion(x,y,getJuego())));
            setCantidadClicks(0);
            jugadorActual.setNoJugo(false);
            setChanged();
            notifyObservers();
        }
    }
}