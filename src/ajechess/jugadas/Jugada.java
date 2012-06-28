package ajechess.jugadas;

import ajechess.juegos.Juego;
import ajechess.movimientos.Movimiento;
import ajechess.piezas.Pieza;
import ajechess.posicion.Posicion;

public class Jugada {

    private Posicion posicion;
    private Movimiento movimiento;
    private Pieza pieza;
    private Juego juego;

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Jugada(Posicion posicion,Movimiento movimiento,Juego juego){
        setPosicion(posicion);
        setMovimiento(movimiento);
        setJuego(juego);
        if((getPosicion()!=null)&&(getJuego()!=null)){
            setPieza(getJuego().getTablero().getCelda(getPosicion()).getPieza());
        }
    }

    public Jugada(String jugada,Juego juego){
        setPosicion(new Posicion(jugada.substring(0,2)));
        setMovimiento(Movimiento.getMovimiento(getPosicion(),new Posicion(jugada.substring(2,4))));
        setJuego(juego);
        setPieza(getJuego().getTablero().getCelda(getPosicion()).getPieza());
    }

    public Jugada jugadaInversa(){
        return new Jugada(getPosicion().mover(getMovimiento()),getMovimiento().movimientoInverso(),getJuego());
    }

    public Posicion getPosicionFinal(){
        return getPosicion().mover(getMovimiento());
    }

    public boolean validar(){
        return getPosicion().validar()&&getPosicionFinal().validar();
    }

    @Override
    public String toString(){
        return getPosicion().toString()+getPosicionFinal().toString();
    }
}
