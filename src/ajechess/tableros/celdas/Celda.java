package ajechess.tableros.celdas;

import ajechess.piezas.Pieza;
import ajechess.colores.Color;
import ajechess.colores.Color.Colores;
import ajechess.jugadas.Jugada;
import ajechess.juegos.Juego;
import ajechess.movimientos.Movimiento;
import ajechess.piezas.tipo.Peon;
import ajechess.posicion.Posicion;
import ajechess.tableros.Tablero;

public class Celda {
    private Pieza pieza;
    private Juego juego;

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

    public Celda(Juego juego){
        setPieza(null);
        setJuego(juego);
    }

    public boolean esAtacada(Colores color,Posicion posicion){
        boolean atacada=false;
        Pieza temp=getPieza();
        Colores otroColor=Color.getOtroColor(color);
        setPieza(new Peon(color,getJuego()));
        for(int i=0;i<Tablero.maxX;i++){
            for(int j=0;j<Tablero.maxY;j++){
                Posicion pos=new Posicion(i,j,getJuego());
                Pieza p=getJuego().getTablero().getCelda(pos).getPieza();
                if((p!=null)&&(p.getColor()==otroColor) && p.validarPosible(new Jugada(pos,Movimiento.getMovimiento(pos,posicion),getJuego()))){
                    atacada=true;
                }
            }
        }
        setPieza(temp);
        return atacada;
    }
}