package ajechess.jugadores;

import ajechess.colores.Color.Colores;
import ajechess.jugadas.Jugada;
import ajechess.piezas.Pieza;
import ajechess.piezas.tipo.Alfil;
import ajechess.piezas.tipo.Caballo;
import ajechess.piezas.tipo.Dama;
import ajechess.piezas.tipo.Peon;
import ajechess.piezas.tipo.Torre;
import java.util.ArrayList;
import ajechess.cuentasRegresivas.CuentaRegresiva;
import ajechess.juegos.Juego;

public abstract class Jugador {

    private Colores color;
    private String nombre;
    private CuentaRegresiva cuenta;
    private ArrayList<Jugada> jugadas;
    private int damasComidas;
    private int torresComidas;
    private int alfilesComidos;
    private int caballosComidos;
    private int peonesComidos;
    private Juego juego;

    public Colores getColor() {
        return color;
    }

    public void setColor(Colores color) {
        this.color = color;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CuentaRegresiva getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaRegresiva cuenta) {
        this.cuenta = cuenta;
    }

    public ArrayList<Jugada> getJugadas() {
        return jugadas;
    }

    public void setJugadas(ArrayList<Jugada> jugadas) {
        this.jugadas = jugadas;
    }

    public int getDamasComidas() {
        return damasComidas;
    }

    public void setDamasComidas(int damasComidas) {
        this.damasComidas = damasComidas;
    }

    public int getTorresComidas() {
        return torresComidas;
    }

    public void setTorresComidas(int torresComidas) {
        this.torresComidas = torresComidas;
    }

    public int getAlfilesComidos() {
        return alfilesComidos;
    }

    public void setAlfilesComidos(int alfilesComidos) {
        this.alfilesComidos = alfilesComidos;
    }

    public int getCaballosComidos() {
        return caballosComidos;
    }

    public void setCaballosComidos(int caballosComidos) {
        this.caballosComidos = caballosComidos;
    }

    public int getPeonesComidos() {
        return peonesComidos;
    }

    public void setPeonesComidos(int peonesComidos) {
        this.peonesComidos = peonesComidos;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Jugada getUltimaJugada() {
        Jugada jugada=null;
        if(!getJugadas().isEmpty()){
            jugada=getJugadas().get(getJugadas().size()-1);
        }
        return jugada;
    }

    public void setComidos(int damas,int torres,int alfiles,int caballos,int peones){
        setDamasComidas(damas);
        setTorresComidas(torres);
        setAlfilesComidos(alfiles);
        setCaballosComidos(caballos);
        setPeonesComidos(peones);
    }

    public Jugador(Colores color, String nombre, int minutos,Juego juego){
        setColor(color);
        setNombre(nombre);
        setJugadas(new ArrayList<Jugada>());
        setCuenta(new CuentaRegresiva(minutos));
        cuenta.reiniciar();
        setComidos(0,0,0,0,0);
        setJuego(juego);
        Thread t=new Thread(getCuenta());
        t.start();
    }

    public void reiniciar(){
        if(getColor()==Colores.BLANCO){
            setColor(Colores.NEGRO);
        }else{
            setColor(Colores.BLANCO);
        }
        cuenta.reiniciar();
        getJugadas().clear();
        setComidos(0,0,0,0,0);
    }

    public void addComido(Pieza pieza){
        if(pieza instanceof Dama){
            setDamasComidas(getDamasComidas()+1);
        }
        if(pieza instanceof Torre){
            setTorresComidas(getTorresComidas()+1);
        }
        if(pieza instanceof Alfil){
            setAlfilesComidos(getAlfilesComidos()+1);
        }
        if(pieza instanceof Caballo){
            setCaballosComidos(getCaballosComidos()+1);
        }
        if(pieza instanceof Peon){
            setPeonesComidos(getPeonesComidos()+1);
        }
    }

    public abstract Jugada jugar();

    public void avisar(Jugada jugada,CuentaRegresiva cuenta){
    }

    public void cerrar(){
    }

    public boolean volverAJugar(boolean deseo){
        return deseo;
    }
}