package ajechess.piezas.tipo;

import ajechess.colores.Color.Colores;
import ajechess.juegos.Juego;
import ajechess.jugadas.Jugada;
import ajechess.movimientos.Movimiento;
import ajechess.piezas.Pieza;
import java.util.ArrayList;

public class Torre extends Pieza{
    private boolean movido;

    public boolean isMovido() {
        return movido;
    }

    public void setMovido(boolean movido) {
        this.movido = movido;
    }

    public Torre(Colores color,Juego juego){
        super(color,false,false,"torre.svg",juego);
        ArrayList<Movimiento> m=new ArrayList<Movimiento>();
        m.add(new Movimiento(1,0));
        m.add(new Movimiento(2,0));
        m.add(new Movimiento(3,0));
        m.add(new Movimiento(4,0));
        m.add(new Movimiento(5,0));
        m.add(new Movimiento(6,0));
        m.add(new Movimiento(7,0));
        m.add(new Movimiento(-1,0));
        m.add(new Movimiento(-2,0));
        m.add(new Movimiento(-3,0));
        m.add(new Movimiento(-4,0));
        m.add(new Movimiento(-5,0));
        m.add(new Movimiento(-6,0));
        m.add(new Movimiento(-7,0));
        m.add(new Movimiento(0,1));
        m.add(new Movimiento(0,2));
        m.add(new Movimiento(0,3));
        m.add(new Movimiento(0,4));
        m.add(new Movimiento(0,5));
        m.add(new Movimiento(0,6));
        m.add(new Movimiento(0,7));
        m.add(new Movimiento(0,-1));
        m.add(new Movimiento(0,-2));
        m.add(new Movimiento(0,-3));
        m.add(new Movimiento(0,-4));
        m.add(new Movimiento(0,-5));
        m.add(new Movimiento(0,-6));
        m.add(new Movimiento(0,-7));
        setMovimientos(m);
        setComidas(m);
        setMovido(false);
    }

    @Override
    public void mover(Jugada jugada){
        setMovido(true);
    }
}